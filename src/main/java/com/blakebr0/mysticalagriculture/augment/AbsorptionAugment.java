package com.blakebr0.mysticalagriculture.augment;

import com.blakebr0.cucumber.helper.ColorHelper;
import com.blakebr0.mysticalagriculture.api.tinkering.Augment;
import com.blakebr0.mysticalagriculture.api.tinkering.AugmentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class AbsorptionAugment extends Augment {
    private static final String PERSISTENT_DATA_KEY = "MA_AbsorptionAugment";
    private final int amplifier;

    public AbsorptionAugment(ResourceLocation id, int tier, int amplifier) {
        super(id, tier, EnumSet.of(AugmentType.ARMOR), getColor(0x7E95A5, tier), getColor(0x3AAAC4, tier));
        this.amplifier = amplifier;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (level.isClientSide() || player.hasEffect(MobEffects.ABSORPTION))
            return;

        if (getLastApplicationTime(player) + 9600L < level.getGameTime()) {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 9600, this.amplifier, true, false));
            setLastApplicationTime(player, level.getGameTime());
        }
    }

    private static int getColor(int color, int tier) {
        return ColorHelper.saturate(color, Math.min((float) tier / 5, 1));
    }

    private static long getLastApplicationTime(Player player) {
        return player.getPersistentData().getLong(PERSISTENT_DATA_KEY);
    }

    private static void setLastApplicationTime(Player player, long time) {
        player.getPersistentData().putLong(PERSISTENT_DATA_KEY, time);
    }
}
