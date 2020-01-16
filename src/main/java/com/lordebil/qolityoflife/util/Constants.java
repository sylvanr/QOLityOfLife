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

    public static final int WOOD_EXP_DROP_AMMOUNT = 3;
    public static final int STONE_EXP_DROP_AMMOUNT = 3;

    public static final boolean SHOULD_DROP_BLOCK = true;
}
