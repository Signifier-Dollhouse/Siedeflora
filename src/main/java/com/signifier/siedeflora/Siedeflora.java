package com.signifier.siedeflora;

import com.signifier.siedeflora.block.BlockRegistry;
import com.signifier.siedeflora.item.ItemRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

@Mod(Siedeflora.MODID)
public class Siedeflora {
    public static final String MODID = "siedeflora";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Siedeflora(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.BLOCK_ENTITIES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
    }
}
