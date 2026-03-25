package com.signifier.siedeflora.block.entity;

import com.signifier.siedeflora.block.AdvancedCropBlock;
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
        // 30s一次
        if (level.getGameTime() % 600 == 0) {
            // 检查上方是否有作物方块
            BlockPos abovePos = pos.above();
            BlockState aboveState = level.getBlockState(abovePos);
            boolean hasCropAbove = aboveState.getBlock() instanceof AdvancedCropBlock;

            // 如果没有作物且营养低于60
            if (!hasCropAbove && blockEntity.nutrition < 60) {
                // 缓慢恢复营养值（每次+5）
                blockEntity.setNutrition(blockEntity.nutrition + 5);
            }
        }
    }

    private static void updateEnvironmentProperties(Level level, BlockPos pos, SoilBlockEntity blockEntity) {
        // 模拟环境对土壤属性的影响
        // 未实现

        // 根据昼夜调整温度
        float daytime = level.getTimeOfDay(1.0f); // 0-1表示一天中的时间
        blockEntity.temperature = 15 + (int)(10 * Math.sin(daytime * Math.PI * 2));

        blockEntity.setChanged();
    }

    // 提供获取营养的方法
    public int getNutrition() {
        return nutrition;
    }
    public void setNutrition(int nutrition) {
        this.nutrition = Math.min(Math.max(nutrition, 0), MAX_NUTRITION);
        setChanged(); // 标记数据已更改
    }
}
