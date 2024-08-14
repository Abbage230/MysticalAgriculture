package com.blakebr0.mysticalagriculture.item.armor;

import com.blakebr0.cucumber.item.BaseArmorItem;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import com.blakebr0.mysticalagriculture.api.tinkering.ITinkerable;
import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import com.blakebr0.mysticalagriculture.config.ModConfigs;
import com.blakebr0.mysticalagriculture.init.ModDataComponentTypes;
import com.blakebr0.mysticalagriculture.init.ModItems;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class EssenceLeggingsItem extends BaseArmorItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.ARMOR, AugmentType.LEGGINGS);
    private final int tinkerableTier;
    private final int slots;

    public EssenceLeggingsItem(Holder<ArmorMaterial> material, int tinkerableTier, int slots) {
        super(material, Type.LEGGINGS, p -> p.component(ModDataComponentTypes.EQUIPPED_AUGMENTS, new HashMap<>()));
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (slot == 38 && entity instanceof Player player) {
            for (var augment : AugmentUtils.getAugments(stack)) {
                augment.onArmorTick(stack, level, player);
            }
        }

        for (var augment : AugmentUtils.getAugments(stack)) {
            augment.onInventoryTick(stack, level, entity, slot, isSelected);
        }
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        if (entity == null) {
            return super.damageItem(stack, amount, null, onBroken);
        }

        if (entity instanceof Player player) {
            var isBreaking = stack.getDamageValue() + amount >= stack.getMaxDamage();
            if (stack.isDamageableItem() && !player.isCreative() && isBreaking) {
                for (var augment : AugmentUtils.getAugments(stack)) {
                    player.getInventory().placeItemBackInInventory(new ItemStack(augment.getItem()));
                }

                player.awardStat(Stats.ITEM_BROKEN.get(this));
            }
        }

        return amount;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));

        if (ModConfigs.AWAKENED_SUPREMIUM_SET_BONUS.get() && stack.is(ModItems.AWAKENED_SUPREMIUM_LEGGINGS.get())) {
            tooltip.add(ModTooltips.SET_BONUS.args(ModTooltips.AWAKENED_SUPREMIUM_SET_BONUS.build()).build());
        }

        ModTooltips.addAugmentListToTooltip(tooltip, stack, this.slots);
    }

    @Override
    public int getAugmentSlots() {
        return this.slots;
    }

    @Override
    public EnumSet<AugmentType> getAugmentTypes() {
        return TYPES;
    }

    @Override
    public int getTinkerableTier() {
        return this.tinkerableTier;
    }
}
