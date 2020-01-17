package com.lordebil.qolityoflife.item;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import java.util.Set;


public class Hammer extends PickaxeItem {
    public final Ingredient customRepair;

    public static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(
        Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK,
        Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK,
        Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE,
        Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE,
        Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE,
        Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE,
        Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB,
        Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB,
        Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE,
        Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB,
        Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB,
        Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB,
        Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX,
        Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX,
        Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX,
        Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX,
        Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX,
        Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL,
        Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH,
        Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER,
        Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER,
        Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER,
        Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER
    );

    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(
        Material.ROCK, Material.IRON, Material.GLASS,
        Material.ICE, Material.PACKED_ICE, Material.ANVIL,
        Material.CLAY, Material.ORGANIC, Material.EARTH,
        Material.SAND, Material.SNOW, Material.SNOW_BLOCK
    );
    
    public Hammer(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder, Ingredient customRepair) {
        super(tier, attackDamageIn, attackSpeedIn, builder);

        this.customRepair = customRepair;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        stack.attemptDamageItem(1, new Random(), null);
        if (entityLiving instanceof PlayerEntity)
            breakArea(worldIn, pos, (PlayerEntity) entityLiving, EFFECTIVE_ON, EFFECTIVE_MATERIALS);

        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return customRepair.test(repair);
    }

    private static void breakArea(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials) {
        System.out.println(); // -0.70 is naar beneden, 0.70 is naar boven
        System.out.println();

        Direction playerDirection;
        double lookVecY = player.getLookVec().getY();

        if (lookVecY < -0.70) playerDirection = Direction.DOWN;
        else if (lookVecY > 0.70) playerDirection = Direction.UP;
        else playerDirection = player.getAdjustedHorizontalFacing();

        if (player.isCrouching()) return;

        Block block = world.getBlockState(pos).getBlock();
        if (!effectiveOn.contains(block) && !effectiveMaterials.contains(block)) return;

        for (int a = -2; a <= 2; a++) {
            for (int b = -2; b <= 2; b++) {
                BlockPos target = null;

                if (playerDirection == Direction.UP    || playerDirection == Direction.DOWN) {
                    if (a == 0 && b == 0) continue;
                    target = pos.add(a, 0, b);
                }
                if (playerDirection == Direction.NORTH || playerDirection == Direction.SOUTH) {
                    if (a == 0 && b == -1) continue;
                    target = pos.add(a, b+1, 0);
                }
                if (playerDirection == Direction.EAST  || playerDirection == Direction.WEST) {
                    if (a == -1 && b == 0) continue;
                    target = pos.add(0, a+1, b);
                }

                breakBlocks(world, target, player, effectiveOn, effectiveMaterials);
            }
        }
    }

    private static void breakBlocks(World world, BlockPos pos, PlayerEntity player, Set<Block> effectiveOn, Set<Material> effectiveMaterials) {
        BlockState state = world.getBlockState(pos);

        boolean isEffective = effectiveOn.contains(state.getBlock()) || effectiveMaterials.contains(state.getMaterial());

        int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand());
        int silkLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand());

        if (isEffective) {
            world.destroyBlock(pos, false);
            Block.spawnDrops(state, world, pos, null, player, player.getHeldItemMainhand());

            int exp = state.getExpDrop(world, pos, fortuneLevel, silkLevel);
            if (exp > 0) {
                state.getBlock().dropXpOnBlockBreak(world, pos, exp);
            }
        }
    }
}
