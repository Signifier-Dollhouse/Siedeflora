package com.signifier.siedeflora.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.Nullable;

public class SteamerTileBlock extends BaseEntityBlock
{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected SteamerTileBlock(Properties properties) {
        super(properties);
    }




    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }
}
