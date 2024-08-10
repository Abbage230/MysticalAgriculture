package com.blakebr0.mysticalagriculture.init;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.crafting.ingredient.CropComponentIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.FilledSoulJarIngredient;
import com.blakebr0.mysticalagriculture.crafting.ingredient.HoeIngredient;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModIngredientTypes {
    public static final DeferredRegister<IngredientType<?>> REGISTRY = DeferredRegister.create(NeoForgeRegistries.INGREDIENT_TYPES, MysticalAgriculture.MOD_ID);

    public static final DeferredHolder<IngredientType<?>, IngredientType<HoeIngredient>> ALL_HOES = REGISTRY.register("all_hoes", () -> new IngredientType<>(HoeIngredient.CODEC));
    public static final DeferredHolder<IngredientType<?>, IngredientType<FilledSoulJarIngredient>> FILLED_SOUL_JAR = REGISTRY.register("filled_soul_jar", () -> new IngredientType<>(FilledSoulJarIngredient.CODEC));
    public static final DeferredHolder<IngredientType<?>, IngredientType<CropComponentIngredient>> CROP_COMPONENT = REGISTRY.register("crop_component", () -> new IngredientType<>(CropComponentIngredient.CODEC));
}
