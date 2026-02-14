package com.signifier.siedeflora.reg;

import com.signifier.siedeflora.Siedeflora;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MyCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Siedeflora.MOD_ID);
    public static final Supplier<CreativeModeTab> FLORETON_ITEM_TAB = CREATIVE_MODE_TABS.register("floreton_item_tab",
            ()-> CreativeModeTab.builder()
                    // 图标
                    .icon(()-> new ItemStack(Items.DIAMOND))
                    // 确定lang文件中的key
                    .title(Component.translatable("creativetab.floreton.item_title"))
                    // 接收物品法
                    .displayItems((itemDisplayParameters, output) -> {
                        // 示例
                        output.accept(MyItems.CESHI);
                        output.accept(Items.DEBUG_STICK);

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
