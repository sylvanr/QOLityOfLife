package com.lordebil.qolityoflife;

import com.lordebil.qolityoflife.event.ItemBreak;
import com.lordebil.qolityoflife.item.ModItems;
import com.lordebil.qolityoflife.util.Constants;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
public class Main {
    public Main() {
        // Register ItemBreaker
        MinecraftForge.EVENT_BUS.register(ItemBreak.class);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemsRegistryEvent) {
            // initialize additional items with the init funciton
            ModItems.init(itemsRegistryEvent);
        }
    }
}
