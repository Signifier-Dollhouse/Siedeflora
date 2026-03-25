package com.signifier.siedeflora.block;

import com.mojang.serialization.MapCodec;
import com.signifier.siedeflora.block.entity.SoilBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
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
        builder.add(AGE);
    }

    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getBlock() instanceof SoilBlock;
    }

    @Override
    public int getMaxAge() {
        return this.maxAge;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 是否在加载区块
        if (!level.isAreaLoaded(pos, 1)){
            // 获取下方土壤方块实体
            BlockPos soilPos = pos.below();
            BlockEntity blockEntity = level.getBlockEntity(soilPos);

            // 检查是否是SoilBlockEntity
            if (blockEntity instanceof SoilBlockEntity soilEntity) {
                // 获取土壤营养值
                int nutrition = soilEntity.getNutrition();
                float growthFactor = 1.0f; // 默认生长系数

                // 根据营养值调整生长系数
                if (nutrition > 80) {
                    growthFactor = 3.0f; // 营养>80，3倍生长速度
                } else if (nutrition > 50) {
                    growthFactor = 2.0f; // 营养>50，2倍生长速度
                }

                // TODO vanilla procedure
                if (level.getRawBrightness(pos, 0) >= 9) {
                    int i = this.getAge(state);
                    if (i < this.getMaxAge()) {
                        // 计算基础生长速度并应用生长系数
                        float adjustedSpeed = 1 * growthFactor;

                        // 检查是否满足生长条件
                        if (net.neoforged.neoforge.common.CommonHooks.canCropGrow
                                (level, pos, state,
                                        random.nextInt((int)(25.0F / adjustedSpeed) + 1) == 0)) {
                            // 生长到下一阶段
                            level.setBlock(pos, this.getStateForAge(i + 1), 2);
                            // 触发生长后事件
                            net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                            // 可选：消耗土壤营养
                            soilEntity.setNutrition(nutrition - 10);
                            soilEntity.setChanged();
                        }
                    }
                }
            }
        }
    }

    // TODO proxy all bonemeal operations to soil block
    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        // 获取下方土壤方块实体
        BlockPos soilPos = pos.below();
        BlockEntity blockEntity = level.getBlockEntity(soilPos);

        // 检查是否是SoilBlockEntity
        if (blockEntity instanceof SoilBlockEntity soilEntity) {
            // 获取当前营养值
            int currentNutrition = soilEntity.getNutrition();

            // 计算营养增加值（随机5-10点）
            int nutritionToAdd = 5 + random.nextInt(6);

            // 设置新的营养值（不超过最大值）
            soilEntity.setNutrition(Math.min(currentNutrition + nutritionToAdd, SoilBlockEntity.MAX_NUTRITION));

            // 播放骨粉使用效果（绿色粒子）
            level.levelEvent(2005, pos, 0);
        }
    }
}
