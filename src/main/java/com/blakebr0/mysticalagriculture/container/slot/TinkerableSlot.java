package com.blakebr0.mysticalagriculture.container.slot;

import com.blakebr0.mysticalagriculture.api.util.AugmentUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class TinkerableSlot extends SlotItemHandler {
    private final AbstractContainerMenu container;

    public TinkerableSlot(AbstractContainerMenu container, IItemHandler inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.container = container;
    }

    @Override
    public void onTake(Player player, ItemStack stack) {
        for (int i = 0; i < 2; i++) {
            this.getItemHandler().extractItem(i + 1, 1, false);
        }
    }

    @Override
    public void set(ItemStack stack) {
        for (int i = 0; i < 2; i++) {
            var augmentStack = this.getItemHandler().getStackInSlot(i + 1);
            if (!augmentStack.isEmpty()) {
                this.getItemHandler().extractItem(i + 1, augmentStack.getMaxStackSize(), false);
            }

            var augment = AugmentUtils.getAugment(stack, i);
            if (augment != null) {
                this.getItemHandler().insertItem(i + 1, new ItemStack(augment.getItem()), false);
            }
        }

        super.set(stack);
        this.container.slotsChanged(null);
    }
}
