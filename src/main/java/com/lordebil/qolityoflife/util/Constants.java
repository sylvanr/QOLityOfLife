package com.lordebil.qolityoflife.util;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String MODID = "qolityoflife";
    public static final String[] AXES_ARRAY = {"Stone Axe", "Wooden Axe", "Iron Axe", "Golden Axe", "Diamond Axe"};
    public static final List<String> AXES = Arrays.asList(Constants.AXES_ARRAY);

    public static final String[] LOGS_ARRAY = {"Jungle Log", "Birch Log", "Oak Log", "Dark Oak Log", "Acacia Log", "Spruce Log"};
    public static final List<String> LOGS = Arrays.asList(Constants.LOGS_ARRAY);

    public static final String[] LEAVES_ARRAY = {"Jungle Leaves", "Birch Leaves", "Oak Leaves", "Dark Oak Leaves", "Acacia Leaves", "Spruce Leaves"};
    public static final List<String> LEAVES = Arrays.asList(Constants.LEAVES_ARRAY);

    public static final String[] ORES_ARRAY = {"Gold Ore", "Iron Ore", "Coal Ore", "Redstone Ore", "Emerald Ore", "Lapis Lazuli Ore", "Diamond Ore", "Nether Quartz Ore"};
    public static final List<String> ORES = Arrays.asList(Constants.ORES_ARRAY);

    public static final String[] PICKAXES_ARRAY = {"Stone Pickaxe", "Wooden Pickaxe", "Iron Pickaxe", "Golden Pickaxe", "Diamond Pickaxe"};
    public static final List<String> PICKAXES = Arrays.asList(Constants.PICKAXES_ARRAY);

    public static final int WOOD_EXP_DROP_AMMOUNT = 3;
    public static final int STONE_EXP_DROP_AMMOUNT = 3;

    public static final boolean SHOULD_DROP_BLOCK = true;
}
