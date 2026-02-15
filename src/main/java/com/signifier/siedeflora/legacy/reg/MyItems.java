package com.signifier.siedeflora.legacy.reg;

import com.signifier.siedeflora.legacy.Siedeflora;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MyItems {
    public static final DeferredRegister.Items MYITEMS = DeferredRegister.createItems(Siedeflora.MOD_ID);

    //物品注册
    public static final DeferredItem<Item> CESHI =
            MYITEMS.registerSimpleItem("ceshi", new Item.Properties().rarity(Rarity.EPIC).stacksTo(2));

    public static void register(IEventBus eventBus) {
        MYITEMS.register(eventBus);
    }
}
