package com.signifier.siedeflora.agriculture.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.signifier.siedeflora.block.entity.SoilBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class CropRenderer implements BlockEntityRenderer<SoilBlockEntity, CropRenderState>
{
    public CropRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public CropRenderState createRenderState() {
        return new CropRenderState();
    }

    @Override
    public void extractRenderState(
            SoilBlockEntity blockEntity,
            CropRenderState state,
            float partialTicks,
            Vec3 cameraPosition,
            ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
    }

    @Override
    public void submit(
            CropRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {

    }
}
