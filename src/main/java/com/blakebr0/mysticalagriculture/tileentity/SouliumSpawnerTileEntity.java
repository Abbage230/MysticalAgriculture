package com.blakebr0.mysticalagriculture.tileentity;

import com.blakebr0.cucumber.energy.DynamicEnergyStorage;
import com.blakebr0.cucumber.helper.StackHelper;
import com.blakebr0.cucumber.inventory.BaseItemStackHandler;
import com.blakebr0.cucumber.inventory.CachedRecipe;
import com.blakebr0.cucumber.inventory.OnContentsChangedFunction;
import com.blakebr0.cucumber.inventory.SidedInventoryWrapper;
import com.blakebr0.cucumber.tileentity.BaseInventoryTileEntity;
import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.api.crafting.ISouliumSpawnerRecipe;
import com.blakebr0.mysticalagriculture.block.SouliumSpawnerBlock;
import com.blakebr0.mysticalagriculture.container.SouliumSpawnerContainer;
import com.blakebr0.mysticalagriculture.container.inventory.UpgradeItemStackHandler;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blakebr0.mysticalagriculture.init.ModTileEntities;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import com.blakebr0.mysticalagriculture.util.RecipeIngredientCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.items.IItemHandler;

import java.util.List;
import java.util.UUID;

public class SouliumSpawnerTileEntity extends BaseInventoryTileEntity implements MenuProvider, IUpgradeableMachine {
    private static final int FUEL_TICK_MULTIPLIER = 20;
    public static final int OPERATION_TIME = 200;
    public static final int FUEL_USAGE = 20;
    public static final int FUEL_CAPACITY = 80000;
    public static final int SPAWN_RADIUS = 3;

    private final BaseItemStackHandler inventory;
    private final UpgradeItemStackHandler upgradeInventory;
    private final DynamicEnergyStorage energy;
    private final SidedInventoryWrapper[] sidedInventoryWrappers;
    private final CachedRecipe<CraftingInput, ISouliumSpawnerRecipe> recipe;
    private MachineUpgradeTier tier;
    private int progress;
    private int fuelLeft;
    private int fuelItemValue;
    private boolean isRunning;
    private double spin, oSpin;
    private DisplayEntity[] displayEntities;

