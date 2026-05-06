package com.signifier.siedeflora.crop.harvest;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntry;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class CropLootEntry extends LootPoolEntryContainer {


    protected CropLootEntry(List<LootItemCondition> conditions) {
        super(conditions);
    }

    @Override
    public MapCodec<? extends LootPoolEntryContainer> codec() {
        return null;
    }

    @Override
    public boolean expand(LootContext lootContext, Consumer<LootPoolEntry> consumer) {
        return false;
    }
}
