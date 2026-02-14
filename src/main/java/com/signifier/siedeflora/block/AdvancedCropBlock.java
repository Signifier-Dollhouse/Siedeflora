package com.signifier.siedeflora.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class AdvancedCropBlock extends CropBlock
{
    public static final MapCodec<AdvancedCropBlock> CODEC = simpleCodec(AdvancedCropBlock::new);
    private final int maxAge = 7;

    public AdvancedCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // age and other states
        super.createBlockStateDefinition(builder);
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    public int getMaxAge() {
        return this.maxAge;
    }
}
