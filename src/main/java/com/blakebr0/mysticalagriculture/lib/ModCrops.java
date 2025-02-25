package com.blakebr0.mysticalagriculture.lib;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.crop.Crop;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.api.crop.CropTier;
import com.blakebr0.mysticalagriculture.api.crop.CropType;
import com.blakebr0.mysticalagriculture.api.lib.LazyIngredient;
import com.blakebr0.mysticalagriculture.api.registry.ICropRegistry;
import com.blakebr0.mysticalagriculture.api.util.ExperienceCapsuleUtils;
import com.blakebr0.mysticalagriculture.api.util.MobSoulUtils;
import com.blakebr0.mysticalagriculture.init.ModBlocks;
import com.blakebr0.mysticalagriculture.init.ModItems;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.Arrays;

public final class ModCrops {
    private static final boolean DEBUG = !FMLEnvironment.production;

    public static final Crop AIR = new Crop(MysticalAgriculture.resource("air"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0xDAD64D, LazyIngredient.item("mysticalagriculture:air_agglomeratio"));
    public static final Crop EARTH = new Crop(MysticalAgriculture.resource("earth"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0x54DA4D, LazyIngredient.item("mysticalagriculture:earth_agglomeratio"));
    public static final Crop WATER = new Crop(MysticalAgriculture.resource("water"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0x4D7EDA, LazyIngredient.item("mysticalagriculture:water_agglomeratio"));
    public static final Crop FIRE = new Crop(MysticalAgriculture.resource("fire"), CropTier.ELEMENTAL, CropType.RESOURCE, CropTextures.ELEMENTAL_CROP_TEXTURES, 0xDA4D4D, LazyIngredient.item("mysticalagriculture:fire_agglomeratio"));

    public static final Crop INFERIUM = new Crop(MysticalAgriculture.resource("inferium"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:inferium_essence"));
    public static final Crop STONE = new Crop(MysticalAgriculture.resource("stone"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:stone"));
    public static final Crop DIRT = new Crop(MysticalAgriculture.resource("dirt"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:dirt"));
    public static final Crop WOOD = new Crop(MysticalAgriculture.resource("wood"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.tag("minecraft:logs"));
    public static final Crop ICE = new Crop(MysticalAgriculture.resource("ice"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:ice"));
    public static final Crop DEEPSLATE = new Crop(MysticalAgriculture.resource("deepslate"), CropTier.ONE, CropType.RESOURCE, LazyIngredient.item("minecraft:deepslate"));

    public static final Crop NATURE = new Crop(MysticalAgriculture.resource("nature"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:nature_agglomeratio"));
    public static final Crop DYE = new Crop(MysticalAgriculture.resource("dye"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:dye_agglomeratio"));
    public static final Crop NETHER = new Crop(MysticalAgriculture.resource("nether"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:nether_agglomeratio"));
    public static final Crop COAL = new Crop(MysticalAgriculture.resource("coal"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("minecraft:coal"));
    public static final Crop CORAL = new Crop(MysticalAgriculture.resource("coral"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:coral_agglomeratio"));
    public static final Crop HONEY = new Crop(MysticalAgriculture.resource("honey"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:honey_agglomeratio"));
    public static final Crop AMETHYST = new Crop(MysticalAgriculture.resource("amethyst"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("minecraft:amethyst_shard"));
    public static final Crop PIG = new Crop(MysticalAgriculture.resource("pig"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.PIG)));
    public static final Crop CHICKEN = new Crop(MysticalAgriculture.resource("chicken"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.CHICKEN)));
    public static final Crop COW = new Crop(MysticalAgriculture.resource("cow"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.COW)));
    public static final Crop SHEEP = new Crop(MysticalAgriculture.resource("sheep"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.SHEEP)));
    public static final Crop SQUID = new Crop(MysticalAgriculture.resource("squid"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.SQUID)));
    public static final Crop FISH = new Crop(MysticalAgriculture.resource("fish"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.FISH)));
    public static final Crop SLIME = new Crop(MysticalAgriculture.resource("slime"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.SLIME)));
    public static final Crop TURTLE = new Crop(MysticalAgriculture.resource("turtle"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.TURTLE)));
    public static final Crop ARMADILLO = new Crop(MysticalAgriculture.resource("armadillo"), CropTier.TWO, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.ARMADILLO)));

    public static final Crop IRON = new Crop(MysticalAgriculture.resource("iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/iron"));
    public static final Crop COPPER = new Crop(MysticalAgriculture.resource("copper"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("minecraft:copper_ingot"));
    public static final Crop NETHER_QUARTZ = new Crop(MysticalAgriculture.resource("nether_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:gems/quartz"));
    public static final Crop GLOWSTONE = new Crop(MysticalAgriculture.resource("glowstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:dusts/glowstone"));
    public static final Crop REDSTONE = new Crop(MysticalAgriculture.resource("redstone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:dusts/redstone"));
    public static final Crop OBSIDIAN = new Crop(MysticalAgriculture.resource("obsidian"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("minecraft:obsidian"));
    public static final Crop PRISMARINE = new Crop(MysticalAgriculture.resource("prismarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:prismarine_agglomeratio"));
    public static final Crop ZOMBIE = new Crop(MysticalAgriculture.resource("zombie"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.ZOMBIE)));
    public static final Crop SKELETON = new Crop(MysticalAgriculture.resource("skeleton"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.SKELETON)));
    public static final Crop CREEPER = new Crop(MysticalAgriculture.resource("creeper"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.CREEPER)));
    public static final Crop SPIDER = new Crop(MysticalAgriculture.resource("spider"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.SPIDER)));
    public static final Crop RABBIT = new Crop(MysticalAgriculture.resource("rabbit"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.RABBIT)));

    public static final Crop GOLD = new Crop(MysticalAgriculture.resource("gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/gold"));
    public static final Crop LAPIS_LAZULI = new Crop(MysticalAgriculture.resource("lapis_lazuli"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/lapis"));
    public static final Crop END = new Crop(MysticalAgriculture.resource("end"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:end_agglomeratio"));
    public static final Crop EXPERIENCE = new Crop(MysticalAgriculture.resource("experience"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:experience_capsule", ExperienceCapsuleUtils.makeComponentMap(ExperienceCapsuleUtils.MAX_XP_POINTS)));
    public static final Crop BREEZE = new Crop(MysticalAgriculture.resource("breeze"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.BREEZE)));
    public static final Crop BLAZE = new Crop(MysticalAgriculture.resource("blaze"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.BLAZE)));
    public static final Crop GHAST = new Crop(MysticalAgriculture.resource("ghast"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.GHAST)));
    public static final Crop ENDERMAN = new Crop(MysticalAgriculture.resource("enderman"), CropTier.FOUR, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.ENDERMAN)));

    public static final Crop DIAMOND = new Crop(MysticalAgriculture.resource("diamond"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:gems/diamond"));
    public static final Crop EMERALD = new Crop(MysticalAgriculture.resource("emerald"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:gems/emerald"));
    public static final Crop NETHERITE = new Crop(MysticalAgriculture.resource("netherite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("minecraft:netherite_ingot"));
    public static final Crop WITHER_SKELETON = new Crop(MysticalAgriculture.resource("wither_skeleton"), CropTier.FIVE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.WITHER)));

    // COMMON
    public static final Crop RUBBER = new Crop(MysticalAgriculture.resource("rubber"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("c:rubber"));
    public static final Crop SILICON = new Crop(MysticalAgriculture.resource("silicon"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("c:silicon"));
    public static final Crop SULFUR = new Crop(MysticalAgriculture.resource("sulfur"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("c:dusts/sulfur"));
    public static final Crop ALUMINUM = new Crop(MysticalAgriculture.resource("aluminum"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("c:ingots/aluminum"));
    public static final Crop SALTPETER = new Crop(MysticalAgriculture.resource("saltpeter"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("mysticalagriculture:material/saltpeter"));
    public static final Crop TIN = new Crop(MysticalAgriculture.resource("tin"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/tin"));
    public static final Crop BRONZE = new Crop(MysticalAgriculture.resource("bronze"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/bronze"));
    public static final Crop ZINC = new Crop(MysticalAgriculture.resource("zinc"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/zinc"));
    public static final Crop BRASS = new Crop(MysticalAgriculture.resource("brass"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/brass"));
    public static final Crop SILVER = new Crop(MysticalAgriculture.resource("silver"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/silver"));
    public static final Crop LEAD = new Crop(MysticalAgriculture.resource("lead"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/lead"));
    public static final Crop GRAPHITE = new Crop(MysticalAgriculture.resource("graphite"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/graphite"));
    public static final Crop STEEL = new Crop(MysticalAgriculture.resource("steel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/steel"));
    public static final Crop NICKEL = new Crop(MysticalAgriculture.resource("nickel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/nickel"));
    public static final Crop CONSTANTAN = new Crop(MysticalAgriculture.resource("constantan"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/constantan"));
    public static final Crop ELECTRUM = new Crop(MysticalAgriculture.resource("electrum"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/electrum"));
    public static final Crop INVAR = new Crop(MysticalAgriculture.resource("invar"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/invar"));
    public static final Crop URANIUM = new Crop(MysticalAgriculture.resource("uranium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/uranium"));
    public static final Crop PLATINUM = new Crop(MysticalAgriculture.resource("platinum"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/platinum"));
    public static final Crop IRIDIUM = new Crop(MysticalAgriculture.resource("iridium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/iridium"));

    // GEMS
    public static final Crop APATITE = new Crop(MysticalAgriculture.resource("apatite"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("c:gems/apatite"));
    public static final Crop RUBY = new Crop(MysticalAgriculture.resource("ruby"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/ruby"));
    public static final Crop SAPPHIRE = new Crop(MysticalAgriculture.resource("sapphire"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/sapphire"));
    public static final Crop PERIDOT = new Crop(MysticalAgriculture.resource("peridot"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/peridot"));

    // MYSTICAL AGRICULTURE
    public static final Crop SOULIUM = new Crop(MysticalAgriculture.resource("soulium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:soulium_dust"));

    // THERMAL SERIES
    public static final Crop BLIZZ = new Crop(MysticalAgriculture.resource("blizz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.BLIZZ)));
    public static final Crop BLITZ = new Crop(MysticalAgriculture.resource("blitz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.BLITZ)));
    public static final Crop BASALZ = new Crop(MysticalAgriculture.resource("basalz"), CropTier.THREE, CropType.MOB, LazyIngredient.item("mysticalagriculture:soul_jar", MobSoulUtils.makeComponentMap(ModMobSoulTypes.BASALZ)));
    public static final Crop SIGNALUM = new Crop(MysticalAgriculture.resource("signalum"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/signalum"));
    public static final Crop LUMIUM = new Crop(MysticalAgriculture.resource("lumium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/lumium"));
    public static final Crop ENDERIUM = new Crop(MysticalAgriculture.resource("enderium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/enderium"));

    // REDSTONE ARSENAL
    public static final Crop FLUX_INFUSED_INGOT = new Crop(MysticalAgriculture.resource("flux_infused_ingot"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("redstone_arsenal:flux_ingot"));
    public static final Crop FLUX_INFUSED_GEM = new Crop(MysticalAgriculture.resource("flux_infused_gem"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("redstone_arsenal:flux_gem"));

    // IMMERSIVE ENGINEERING
    public static final Crop HOP_GRAPHITE = new Crop(MysticalAgriculture.resource("hop_graphite"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/hop_graphite"));

    // TINKERS' CONSTRUCT
    public static final Crop AMETHYST_BRONZE = new Crop(MysticalAgriculture.resource("amethyst_bronze"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/amethyst_bronze"));
    public static final Crop SLIMESTEEL = new Crop(MysticalAgriculture.resource("slimesteel"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/slimesteel"));
    public static final Crop PIG_IRON = new Crop(MysticalAgriculture.resource("pig_iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/pig_iron"));
    public static final Crop COBALT = new Crop(MysticalAgriculture.resource("cobalt"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/cobalt"));
    public static final Crop ROSE_GOLD = new Crop(MysticalAgriculture.resource("rose_gold"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/rose_gold"));
    public static final Crop MANYULLYN = new Crop(MysticalAgriculture.resource("manyullyn"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/manyullyn"));
    public static final Crop QUEENS_SLIME = new Crop(MysticalAgriculture.resource("queens_slime"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/queens_slime"));
    public static final Crop HEPATIZON = new Crop(MysticalAgriculture.resource("hepatizon"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/hepatizon"));

    // ENDER IO
    public static final Crop GRAINS_OF_INFINITY = new Crop(MysticalAgriculture.resource("grains_of_infinity"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("enderio:grains_of_infinity"));
    public static final Crop COPPER_ALLOY = new Crop(MysticalAgriculture.resource("copper_alloy"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("enderio:copper_alloy_ingot"));
    public static final Crop REDSTONE_ALLOY = new Crop(MysticalAgriculture.resource("redstone_alloy"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("enderio:redstone_alloy_ingot"));
    public static final Crop CONDUCTIVE_ALLOY = new Crop(MysticalAgriculture.resource("conductive_alloy"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("enderio:conductive_alloy_ingot"));
    public static final Crop SOULARIUM = new Crop(MysticalAgriculture.resource("soularium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("enderio:soularium_ingot"));
    public static final Crop DARK_STEEL = new Crop(MysticalAgriculture.resource("dark_steel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("enderio:dark_steel_ingot"));
    public static final Crop PULSATING_ALLOY = new Crop(MysticalAgriculture.resource("pulsating_alloy"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("enderio:pulsating_alloy_ingot"));
    public static final Crop ENERGETIC_ALLOY = new Crop(MysticalAgriculture.resource("energetic_alloy"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("enderio:energetic_alloy_ingot"));
    public static final Crop VIBRANT_ALLOY = new Crop(MysticalAgriculture.resource("vibrant_alloy"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("enderio:vibrant_alloy_ingot"));
    public static final Crop END_STEEL = new Crop(MysticalAgriculture.resource("end_steel"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("enderio:end_steel_ingot"));

    // BOTANIA
    public static final Crop MYSTICAL_FLOWER = new Crop(MysticalAgriculture.resource("mystical_flower"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("mysticalagriculture:mystical_flower_agglomeratio"));
    public static final Crop MANASTEEL = new Crop(MysticalAgriculture.resource("manasteel"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("botania:manasteel_ingot"));
    public static final Crop ELEMENTIUM = new Crop(MysticalAgriculture.resource("elementium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("botania:elementium_ingot"));
    public static final Crop TERRASTEEL = new Crop(MysticalAgriculture.resource("terrasteel"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("botania:terrasteel_ingot"));

    // MEKANISM
    public static final Crop OSMIUM = new Crop(MysticalAgriculture.resource("osmium"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/osmium"));
    public static final Crop FLUORITE = new Crop(MysticalAgriculture.resource("fluorite"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/fluorite"));
    public static final Crop REFINED_GLOWSTONE = new Crop(MysticalAgriculture.resource("refined_glowstone"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/refined_glowstone"));
    public static final Crop REFINED_OBSIDIAN = new Crop(MysticalAgriculture.resource("refined_obsidian"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/refined_obsidian"));

    // CHISEL
    public static final Crop MARBLE = new Crop(MysticalAgriculture.resource("marble"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("mysticalagriculture:material/marble"));
    public static final Crop LIMESTONE = new Crop(MysticalAgriculture.resource("limestone"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("mysticalagriculture:material/limestone"));
    public static final Crop BASALT = new Crop(MysticalAgriculture.resource("basalt"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.tag("chisel:basalt"));

    // TWILIGHT FOREST
    public static final Crop STEELEAF = new Crop(MysticalAgriculture.resource("steeleaf"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("twilightforest:steeleaf_ingot"));
    public static final Crop IRONWOOD = new Crop(MysticalAgriculture.resource("ironwood"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("twilightforest:ironwood_ingot"));
    public static final Crop KNIGHTMETAL = new Crop(MysticalAgriculture.resource("knightmetal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("twilightforest:knightmetal_ingot"));
    public static final Crop FIERY_INGOT = new Crop(MysticalAgriculture.resource("fiery_ingot"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("twilightforest:fiery_ingot"));

    // INTEGRATED DYNAMICS
    public static final Crop MENRIL = new Crop(MysticalAgriculture.resource("menril"), CropTier.TWO, CropType.RESOURCE, LazyIngredient.item("integrateddynamics:menril_berries"));

    // ASTRAL SORCERY
    public static final Crop AQUAMARINE = new Crop(MysticalAgriculture.resource("aquamarine"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("astralsorcery:aquamarine"));
    public static final Crop STARMETAL = new Crop(MysticalAgriculture.resource("starmetal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("astralsorcery:starmetal_ingot"));
    public static final Crop ROCK_CRYSTAL = new Crop(MysticalAgriculture.resource("rock_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("astralsorcery:rock_crystal"));

    // PNEUMATICCRAFT
    public static final Crop COMPRESSED_IRON = new Crop(MysticalAgriculture.resource("compressed_iron"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:ingots/compressed_iron"));

    // DRACONIC EVOLUTION
    public static final Crop DRACONIUM = new Crop(MysticalAgriculture.resource("draconium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/draconium"));

    // EXTREME REACTORS 2
    public static final Crop YELLORIUM = new Crop(MysticalAgriculture.resource("yellorium"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/yellorium"));
    public static final Crop CYANITE = new Crop(MysticalAgriculture.resource("cyanite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.tag("c:ingots/cyanite"));

    // APPLIED ENERGISTICS 2
    public static final Crop SKY_STONE = new Crop(MysticalAgriculture.resource("sky_stone"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("ae2:sky_stone_block"));
    public static final Crop CERTUS_QUARTZ = new Crop(MysticalAgriculture.resource("certus_quartz"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.tag("c:gems/certus_quartz"));
    public static final Crop FLUIX = new Crop(MysticalAgriculture.resource("fluix"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.tag("c:gems/fluix"));

    // REFINED STORAGE
    public static final Crop QUARTZ_ENRICHED_IRON = new Crop(MysticalAgriculture.resource("quartz_enriched_iron"), CropTier.THREE, CropType.RESOURCE, LazyIngredient.item("refinedstorage:quartz_enriched_iron"));

    // POWAH
    public static final Crop ENERGIZED_STEEL = new Crop(MysticalAgriculture.resource("energized_steel"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("powah:steel_energized"));
    public static final Crop BLAZING_CRYSTAL = new Crop(MysticalAgriculture.resource("blazing_crystal"), CropTier.FOUR, CropType.RESOURCE, LazyIngredient.item("powah:crystal_blazing"));
    public static final Crop NIOTIC_CRYSTAL = new Crop(MysticalAgriculture.resource("niotic_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:crystal_niotic"));
    public static final Crop SPIRITED_CRYSTAL = new Crop(MysticalAgriculture.resource("spirited_crystal"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:crystal_spirited"));
    public static final Crop URANINITE = new Crop(MysticalAgriculture.resource("uraninite"), CropTier.FIVE, CropType.RESOURCE, LazyIngredient.item("powah:uraninite"));

    public static void onRegisterCrops(ICropRegistry registry) {
        INFERIUM.getTextures().setEssenceTexture(MysticalAgriculture.resource("item/inferium_essence"));
        INFERIUM.getRecipeConfig().setSeedCraftingRecipeEnabled(false).setSeedInfusionRecipeEnabled(false);
        INFERIUM.setCropBlock(() -> (CropBlock) ModBlocks.INFERIUM_CROP.get())
                .setEssenceItem(ModItems.INFERIUM_ESSENCE);

        registry.register(AIR);
        registry.register(EARTH);
        registry.register(WATER);
        registry.register(FIRE);

        registry.register(INFERIUM);
        registry.register(STONE);
        registry.register(DIRT);
        registry.register(WOOD);
        registry.register(ICE);
        registry.register(DEEPSLATE);

        registry.register(NATURE);
        registry.register(DYE);
        registry.register(NETHER);
        registry.register(COAL);
        registry.register(CORAL);
        registry.register(HONEY);
        registry.register(AMETHYST);
        registry.register(PIG);
        registry.register(CHICKEN);
        registry.register(COW);
        registry.register(SHEEP);
        registry.register(SQUID);
        registry.register(FISH);
        registry.register(SLIME);
        registry.register(TURTLE);
        registry.register(ARMADILLO);

        registry.register(IRON);
        registry.register(COPPER);
        registry.register(NETHER_QUARTZ);
        registry.register(GLOWSTONE);
        registry.register(REDSTONE);
        registry.register(OBSIDIAN);
        registry.register(PRISMARINE);
        registry.register(ZOMBIE);
        registry.register(SKELETON);
        registry.register(CREEPER);
        registry.register(SPIDER);
        registry.register(RABBIT);

        registry.register(GOLD);
        registry.register(LAPIS_LAZULI);
        registry.register(END);
        registry.register(EXPERIENCE);
        registry.register(BREEZE);
        registry.register(BLAZE);
        registry.register(GHAST);
        registry.register(ENDERMAN);

        registry.register(DIAMOND);
        registry.register(EMERALD);
        registry.register(NETHERITE);
        registry.register(WITHER_SKELETON);

        // COMMON
        registry.register(RUBBER);
        registry.register(SILICON);
        registry.register(SULFUR);
        registry.register(ALUMINUM);
        registry.register(SALTPETER);
        registry.register(TIN);
        registry.register(BRONZE);
        registry.register(ZINC);
        registry.register(BRASS);
        registry.register(SILVER);
        registry.register(LEAD);
        registry.register(GRAPHITE);
        registry.register(STEEL);
        registry.register(NICKEL);
        registry.register(CONSTANTAN);
        registry.register(ELECTRUM);
        registry.register(INVAR);
        registry.register(URANIUM);
        registry.register(PLATINUM);
        registry.register(IRIDIUM);

        // GEMS
        registry.register(APATITE);
        registry.register(RUBY);
        registry.register(SAPPHIRE);
        registry.register(PERIDOT);

        // MYSTICAL AGRICULTURE
        registry.register(SOULIUM);

        // THERMAL SERIES
        registry.register(withRequiredMods(BLIZZ, "thermal"));
        registry.register(withRequiredMods(BLITZ, "thermal"));
        registry.register(withRequiredMods(BASALZ, "thermal"));
        registry.register(withRequiredMods(SIGNALUM, "thermal"));
        registry.register(withRequiredMods(LUMIUM, "thermal"));
        registry.register(withRequiredMods(ENDERIUM, "thermal"));

        registry.register(withRequiredMods(FLUX_INFUSED_INGOT, "redstone_arsenal"));
        registry.register(withRequiredMods(FLUX_INFUSED_GEM, "redstone_arsenal"));

        // IMMERSIVE ENGINEERING
        registry.register(withRequiredMods(HOP_GRAPHITE, "immersiveengineering"));

        // TINKERS' CONSTRUCT
        registry.register(withRequiredMods(AMETHYST_BRONZE, "tconstruct"));
        registry.register(withRequiredMods(SLIMESTEEL, "tconstruct"));
        registry.register(withRequiredMods(PIG_IRON, "tconstruct"));
        registry.register(withRequiredMods(COBALT, "tconstruct"));
        registry.register(withRequiredMods(ROSE_GOLD, "tconstruct"));
        registry.register(withRequiredMods(MANYULLYN, "tconstruct"));
        registry.register(withRequiredMods(QUEENS_SLIME, "tconstruct"));
        registry.register(withRequiredMods(HEPATIZON, "tconstruct"));

        // ENDER IO
        registry.register(withRequiredMods(GRAINS_OF_INFINITY, "enderio"));
        registry.register(withRequiredMods(COPPER_ALLOY, "enderio"));
        registry.register(withRequiredMods(REDSTONE_ALLOY, "enderio"));
        registry.register(withRequiredMods(CONDUCTIVE_ALLOY, "enderio"));
        registry.register(withRequiredMods(SOULARIUM, "enderio"));
        registry.register(withRequiredMods(DARK_STEEL, "enderio"));
        registry.register(withRequiredMods(PULSATING_ALLOY, "enderio"));
        registry.register(withRequiredMods(ENERGETIC_ALLOY, "enderio"));
        registry.register(withRequiredMods(VIBRANT_ALLOY, "enderio"));
        registry.register(withRequiredMods(END_STEEL, "enderio"));

        // BOTANIA
        registry.register(withRequiredMods(MYSTICAL_FLOWER, "botania"));
        registry.register(withRequiredMods(MANASTEEL, "botania"));
        registry.register(withRequiredMods(ELEMENTIUM, "botania"));
        registry.register(withRequiredMods(TERRASTEEL, "botania"));

        // MEKANISM
        registry.register(withRequiredMods(OSMIUM, "mekanism"));
        registry.register(withRequiredMods(FLUORITE, "mekanism"));
        registry.register(withRequiredMods(REFINED_GLOWSTONE, "mekanism"));
        registry.register(withRequiredMods(REFINED_OBSIDIAN, "mekanism"));

        // CHISEL
        registry.register(withRequiredMods(MARBLE, "chisel", "astralsorcery"));
        registry.register(withRequiredMods(LIMESTONE, "chisel", "create"));
        registry.register(withRequiredMods(BASALT, "chisel"));

        // TWILIGHT FOREST
        registry.register(withRequiredMods(STEELEAF, "twilightforest"));
        registry.register(withRequiredMods(IRONWOOD, "twilightforest"));
        registry.register(withRequiredMods(KNIGHTMETAL, "twilightforest"));
        registry.register(withRequiredMods(FIERY_INGOT, "twilightforest"));

        // INTEGRATED DYNAMICS
        registry.register(withRequiredMods(MENRIL, "integrateddynamics"));

        // ASTRAL SORCERY
        registry.register(withRequiredMods(AQUAMARINE, "astralsorcery"));
        registry.register(withRequiredMods(STARMETAL, "astralsorcery"));
        registry.register(withRequiredMods(ROCK_CRYSTAL, "astralsorcery"));

        // PNEUMATICCRAFT
        registry.register(withRequiredMods(COMPRESSED_IRON, "pneumaticcraft"));

        // DRACONIC EVOLUTION
        registry.register(withRequiredMods(DRACONIUM, "draconicevolution"));

        // EXTREME REACTORS 2
        registry.register(withRequiredMods(YELLORIUM, "bigreactors"));
        registry.register(withRequiredMods(CYANITE, "bigreactors"));

        // APPLIED ENERGISTICS 2
        registry.register(withRequiredMods(SKY_STONE, "ae2"));
        registry.register(withRequiredMods(CERTUS_QUARTZ, "ae2"));
        registry.register(withRequiredMods(FLUIX, "ae2"));

        // REFINED STORAGE
        registry.register(withRequiredMods(QUARTZ_ENRICHED_IRON, "refinedstorage"));

        // POWAH
        registry.register(withRequiredMods(ENERGIZED_STEEL, "powah"));
        registry.register(withRequiredMods(BLAZING_CRYSTAL, "powah"));
        registry.register(withRequiredMods(NIOTIC_CRYSTAL, "powah"));
        registry.register(withRequiredMods(SPIRITED_CRYSTAL, "powah"));
        registry.register(withRequiredMods(URANINITE, "powah"));
    }

    private static Crop withRequiredMods(Crop crop, String... mods) {
        if (DEBUG) return crop;
        
        boolean enabled = Arrays.stream(mods).anyMatch(ModList.get()::isLoaded);
        return crop.setEnabled(enabled);
    }
}
