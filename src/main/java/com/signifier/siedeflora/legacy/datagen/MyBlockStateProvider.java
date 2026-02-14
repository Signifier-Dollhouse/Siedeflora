package com.signifier.siedeflora.datagen;

import com.signifier.siedeflora.Siedeflora;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class MyBlockStateProvider extends BlockStateProvider {
    public MyBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Siedeflora.MOD_ID, exFileHelper);
    }

    @Override
    // 给方块自动生成默认的json模型文件
    protected void registerStatesAndModels() {
//        blockWithItem(MyBlocks.EXAMPLE_BLOCK);
//        blockWithItem(MyBlocks.MAGIC_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
