package com.signifier.siedeflora.legacy.datagen;

import com.signifier.siedeflora.legacy.reg.MyItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MyRecipeProvider extends RecipeProvider {
    public MyRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // 创建一个列表,可以批量处理相同的配方,如熔炉这种
        List<ItemLike> MY_ORE = List.of(MyItems.CESHI);

        oreSmelting(recipeOutput, MY_ORE, RecipeCategory.MISC, Items.DIAMOND, 0.25f, 200, "ceshi_diamond");
        oreBlasting(recipeOutput, MY_ORE, RecipeCategory.MISC, Items.DIAMOND, 0.25f, 100, "ceshi_diamond");

        // 有序示例
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND)
                .pattern("BBB")
                .pattern("BBB")
                .pattern(" B ")
                .define('B', MyItems.CESHI.get())
                .unlockedBy("has_dimond_shaped", has(MyItems.CESHI)).save(recipeOutput);

        // 无序示例
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GOLD_BLOCK, 7)
                .requires(MyItems.CESHI)
                .unlockedBy("has_dimond_shapeless", has(MyItems.CESHI)).save(recipeOutput);
    }
}
