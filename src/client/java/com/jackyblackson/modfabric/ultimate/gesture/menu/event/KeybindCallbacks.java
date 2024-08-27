package com.jackyblackson.modfabric.ultimate.gesture.menu.event;

import com.jackyblackson.modfabric.ultimate.gesture.menu.config.Hotkeys;
import com.jackyblackson.modfabric.ultimate.gesture.menu.gui.GuiConfigs;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.BaseGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.MenuManagerMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.TestGestureMenu;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.interfaces.IClientTickHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class KeybindCallbacks implements IHotkeyCallback, IClientTickHandler {

    private static final KeybindCallbacks INSTANCE = new KeybindCallbacks();

    protected int massCraftTicker;

    public static KeybindCallbacks getInstance()
    {
        return INSTANCE;
    }

    private KeybindCallbacks()
    {
    }

    public void setCallbacks()
    {
        for (ConfigHotkey hotkey : Hotkeys.HOTKEY_LIST)
        {
            hotkey.getKeybind().setCallback(this);
        }
    }

    @Override
    public boolean onKeyAction(KeyAction action, IKeybind key) {
        return onKeyActionImpl(action, key);
    }

    private boolean onKeyActionImpl(KeyAction action, IKeybind key) {
        if (key == Hotkeys.OPEN_CONFIG_GUI.getKeybind())
        {
            GuiBase.openGui(new GuiConfigs());
            return true;
        } else if (key == Hotkeys.OPEN_GESTURE_MENU.getKeybind()) {

            System.out.println("OPEN MENU!");
            var menu = GestureMenuManager.getInstance().getMenuWithId(GestureMenuManager.getInstance().getCurrentMenu(), null);
            if(menu != null) {
                GestureMenuManager.getInstance().menuHistory.clear();
                GestureMenuManager.getInstance().menuHistory.push(menu.getMenuId());
            }

            MinecraftClient.getInstance().setScreen(menu);
            System.out.println("OPENED!");
            return true;
        } else if (key == Hotkeys.CLOSE_GESTURE_MENU.getKeybind()) {
            System.out.println("MENU ACT!");
            Screen currentScreen = MinecraftClient.getInstance().currentScreen;
            if(currentScreen instanceof IGestureMenu menu) {
                var result = menu.act();
                if(result != null){
                    System.out.println(result);
                } else {
                    System.out.println("ACT SUCCESS!");
                }
            }
            return true;
        } else if (key == Hotkeys.OPEN_CONFIG_MENU.getKeybind()) {
            var menu = new MenuManagerMenu(null);
            menu.initMenu();
            GestureMenuManager.getInstance().menuHistory.clear();
            GestureMenuManager.getInstance().menuHistory.push(menu.getMenuId());
            MinecraftClient.getInstance().setScreen(menu);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onClientTick(MinecraftClient mc) {

    }
}
