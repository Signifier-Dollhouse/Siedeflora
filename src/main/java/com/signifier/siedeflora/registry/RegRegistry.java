package com.signifier.siedeflora.registry;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.agriculture.crop.Period;
import com.signifier.siedeflora.agriculture.soil.SoilTexture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@EventBusSubscriber(modid = Siedeflora.MODID)
public interface RegRegistry
{
    ResourceKey<Registry<SoilTexture>> SOIL_KEY = ResourceKey.createRegistryKey(
            Siedeflora.id("soil"));
    ResourceKey<Registry<Period>> PERIOD_KEY = ResourceKey.createRegistryKey(Siedeflora.id("period"));

    Registry<SoilTexture> SOIL = new RegistryBuilder<>(SOIL_KEY)
            .sync(true)
            .defaultKey(Siedeflora.id("simple"))
            .create();
    Registry<Period> PERIOD = new RegistryBuilder<>(PERIOD_KEY)
            .sync(true)
            .create();

    @SubscribeEvent
    static void registerRegistries(final NewRegistryEvent event) {
        event.register(SOIL);
        event.register(PERIOD);
    }
}
