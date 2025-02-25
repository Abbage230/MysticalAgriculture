package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.item.BaseItem;
import com.blakebr0.cucumber.lib.Tooltips;
import com.blakebr0.cucumber.util.Formatting;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import com.blakebr0.mysticalagriculture.util.IUpgradeableMachine;
import com.blakebr0.mysticalagriculture.util.MachineUpgradeTier;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;

import java.util.List;

public class MachineUpgradeItem extends BaseItem {
    private final MachineUpgradeTier tier;

    public MachineUpgradeItem(MachineUpgradeTier tier) {
        super();
        this.tier = tier;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();
        var tile = level.getBlockEntity(pos);

        if (tile instanceof IUpgradeableMachine machine && machine.canApplyUpgrade(this.tier)) {
            var stack = context.getItemInHand();
            var remaining = machine.applyUpgrade(this);

            stack.shrink(1);

            if (!remaining.isEmpty()) {
                var item = new ItemEntity(level, pos.getX(), pos.getY() + 1, pos.getZ(), remaining.copy());

                level.addFreshEntity(item);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            var speed = Formatting.number(1 / this.tier.getOperationTimeMultiplier()).withStyle(this.tier.getTextColor());
            var fuelRate = Formatting.number(this.tier.getFuelUsageMultiplier()).withStyle(this.tier.getTextColor());
            var fuelCapacity = Formatting.number(this.tier.getFuelCapacityMultiplier()).withStyle(this.tier.getTextColor());
            var area = Formatting.number(this.tier.getAddedRange()).withStyle(this.tier.getTextColor());

            tooltip.add(ModTooltips.UPGRADE_SPEED.args(speed).build());
            tooltip.add(ModTooltips.UPGRADE_FUEL_RATE.args(fuelRate).build());
            tooltip.add(ModTooltips.UPGRADE_FUEL_CAPACITY.args(fuelCapacity).build());
            tooltip.add(ModTooltips.UPGRADE_AREA.args(area).build());
        } else {
            tooltip.add(Tooltips.HOLD_SHIFT_FOR_INFO.build());
        }
    }

    public MachineUpgradeTier getTier() {
        return this.tier;
    }
}
