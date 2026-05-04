package com.signifier.siedeflora.block.entity;

import com.signifier.siedeflora.block.BlockRegistry;
import com.signifier.siedeflora.block.soil.NutritionLevel;
import net.minecraft.core.BlockPos;
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

    private NutritionLevel nitro = NutritionLevel.MEDIUM;
    private NutritionLevel phos = NutritionLevel.MEDIUM;
    private NutritionLevel pota = NutritionLevel.MEDIUM;
    // 有机质
    private int organ = 60;
    // 湿润度
    private int moisture = 40;

    // 以下待定
    private int looseness = 60;
    private int temperature = 15;
    private int toxicity = 20;

    public SoilBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockRegistry.SOIL_BETYPE.get(), pos, blockState);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.nitro = NutritionLevel.valueOf(tag.getString("nitro"));
        this.phos = NutritionLevel.valueOf(tag.getString("phos"));
        this.pota = NutritionLevel.valueOf(tag.getString("pota"));
        this.organ = tag.getInt("organ");
        this.moisture = tag.getInt("moisture");
        this.looseness = tag.getInt("looseness");
        this.temperature = tag.getInt("temperature");
        this.toxicity = tag.getInt("toxicity");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("nitro", nitro.getSerializedName());
        tag.putString("phos", phos.getSerializedName());
        tag.putString("pota", pota.getSerializedName());
        tag.putInt("organ", organ);
        tag.putInt("moisture", moisture);
        tag.putInt("looseness", looseness);
        tag.putInt("temperature", temperature);
        tag.putInt("toxicity", toxicity);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SoilBlockEntity blockEntity) {
        // TODO update our soil properties here
    }
}
