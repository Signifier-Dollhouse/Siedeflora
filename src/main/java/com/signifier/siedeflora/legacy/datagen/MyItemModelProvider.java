package com.signifier.siedeflora.legacy.datagen;

import com.signifier.siedeflora.legacy.Siedeflora;
import com.signifier.siedeflora.legacy.reg.MyItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MyItemModelProvider extends ItemModelProvider {
    public MyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Siedeflora.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(MyItems.CESHI.get());
    }
}
