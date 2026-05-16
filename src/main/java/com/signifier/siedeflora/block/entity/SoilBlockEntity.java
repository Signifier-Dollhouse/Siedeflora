package com.signifier.siedeflora.block.entity;

import com.signifier.siedeflora.agriculture.crop.CropState;
import com.signifier.siedeflora.agriculture.soil.SoilState;
import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class SoilBlockEntity extends BlockEntity
{
    public SoilState soilState = new SoilState();
    public CropState cropState = new CropState();

    public SoilBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockRegistry.SOIL_BETYPE.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
    }
}
