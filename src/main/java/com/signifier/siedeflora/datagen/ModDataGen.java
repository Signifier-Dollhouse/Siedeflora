package com.signifier.siedeflora.datagen;

import com.signifier.siedeflora.Siedeflora;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Siedeflora.MODID)
public interface ModDataGen
{
    @SubscribeEvent
    static void onDataGen(GatherDataEvent event) {

    }
}
