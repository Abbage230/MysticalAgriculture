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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class EssenceBootsItem extends BaseArmorItem implements ITinkerable {
    private static final EnumSet<AugmentType> TYPES = EnumSet.of(AugmentType.ARMOR, AugmentType.BOOTS);
    private final int tinkerableTier;
    private final int slots;

    public EssenceBootsItem(Holder<ArmorMaterial> material, int maxDamageFactor, int tinkerableTier, int slots) {
        super(material, Type.BOOTS, maxDamageFactor, p -> p.component(ModDataComponentTypes.EQUIPPED_AUGMENTS, new ArrayList<>(slots)));
        this.tinkerableTier = tinkerableTier;
        this.slots = slots;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected) {
        if (slot == 39 && entity instanceof Player player) {
            for (var augment : AugmentUtils.getAugments(stack)) {
                augment.onArmorTick(stack, level, player);
            }
        }

        for (var augment : AugmentUtils.getAugments(stack)) {
            augment.onInventoryTick(stack, level, entity, slot, isSelected);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(ModTooltips.getTooltipForTier(this.tinkerableTier));

        if (ModConfigs.AWAKENED_SUPREMIUM_SET_BONUS.get() && stack.is(ModItems.AWAKENED_SUPREMIUM_BOOTS.get())) {
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
