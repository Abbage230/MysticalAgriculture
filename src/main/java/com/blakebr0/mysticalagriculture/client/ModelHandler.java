package com.blakebr0.mysticalagriculture.client;

import com.blakebr0.cucumber.model.RetextureableBlockModelWrapper;
import com.blakebr0.cucumber.model.RetextureableItemModelWrapper;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.api.MysticalAgricultureAPI;
import com.blakebr0.mysticalagriculture.api.crop.CropTextures;
import com.blakebr0.mysticalagriculture.block.ModBlocks;
import com.blakebr0.mysticalagriculture.registry.CropRegistry;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.CropsBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BlockModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemModelGenerator;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.ModelRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ModelHandler {
    private static final Logger LOGGER = LogManager.getLogger(MysticalAgriculture.NAME);

    private static final ResourceLocation MISSING_NO = new ResourceLocation("minecraft", "missingno");
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("minecraft", "textures/atlas/blocks.png");

    @SubscribeEvent
    public void onRegisterModels(ModelRegistryEvent event) {
        for (int i = 0; i < 8; i++) {
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_resource_crop_" + i));
            ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "block/mystical_mob_crop_" + i));
        }

        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        ModelLoader.addSpecialModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        ModelBakery bakery = event.getModelLoader();

        Map<String, IBakedModel[]> cropModels = new HashMap<>();
        Map<String, RetextureableBlockModelWrapper> cropModelsGrown = new HashMap<>();
        MysticalAgricultureAPI.CROP_TYPES.forEach(type -> {
            cropModels.put(type.getName(), IntStream.range(0, 7)
                    .mapToObj(i -> registry.get(new ResourceLocation(type.getStemModel() + "_" + i)))
                    .toArray(IBakedModel[]::new));

            IUnbakedModel model = bakery.getUnbakedModel(new ResourceLocation(type.getStemModel() + "_7"));
            RetextureableBlockModelWrapper modelWrapper = new RetextureableBlockModelWrapper((BlockModel) model);
            cropModelsGrown.put(type.getName(), modelWrapper);
        });

        Function<Material, TextureAtlasSprite> getSprite = bakery.getSpriteMap()::getSprite;
        ItemModelGenerator generator = new ItemModelGenerator();

        IUnbakedModel essenceModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_essence"));
        RetextureableItemModelWrapper essenceModelWrapper = new RetextureableItemModelWrapper((BlockModel) essenceModel);
        IUnbakedModel seedsModel = bakery.getUnbakedModel(new ResourceLocation(MysticalAgriculture.MOD_ID, "item/mystical_seeds"));
        RetextureableItemModelWrapper seedsModelWrapper = new RetextureableItemModelWrapper((BlockModel) seedsModel);

        Stopwatch stopwatch = Stopwatch.createStarted();

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            CropTextures textures = crop.getTextures();

            CropsBlock crops = crop.getCrop();
            if (crops.getRegistryName() != null) {
                for (int i = 0; i < 7; i++) {
                    ModelResourceLocation location = new ModelResourceLocation(crops.getRegistryName(), "age=" + i);
                    IBakedModel bakedModel = registry.get(location);
                    if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                        String type = crop.getType().getName();
                        registry.replace(location, cropModels.get(type)[i]);
                    }
                }

                ModelResourceLocation location = new ModelResourceLocation(crops.getRegistryName(), "age=7");
                IBakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = crop.getTextures().getFlowerTexture();
                    IUnbakedModel cropRetexturedModel = cropModelsGrown.get(crop.getType().getName()).retexture(ImmutableMap.of("flower", texture.toString()));
                    IBakedModel cropBakedModel = cropRetexturedModel.bake(bakery, getSprite, ModelRotation.X0_Y0, location);
                    registry.replace(location, cropBakedModel);
                }
            }

            Item essence = crop.getEssence();
            if (essence.getRegistryName() != null) {
                ModelResourceLocation location = new ModelResourceLocation(essence.getRegistryName(), "inventory");
                IBakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = textures.getEssenceTexture();
                    RetextureableBlockModelWrapper retexture = essenceModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    IUnbakedModel generated = generator.makeItemModel(getSprite, retexture);
                    IBakedModel model = generated.bake(bakery, getSprite, ModelRotation.X0_Y0, location);
                    registry.replace(location, model);
                }
            }

            BlockNamedItem seeds = crop.getSeeds();
            if (seeds.getRegistryName() != null) {
                ModelResourceLocation location = new ModelResourceLocation(seeds.getRegistryName(), "inventory");
                IBakedModel bakedModel = registry.get(location);
                if (bakedModel == null || bakedModel.getParticleTexture(EmptyModelData.INSTANCE).getName().equals(MISSING_NO)) {
                    ResourceLocation texture = textures.getSeedTexture();
                    RetextureableBlockModelWrapper retexture = seedsModelWrapper.retexture(ImmutableMap.of("layer0", texture.toString()));
                    IUnbakedModel generated = generator.makeItemModel(getSprite, retexture);
                    IBakedModel model = generated.bake(bakery, getSprite, ModelRotation.X0_Y0, location);
                    registry.replace(location, model);
                }
            }
        });

        stopwatch.stop();
        LOGGER.info("Model replacement took {} milliseconds.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @SubscribeEvent
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().getId().equals(BLOCK_ATLAS)) {
            CropRegistry.getInstance().getCrops().forEach(crop -> {
                CropTextures textures = crop.getTextures();

                event.addSprite(textures.getFlowerTexture());
                event.addSprite(textures.getEssenceTexture());
                event.addSprite(textures.getSeedTexture());
            });
        }
    }

    public static void onClientSetup() {
        RenderTypeLookup.setRenderLayer(ModBlocks.PROSPERITY_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.INFERIUM_ORE.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOULIUM_ORE.get(), RenderType.getCutoutMipped());

        CropRegistry.getInstance().getCrops().forEach(crop -> {
            RenderTypeLookup.setRenderLayer(crop.getCrop(), RenderType.getCutoutMipped());
        });
    }
}
