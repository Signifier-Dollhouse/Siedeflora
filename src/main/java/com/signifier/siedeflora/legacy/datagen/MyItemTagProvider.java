package com.signifier.siedeflora.legacy.datagen;

import com.signifier.siedeflora.legacy.Siedeflora;
import com.signifier.siedeflora.legacy.reg.MyItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class MyItemTagProvider extends ItemTagsProvider {
    public MyItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Siedeflora.MOD_ID, existingFileHelper);
    }
    // 示例
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.FOOT_ARMOR)
                .add(MyItems.CESHI.get());
    }
}
