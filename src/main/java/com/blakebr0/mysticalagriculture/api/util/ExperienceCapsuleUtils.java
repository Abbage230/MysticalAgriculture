package com.blakebr0.mysticalagriculture.api.util;

import com.blakebr0.mysticalagriculture.api.MysticalAgricultureDataComponentTypes;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ExperienceCapsuleUtils {
    /**
     * The maximum amount of xp points an experience capsule can hold
     */
    public static final int MAX_XP_POINTS = 1200;

    /**
     * Creates a {@link DataComponentMap} with the necessary components for the experience capsule with this amount of xp
     *
     * @param xp the amount of xp points
     * @return a tag compound for the specified amount of xp
     */
    public static DataComponentMap makeComponentMap(int xp) {
        return DataComponentMap.builder().set(MysticalAgricultureDataComponentTypes.EXPERIENCE_CAPSULE, Math.min(MAX_XP_POINTS, xp)).build();
    }

    /**
     * Get a new experience capsule with the specified amount of xp
     *
     * @param xp   the amount of xp points
     * @param item the experience capsule item
     * @return the experience capsule
     */
    public static ItemStack getExperienceCapsule(int xp, Item item) {
        var stack = new ItemStack(item);
        stack.set(MysticalAgricultureDataComponentTypes.EXPERIENCE_CAPSULE, xp);
        return stack;
    }

    /**
     * Gets the amount of experience currently stored in the specified item stack
     *
     * @param stack the item stack
     * @return the amount of experience
     */
    public static int getExperience(ItemStack stack) {
        var component = stack.get(MysticalAgricultureDataComponentTypes.EXPERIENCE_CAPSULE);

        if (component != null)
            return component;

        return 0;
    }

    /**
     * Add experience to an experience capsule
     *
     * @param stack  the experience capsule stack
     * @param amount the amount of experience to add
     * @return any experience that wasn't added
     */
    public static int addExperienceToCapsule(ItemStack stack, int amount) {
        int xp = getExperience(stack);
        if (xp >= MAX_XP_POINTS) {
            return amount;
        } else {
            int newAmount = Math.min(MAX_XP_POINTS, xp + amount);
            stack.set(MysticalAgricultureDataComponentTypes.EXPERIENCE_CAPSULE, newAmount);
            return Math.max(0, amount - (newAmount - xp));
        }
    }

    /**
     * Remove experience from an experience capsule
     *
     * @param stack  the experience capsule stack
     * @param amount the amount of experience to remove
     * @return any experience that wasn't removed
     */
    public static int removeExperienceFromCapsule(ItemStack stack, int amount) {
        int xp = getExperience(stack);
        int newAmount = Math.max(0, xp - amount);
        stack.set(MysticalAgricultureDataComponentTypes.EXPERIENCE_CAPSULE, newAmount);
        return Math.max(0, amount - xp);
    }
}
