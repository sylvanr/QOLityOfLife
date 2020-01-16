package com.lordebil.qolityoflife.qol;

import com.lordebil.qolityoflife.util.Constants;
import com.lordebil.qolityoflife.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GravelGrabber {
    private static List<BlockPos> blocksToBeDestroyed;
    public static void grabGravel(BlockPos pos, World world, Block block) {
        blocksToBeDestroyed = new ArrayList<>();

        getBlocksDown(pos, world);
        getBlocksUp(pos, world);
        breakFinalBlocks(world);
        block.dropXpOnBlockBreak(world, pos, Constants.WOOD_EXP_DROP_AMMOUNT * blocksToBeDestroyed.size());
    }

    private static void getBlocksDown(BlockPos pos, World world) {
        BlockPos downPos = pos.down();
        if (Helper.blockIsType(world.getBlockState(downPos).getBlock(), "Gravel")) {
            blocksToBeDestroyed.add(downPos);
            getBlocksDown(downPos, world);
        }
    }

    private static void getBlocksUp(BlockPos pos, World world) {
        BlockPos upPos = pos.up();
        if (Helper.blockIsType(world.getBlockState(upPos).getBlock(), "Gravel")) {
            blocksToBeDestroyed.add(upPos);
            getBlocksUp(upPos, world);
        }
    }

    private static void breakFinalBlocks(World world) {
        for (BlockPos pos : blocksToBeDestroyed) {
            world.destroyBlock(pos, Constants.SHOULD_DROP_BLOCK);
        }
    }
}
