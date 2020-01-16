package com.lordebil.qolityoflife.event;

import com.lordebil.qolityoflife.qol.GravelGrabber;
import com.lordebil.qolityoflife.qol.TreeFeller;
import com.lordebil.qolityoflife.util.Constants;
import com.lordebil.qolityoflife.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemBreak {

    @SubscribeEvent
    public static void breakItem(BlockEvent.BreakEvent event) {
        // Player who broke the block
        PlayerEntity player = event.getPlayer();
        Item heldItem = player.getHeldItemMainhand().getItem();
        BlockPos blockPos = event.getPos();
        Block block = event.getState().getBlock();
        World world = event.getWorld().getWorld();

        // Check if the user is holding an axe
        if (Helper.itemIsType(heldItem, Constants.AXES) &&
            Helper.blockIsType(block, Constants.LOGS) &&
            !player.isCrouching()
        ) TreeFeller.fellTree(blockPos, world, block);

        if (Helper.blockIsType(block, "Gravel") && !player.isCrouching())
            GravelGrabber.grabGravel(blockPos, world, block);
    }
}
