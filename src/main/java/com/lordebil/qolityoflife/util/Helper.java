package com.lordebil.qolityoflife.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.List;

public class Helper {
    public static boolean blockIsType(Block block, List list) {
        return list.contains(block.getNameTextComponent().getFormattedText());
    }

    public static boolean itemIsType(Item item, List list) {
        return list.contains(item.getName().getFormattedText());
    }
}
