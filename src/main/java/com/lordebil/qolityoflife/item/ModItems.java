package com.lordebil.qolityoflife.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.event.RegistryEvent;

public class ModItems {
    public static Item IRON_HAMMER;
    public static Item GOLDEN_HAMMER;
    public static Item DIAMOND_HAMMER;

    public static void init(RegistryEvent.Register<Item> event) {
        Item ironBlock = Item.BLOCK_TO_ITEM.get(Blocks.IRON_BLOCK);
        Item goldBlock = Item.BLOCK_TO_ITEM.get(Blocks.GOLD_BLOCK);
        Item diamondBlock = Item.BLOCK_TO_ITEM.get(Blocks.DIAMOND_BLOCK);

        IRON_HAMMER = makeHammer(ItemTier.IRON, ironBlock, "iron_hammer");
        GOLDEN_HAMMER = makeHammer(ItemTier.GOLD, goldBlock, "golden_hammer");
        DIAMOND_HAMMER = makeHammer(ItemTier.DIAMOND, diamondBlock, "diamond_hammer");

        event.getRegistry().register(IRON_HAMMER);
        event.getRegistry().register(GOLDEN_HAMMER);
        event.getRegistry().register(DIAMOND_HAMMER);
    }

    private static Item makeHammer(ItemTier tier, Item customRepair, String registryName) {
        Ingredient ingredient = Ingredient.fromItems(customRepair);
        return new Hammer(tier, 1, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS), ingredient).setRegistryName(registryName);
    }
}
