package com.signifier.siedeflora.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SoilBlockEntity extends BlockEntity
{
    public SoilBlockEntity(BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }
}
