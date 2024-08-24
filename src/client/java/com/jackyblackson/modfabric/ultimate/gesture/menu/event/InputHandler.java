package com.jackyblackson.modfabric.ultimate.gesture.menu.event;

import com.jackyblackson.modfabric.ultimate.gesture.menu.Reference;
import com.jackyblackson.modfabric.ultimate.gesture.menu.config.Hotkeys;
import fi.dy.masa.malilib.hotkeys.*;

public class InputHandler implements IKeybindProvider, IKeyboardInputHandler, IMouseInputHandler {
    private final KeybindCallbacks callbacks;

    public InputHandler()
    {
        this.callbacks = KeybindCallbacks.getInstance();
    }

    @Override
    public void addKeysToMap(IKeybindManager manager)
    {
        for (IHotkey hotkey : Hotkeys.HOTKEY_LIST)
        {
            manager.addKeybindToMap(hotkey.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager)
    {
        manager.addHotkeysForCategory(Reference.MOD_NAME, "ultimate-gesture-menu.hotkeys.category.hotkeys", Hotkeys.HOTKEY_LIST);
    }

    @Override
    public boolean onKeyInput(int keyCode, int scanCode, int modifiers, boolean eventKeyState)
    {
        return false;
    }

    @Override
    public boolean onMouseScroll(int mouseX, int mouseY, double amount)
    {
        return false;
    }

    @Override
    public boolean onMouseClick(int mouseX, int mouseY, int eventButton, boolean eventButtonState)
    {
        return false;
    }
}
