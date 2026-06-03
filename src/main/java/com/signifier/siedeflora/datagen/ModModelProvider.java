package com.signifier.siedeflora.datagen;

import com.signifier.siedeflora.Siedeflora;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider
{
    public ModModelProvider(PackOutput output) {
        super(output, Siedeflora.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
//        blockModels.createTrivialBlock(BlockRegistry.SOIL.get(), );
    }
}
