package com.blakebr0.mysticalagriculture.item;

import com.blakebr0.cucumber.util.Localizable;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.ICropProvider;
import com.blakebr0.mysticalagriculture.lib.ModTooltips;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class MysticalSeedsItem extends ItemNameBlockItem implements ICropProvider {
    private final Crop crop;

    public MysticalSeedsItem(Crop crop) {
        super(crop.getCropBlock(), new Properties());
        this.crop = crop;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).build();
    }

    @Override
    public Component getDescription() {
        return this.getName(ItemStack.EMPTY);
    }

    @Override
    public String getDescriptionId() {
        return Localizable.of("item.mysticalagriculture.mystical_seeds").args(this.crop.getDisplayName()).buildString();
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.crop.hasEffect(stack) || super.isFoil(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
        var tier = this.crop.getTier().getDisplayName();

        tooltip.add(ModTooltips.TIER.args(tier).build());

        if (!this.crop.getModId().equals(MysticalAgriculture.MOD_ID)) {
            tooltip.add(ModTooltips.getAddedByTooltip(this.crop.getModId()));
        }

        var biomes = this.crop.getRequiredBiomes();

        if (!biomes.isEmpty()) {
            tooltip.add(ModTooltips.REQUIRED_BIOMES.build());

            var ids = biomes.stream()
                    .map(ResourceLocation::toString)
                    .map(s -> " - " + s)
                    .map(Component::literal)
                    .toList();

            tooltip.addAll(ids);
        }

        if (flag.isAdvanced()) {
            tooltip.add(ModTooltips.CROP_ID.args(this.crop.getId().toString()).color(ChatFormatting.DARK_GRAY).build());
        }
    }

    @Override
    public Crop getCrop() {
        return this.crop;
    }
}
