package com.jackyblackson.modfabric.ultimate.gesture.menu.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.jackyblackson.modfabric.ultimate.gesture.menu.Reference;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.IConfigValue;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Configs implements IConfigHandler
{
    private static final String CONFIG_FILE_NAME = Reference.MOD_ID + ".json";

    public static class Generic
    {
        public static final ConfigBoolean CARPET_CTRL_Q_CRAFTING                = new ConfigBoolean("carpetCtrlQCraftingEnabledOnServer",   false, "Set to true if the server is running the Carpet mod,\nand has the ctrlQCrafting option enabled.\nThis just changes which method Item Scroller uses\nfor the Drop key + Shift + Right click crafting.");
        public static final ConfigInteger MAX_MENU_WIDTH_ITEMS = new ConfigInteger("maxItemCountOfWidth",9, "The max count of item of a Gesture Menu in width");
        public static final ConfigInteger MAX_MENU_HEIGHT_ITEMS = new ConfigInteger("maxItemCountOfWidth",5, "The max count of item of a Gesture Menu in width");
        public static final ConfigInteger GESTURE_MOVEMENT_THRESHOLD = new ConfigInteger("gestureMovementThreshold", 8, "How many pixel the mouse move will cause a shift of the selection.");

        public static final ImmutableList<IConfigValue> OPTIONS = ImmutableList.of(
                CARPET_CTRL_Q_CRAFTING,
                MAX_MENU_WIDTH_ITEMS,
                MAX_MENU_HEIGHT_ITEMS,
                GESTURE_MOVEMENT_THRESHOLD
        );
    }

    public static class Toggles
    {
        public static final ConfigBoolean CRAFTING_FEATURES         = new ConfigBoolean("enableCraftingFeatures",           true, "Enables scrolling items to and from crafting grids,\nwith a built-in 18 recipe memory.\nHold down the Recipe key to see the stored recipes and\nto change the selection. While holding the Recipe key,\nyou can either scroll or press a number key to change the selection.\nA recipe is stored to the currently selected \"recipe slot\"\n by clicking pick block over a configured crafting output slot.\nThe supported crafting grids must be added to the scrollableCraftingGrids list.");



        public static final ImmutableList<IConfigValue> OPTIONS = ImmutableList.of(
                CRAFTING_FEATURES
        );
    }

    public static final Set<String> GUI_BLACKLIST = new HashSet<>();
    public static final Set<String> SLOT_BLACKLIST = new HashSet<>();

    public static void loadFromFile()
    {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead())
        {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject())
            {
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(root, "Generic", Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "Hotkeys", Hotkeys.HOTKEY_LIST);
                ConfigUtils.readConfigBase(root, "Toggles", Toggles.OPTIONS);

                getStrings(root, GUI_BLACKLIST, "guiBlacklist");
                getStrings(root, SLOT_BLACKLIST, "slotBlacklist");
            }
        }
    }

    public static void saveToFile()
    {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs())
        {
            JsonObject root = new JsonObject();

            ConfigUtils.writeConfigBase(root, "Generic", Generic.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Hotkeys", Hotkeys.HOTKEY_LIST);
            ConfigUtils.writeConfigBase(root, "Toggles", Toggles.OPTIONS);

            writeStrings(root, GUI_BLACKLIST, "guiBlacklist");
            writeStrings(root, SLOT_BLACKLIST, "slotBlacklist");

            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override
    public void load()
    {
        loadFromFile();
    }

    @Override
    public void save()
    {
        saveToFile();
    }

    private static void getStrings(JsonObject obj, Set<String> outputSet, String arrayName)
    {
        outputSet.clear();

        if (JsonUtils.hasArray(obj, arrayName))
        {
            JsonArray arr = obj.getAsJsonArray(arrayName);
            final int size = arr.size();

            for (int i = 0; i < size; i++)
            {
                outputSet.add(arr.get(i).getAsString());
            }
        }
    }

    private static void writeStrings(JsonObject obj, Set<String> inputSet, String arrayName)
    {
        if (inputSet.isEmpty() == false)
        {
            JsonArray arr = new JsonArray();

            for (String str : inputSet)
            {
                arr.add(str);
            }

            obj.add(arrayName, arr);
        }
    }
}
