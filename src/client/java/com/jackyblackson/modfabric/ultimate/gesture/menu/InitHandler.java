package com.jackyblackson.modfabric.ultimate.gesture.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.config.Configs;
import com.jackyblackson.modfabric.ultimate.gesture.menu.event.InputHandler;
import com.jackyblackson.modfabric.ultimate.gesture.menu.event.KeybindCallbacks;
import com.jackyblackson.modfabric.ultimate.gesture.menu.event.WorldLoadListener;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.event.TickHandler;
import fi.dy.masa.malilib.event.WorldLoadHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;

public class InitHandler implements IInitializationHandler
{
    @Override
    public void registerModHandlers()
    {
        ConfigManager.getInstance().registerConfigHandler(Reference.MOD_ID, new Configs());

        InputHandler handler = new InputHandler();
        InputEventHandler.getKeybindManager().registerKeybindProvider(handler);
        InputEventHandler.getInputManager().registerKeyboardInputHandler(handler);
        InputEventHandler.getInputManager().registerMouseInputHandler(handler);

        WorldLoadListener listener = new WorldLoadListener();
        WorldLoadHandler.getInstance().registerWorldLoadPreHandler(listener);
        WorldLoadHandler.getInstance().registerWorldLoadPostHandler(listener);

        TickHandler.getInstance().registerClientTickHandler(KeybindCallbacks.getInstance());

        KeybindCallbacks.getInstance().setCallbacks();
    }
}
