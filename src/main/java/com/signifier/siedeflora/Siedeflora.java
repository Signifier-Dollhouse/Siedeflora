package com.signifier.siedeflora;

import com.signifier.siedeflora.block.BlockRegistry;
import com.signifier.siedeflora.item.ItemRegistry;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

@Mod(Siedeflora.MODID)
public class Siedeflora {
    public static final String MODID = "siedeflora";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Siedeflora(IEventBus modBus, ModContainer container) {
        BlockRegistry.BLOCKS.register(modBus);
        BlockRegistry.BLOCK_ENTITIES.register(modBus);
        ItemRegistry.ITEMS.register(modBus);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}
