package com.signifier.siedeflora;

import com.signifier.siedeflora.block.BlockRegistry;
import com.signifier.siedeflora.item.ItemRegistry;
import com.signifier.siedeflora.legacy.Config;
import com.signifier.siedeflora.legacy.reg.MyCreativeTabs;
import net.minecraft.world.item.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Siedeflora.MODID)
public class Siedeflora {
    public static final String MODID = "siedeflora";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Siedeflora(IEventBus modEventBus, ModContainer modContainer) {
        //modEventBus.addListener(this::commonSetup);

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.BLOCK_ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        MyCreativeTabs.register(modEventBus);

        //modEventBus.addListener(this::addCreative);
        //NeoForge.EVENT_BUS.register(this);
    }

//    private void commonSetup(FMLCommonSetupEvent event) {
//        // Some common setup code
//        LOGGER.info("HELLO FROM COMMON SETUP");
//
//        if (com.signifier.siedeflora.legacy.Config.LOG_DIRT_BLOCK.getAsBoolean()) {
//            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
//        }
//
//        LOGGER.info("{}{}", com.signifier.siedeflora.legacy.Config.MAGIC_NUMBER_INTRODUCTION.get(), com.signifier.siedeflora.legacy.Config.MAGIC_NUMBER.getAsInt());
//
//        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
//    }
//
//    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
//            event.accept(Items.DEBUG_STICK);
//        }
//    }
}
