package com.signifier.siedeflora.legacy;

import com.signifier.siedeflora.legacy.reg.MyBlocks;
import com.signifier.siedeflora.legacy.reg.MyCreativeTabs;
import com.signifier.siedeflora.legacy.reg.MyItems;
import net.minecraft.world.item.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
//@Mod(Siedeflora.MOD_ID)
public class Siedeflora {

    public static final String MOD_ID = "siedeflora";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Siedeflora(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        // modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        // MyBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        // MyItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        // MyCreativeTabs.register(modEventBus);

        // modEventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        // NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        // modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }



    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
