package com.signifier.siedeflora.block.entity;

import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SoilBlockEntity extends BlockEntity
{
    public SoilBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockRegistry.SOIL_BETYPE.get(), pos, blockState);
    }


    public static void serverTick(Level level, BlockPos pos, BlockState state, SoilBlockEntity blockEntity) {

    }
}
