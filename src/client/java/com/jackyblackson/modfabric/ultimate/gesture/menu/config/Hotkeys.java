package com.jackyblackson.modfabric.ultimate.gesture.menu.config;

import java.util.List;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

public class Hotkeys
{
    private static final KeybindSettings GUI_RELAXED = KeybindSettings.create(KeybindSettings.Context.GUI, KeyAction.PRESS, true, false, false, false);
    private static final KeybindSettings GUI_RELAXED_CANCEL = KeybindSettings.create(KeybindSettings.Context.GUI, KeyAction.PRESS, true, false, false, true);
    private static final KeybindSettings GUI_NO_ORDER = KeybindSettings.create(KeybindSettings.Context.GUI, KeyAction.PRESS, false, false, false, true);
    private static final KeybindSettings INGAME_RELAXED = KeybindSettings.create(KeybindSettings.Context.INGAME, KeyAction.PRESS, true, false, false, false);
    private static final KeybindSettings INGAME_RELEASE = KeybindSettings.create(KeybindSettings.Context.INGAME, KeyAction.RELEASE, true, false, false, false);

    private static final KeybindSettings INGAME_MENU = KeybindSettings.create(KeybindSettings.Context.INGAME, KeyAction.BOTH, true, false, false, false);
    private static final KeybindSettings GUI_RELEASE = KeybindSettings.create(KeybindSettings.Context.GUI, KeyAction.RELEASE, true, false, false, false);
    public static final ConfigHotkey OPEN_CONFIG_GUI = new ConfigHotkey(
            "open config gui",
            "C, G",
            "Open the in-game config GUI"
    );
    public static final ConfigHotkey OPEN_GESTURE_MENU = new ConfigHotkey(
            "open gesture gui",
            "R",
            INGAME_MENU,
            "Open the in-game config GUI"
    );

    public static final ConfigHotkey CLOSE_GESTURE_MENU = new ConfigHotkey(
            "close gesture gui",
            "R",
            GUI_RELEASE,
            "Open the in-game config GUI"
    );



    public static final List<ConfigHotkey> HOTKEY_LIST = ImmutableList.of(
            OPEN_CONFIG_GUI,
            OPEN_GESTURE_MENU,
            CLOSE_GESTURE_MENU
    );
}
