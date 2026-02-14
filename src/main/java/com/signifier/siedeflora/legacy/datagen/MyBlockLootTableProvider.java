package com.signifier.siedeflora.datagen;

import com.signifier.siedeflora.reg.MyBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class MyBlockLootTableProvider extends BlockLootSubProvider {
    protected MyBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate(){
//        // 掉落自身
//        dropSelf(MyBlock.MAGIC_BLOCK.get());
//        // 以矿的形式掉指定物品的写法
//        add(MyBlock.EXAMPLE_BLOCK.get(),
//                block -> createOreDrop(MyBlock.EXAMPLE_BLOCK.get(), MyItem.EXAMPLE_ITEM.get()));
    }

    // 新写的矿物掉落规则,让方块掉落可以定义掉落什么物品以及掉落数量的范围,还可以应用于精准和时运附魔
    protected LootTable.Builder createMultipleOreDrop(Block block, Item item, float minDrops, float maxDrops){
        // holder查询.注册表查询<附魔> 注册表查询结果 = 父类.注册表大类.查询或抛出(注册表.附魔)
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        // 返回 父类.创造精准采集调度表(传入block,物品战利品表)
        return this.createSilkTouchDispatchTable(block, LootItem.lootTableItem(item))
                //.应用(设置物品数量.设置数量(均分器.在...之间(最小掉落,最大掉落)))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                //.应用(应用额外数量.添加额外矿物数量(注册表查询.抛出(附魔.时运)))
                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)));
    }

    @Override//重写
    //获取已知的注册方块
    protected Iterable<Block> getKnownBlocks() {
        //返回MyBlock中BLCOKS的value值
        // iterator:返回此流的元素的迭代器
        // stream:返回以该集合作为其源的序列流
        return MyBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