    public SouliumSpawnerTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.SOULIUM_SPAWNER.get(), pos, state);
        this.inventory = createInventoryHandler((slot) -> this.onInventoryChanged());
        this.upgradeInventory = new UpgradeItemStackHandler();
        this.energy = new DynamicEnergyStorage(FUEL_CAPACITY, this::setChangedFast);
        this.sidedInventoryWrappers = SidedInventoryWrapper.create(this.inventory, List.of(Direction.UP, Direction.DOWN, Direction.NORTH), this::canInsertStackSided, null);
        this.recipe = new CachedRecipe<>(ModRecipeTypes.SOULIUM_SPAWNER.get());
    }

    @Override
    public BaseItemStackHandler getInventory() {
        return this.inventory;
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);

        this.progress = tag.getInt("Progress");
        this.fuelLeft = tag.getInt("FuelLeft");
        this.fuelItemValue = tag.getInt("FuelItemValue");
        this.energy.deserializeNBT(lookup, tag.get("Energy"));
        this.upgradeInventory.deserializeNBT(lookup, tag.getCompound("UpgradeInventory"));
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);

        tag.putInt("Progress", this.progress);
        tag.putInt("FuelLeft", this.fuelLeft);
        tag.putInt("FuelItemValue", this.fuelItemValue);
        tag.put("Energy", this.energy.serializeNBT(lookup));
        tag.put("UpgradeInventory", this.upgradeInventory.serializeNBT(lookup));
    }

    @Override
    public void onLoad() {
        super.onLoad();

        this.reloadActiveRecipe();
    }

    @Override
    public void onDataPacket(Connection connection, ClientboundBlockEntityDataPacket packet, HolderLookup.Provider lookup) {
        super.onDataPacket(connection, packet, lookup);

        this.reloadActiveRecipe();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookup) {
        super.handleUpdateTag(tag, lookup);

        this.reloadActiveRecipe();
    }

    @Override
    public Component getDisplayName() {
        return Localizable.of("container.mysticalagriculture.soulium_spawner").build();
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return SouliumSpawnerContainer.create(id, playerInventory, this.inventory, this.upgradeInventory, this.getBlockPos());
    }

    @Override
    public UpgradeItemStackHandler getUpgradeInventory() {
        return this.upgradeInventory;
    }

    public IItemHandler getSidedInventory(Direction direction) {
        if (direction == null) direction = Direction.NORTH;

        return switch (direction) {
            case UP -> this.sidedInventoryWrappers[0];
            case DOWN -> this.sidedInventoryWrappers[1];
            default -> this.sidedInventoryWrappers[2];
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        if (tile.energy.getEnergyStored() < tile.energy.getMaxEnergyStored()) {
            var fuel = tile.inventory.getStackInSlot(1);

            if (tile.fuelLeft <= 0 && !fuel.isEmpty()) {
                tile.fuelItemValue = fuel.getBurnTime(null);

                if (tile.fuelItemValue > 0) {
                    tile.fuelLeft = tile.fuelItemValue *= FUEL_TICK_MULTIPLIER;
                    tile.inventory.setStackInSlot(1, StackHelper.shrink(fuel, 1, true));

                    tile.setChangedFast();
                }
            }

            if (tile.fuelLeft > 0) {
                var fuelPerTick = Math.min(Math.min(tile.fuelLeft, tile.getFuelUsage() * 2), tile.energy.getMaxEnergyStored() - tile.energy.getEnergyStored());

                tile.fuelLeft -= tile.energy.receiveEnergy(fuelPerTick, false);

                if (tile.fuelLeft <= 0)
                    tile.fuelItemValue = 0;

                tile.setChangedFast();
            }
        }

        var tier = tile.getMachineTier();

        if (tier != tile.tier) {
            tile.tier = tier;

            if (tier == null) {
                tile.energy.resetMaxEnergyStorage();
            } else {
                tile.energy.setMaxEnergyStorage((int) (FUEL_CAPACITY * tier.getFuelCapacityMultiplier()));
            }

            tile.setChangedFast();
        }

        var isDisabled = level.hasNeighborSignal(tile.getBlockPos());
        var wasRunning = tile.isRunning;

        tile.isRunning = false;

        if (tile.energy.getEnergyStored() >= tile.getFuelUsage() && !isDisabled) {
            var input = tile.inventory.getStackInSlot(0);

            if (!input.isEmpty()) {
                var recipe = tile.getActiveRecipe();

                if (recipe != null && input.getCount() >= recipe.getCount(0)) {
                    tile.isRunning = true;
                    tile.progress++;
                    tile.energy.extractEnergy(tile.getFuelUsage(), false);

                    if (tile.progress >= tile.getOperationTime() && tile.attemptSpawn(recipe)) {
                        tile.inventory.setStackInSlot(0, StackHelper.shrink(input, recipe.getCount(0), false));
                        tile.progress = 0;
                        tile.sendSpawnParticles();
                    }

                    tile.setChangedFast();
                }
            } else {
                tile.isRunning = false;

                if (tile.progress > 0) {
                    tile.progress = 0;

                    tile.setChangedFast();
                }
            }
        }

        if (wasRunning != tile.isRunning) {
            level.setBlock(pos, state.setValue(SouliumSpawnerBlock.RUNNING, tile.isRunning), 3);

            tile.setChangedFast();
        }

        tile.dispatchIfChanged();
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, SouliumSpawnerTileEntity tile) {
        var isRunning = state.getValue(SouliumSpawnerBlock.RUNNING);
        var spinSpeed = isRunning ? 200D : 400D;

        tile.oSpin = tile.spin;
        tile.spin = (tile.spin + (1000.0D / spinSpeed)) % 360.0D;

        if (isRunning) {
            tile.sendRunningParticles();
        }
    }

    public static BaseItemStackHandler createInventoryHandler() {
        return createInventoryHandler(null);
    }

    public static BaseItemStackHandler createInventoryHandler(OnContentsChangedFunction onContentsChanged) {
        return BaseItemStackHandler.create(2, onContentsChanged, builder -> {
            builder.addSlotLimit(0, 512);
        });
    }

    public ISouliumSpawnerRecipe getActiveRecipe() {
        return this.recipe.get();
    }

    public DynamicEnergyStorage getEnergy() {
        return this.energy;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getOperationTime() {
        if (this.tier == null)
            return OPERATION_TIME;

        return (int) (OPERATION_TIME * this.tier.getOperationTimeMultiplier());
    }

    public int getFuelLeft() {
        return this.fuelLeft;
    }

    public int getFuelItemValue() {
        return this.fuelItemValue;
    }

    public int getFuelUsage() {
        if (this.tier == null)
            return FUEL_USAGE;

        return (int) (FUEL_USAGE * this.tier.getFuelUsageMultiplier());
    }

    public double getSpin() {
        return this.spin;
    }

    public double getoSpin() {
        return this.oSpin;
    }

    public DisplayEntity getDisplayEntity() {
        if (this.displayEntities == null)
            return null;

        var index = Math.toIntExact((System.currentTimeMillis() / 2000L) % this.displayEntities.length);

        return this.displayEntities[index];
    }

    private CraftingInput toCraftingInput() {
        return this.inventory.toShapelessCraftingInput(0, 1);
    }

    private boolean attemptSpawn(ISouliumSpawnerRecipe recipe) {
        if (this.level == null)
            return false;

        var entity = recipe.getRandomEntityType(this.level.random)
                .map(e -> e.data().create(this.level))
                .orElse(null);

        if (entity == null)
            return false;

        var entities = this.level.getEntitiesOfClass(entity.getClass(), AABB.ofSize(this.getBlockPos().getCenter(), SPAWN_RADIUS * 2, SPAWN_RADIUS * 2, SPAWN_RADIUS * 2))
                .stream()
                .filter(Entity::isAlive)
                .count();

        if (entities >= 32)
            return false;

        var positions = BlockPos.betweenClosedStream(
                this.getBlockPos().offset(-SPAWN_RADIUS, 0, -SPAWN_RADIUS),
                this.getBlockPos().offset(SPAWN_RADIUS, 0, SPAWN_RADIUS)
        ).map(BlockPos::immutable).toList();

        var pos = positions.get(this.level.random.nextInt(positions.size()));

        entity.setUUID(UUID.randomUUID());
        entity.moveTo(pos.getX(), pos.getY(), pos.getZ(), this.level.random.nextFloat() * 360F, 0);

        if (entity instanceof Mob mob) {
            mob.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(this.getBlockPos()), MobSpawnType.MOB_SUMMONED, null);
        }

        int attempts = 20;

        while (attempts-- > 0 && !this.canEntitySpawn(entity)) {
            pos = positions.get(this.level.random.nextInt(positions.size()));

            entity.moveTo(pos.getX(), pos.getY(), pos.getZ(), this.level.random.nextFloat() * 360F, 0);
        }

        if (attempts <= 0)
            return false;

        this.level.addFreshEntity(entity);

        return true;
    }

    private boolean canEntitySpawn(Entity entity) {
        return this.level != null && this.level.isUnobstructed(entity) && !this.level.containsAnyLiquid(entity.getBoundingBox());
    }

    private void onInventoryChanged() {
        this.reloadActiveRecipe();
        this.setChangedFast();
    }

    private void reloadActiveRecipe() {
        if (this.level == null)
            return;

        var recipe = this.recipe.checkAndGet(this.toCraftingInput(), this.level);

        if (recipe != null) {
            var entities = recipe.getEntityTypes().unwrap();
            var totalWeight = entities.stream().map(e -> e.getWeight().asInt()).reduce(0, Integer::sum);

            this.displayEntities = entities
                    .stream()
                    .map(e -> new DisplayEntity(e.data().create(this.level), ((double) e.getWeight().asInt() / totalWeight) * 100D))
                    .toArray(DisplayEntity[]::new);
        } else {
            this.displayEntities = null;
        }
    }

    private void sendRunningParticles() {
        if (this.level == null)
            return;

        var pos = this.getBlockPos();

        double x = pos.getX() + (Math.random() / 2) + 0.25D;
        double y = pos.getY() + (Math.random() / 2) + 0.25D;
        double z = pos.getZ() + (Math.random() / 2) + 0.25D;

        this.level.addParticle(ParticleTypes.FLAME, x, y, z, 0, 0, 0);
    }

    private void sendSpawnParticles() {
        if (this.getLevel() == null || this.getLevel().isClientSide())
            return;

        var level = (ServerLevel) this.getLevel();
        var pos = this.getBlockPos();

        for (int i = 0; i < 20; i++) {
            double x = pos.getX() + Math.random();
            double y = pos.getY() + Math.random();
            double z = pos.getZ() + Math.random();

            level.sendParticles(ParticleTypes.FLAME, x, y, z, 1, 0, 0, 0, 0.1D);
        }
    }

    private boolean canInsertStackSided(int slot, ItemStack stack, Direction direction) {
        if (direction == null)
            return true;
        if (slot == 0 && direction == Direction.UP)
            return RecipeIngredientCache.INSTANCE.isValidSouliumSpawnerInput(stack);
        if (slot == 1 && direction == Direction.NORTH)
            return FurnaceBlockEntity.isFuel(stack);

        return false;
    }

    public record DisplayEntity(Entity entity, double chance) {}
}
