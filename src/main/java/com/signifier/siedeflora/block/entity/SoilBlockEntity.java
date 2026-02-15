package com.signifier.siedeflora.block.entity;

import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SoilBlockEntity extends BlockEntity
{
    public static final int MAX_NUTRITION = 100;
    public static final int MAX_MOISTURE = 300;
    public static final int MAX_LOOSENESS = 100;
    public static final int MAX_TEMPERATURE = 50;
    public static final int MAX_TOXICITY = 100;

    private int nutrition = 60;
    private int moisture = 40;
    private int looseness = 60;
    private int temperature = 15;
    private int toxicity = 20;

    public SoilBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockRegistry.SOIL_BETYPE.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("nutrition", nutrition);
        tag.putInt("moisture", moisture);
        tag.putInt("looseness", looseness);
        tag.putInt("temperature", temperature);
        tag.putInt("toxicity", toxicity);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.nutrition = tag.getInt("nutrition");
        this.moisture = tag.getInt("moisture");
        this.looseness = tag.getInt("looseness");
        this.temperature = tag.getInt("temperature");
        this.toxicity = tag.getInt("toxicity");
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SoilBlockEntity blockEntity) {
        // TODO update our soil properties here
    }
}
