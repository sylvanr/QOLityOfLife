package com.lordebil.qolityoflife.qol;

import com.lordebil.qolityoflife.util.Constants;
import com.lordebil.qolityoflife.util.Helper;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class VeinMiner {
    private static BlockPos startPos;
    private static int blocksBrokenAfterBreakEvent;
    private static List<BlockPos> checkedBlocks;
    private static List<BlockPos> blocksToBeDestroyed;

    public static void mineVein(BlockPos pos, World world, Block block) {
        startPos = pos;
        blocksBrokenAfterBreakEvent = 1;
        checkedBlocks = new ArrayList<>();
        blocksToBeDestroyed = new ArrayList<>();
        recursiveBlockSearch(startPos, world);
        breakFinalBlocks(world);
        block.dropXpOnBlockBreak(world, startPos, Constants.STONE_EXP_DROP_AMMOUNT * blocksBrokenAfterBreakEvent);
    }

    private static void recursiveBlockSearch(BlockPos blockPos, World world) {
        boolean checkXYDistance = blockPosWithinRange(blockPos);
        checkBlockAndBreak(blockPos.down(), world);
        checkBlockAndBreak(blockPos.up(), world);
        if (checkXYDistance) {
            checkBlockAndBreak(blockPos.north(), world);
            checkBlockAndBreak(blockPos.east(), world);
            checkBlockAndBreak(blockPos.south(), world);
            checkBlockAndBreak(blockPos.west(), world);
        }
    }

    private static boolean blockPosWithinRange(BlockPos blockPos) {
        int a = Math.abs(blockPos.north().getZ() - startPos.getZ());
        int b = Math.abs(blockPos.west().getX() - startPos.getX());
        int c = Math.abs(blockPos.east().getX() - startPos.getX());
        int d = Math.abs(blockPos.south().getZ() - startPos.getZ());

        return !(a > 8 || b > 8 || c > 8 || d > 8);
    }

    private static void checkBlockAndBreak(BlockPos blockPos, World world) {
        if (checkedBlocks.contains(blockPos)) return;
        checkedBlocks.add(blockPos);
        Block block = world.getBlockState(blockPos).getBlock();

        boolean shouldBlockBreak = Helper.blockIsType(block, Constants.ORES);
        if (shouldBlockBreak) {
            recursiveBlockSearch(blockPos, world);
            blocksBrokenAfterBreakEvent++;
            blocksToBeDestroyed.add(blockPos);
        }
    }

    private static void breakFinalBlocks(World world) {
        for (BlockPos pos : blocksToBeDestroyed) {
            world.destroyBlock(pos, Constants.SHOULD_DROP_BLOCK);
        }
    }
}
