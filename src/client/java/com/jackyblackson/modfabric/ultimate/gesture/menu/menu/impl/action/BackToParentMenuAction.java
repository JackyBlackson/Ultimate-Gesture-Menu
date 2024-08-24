package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class BackToParentMenuAction extends BaseMenuAction{
    @Override
    public String act(IGestureMenuItem item) {
        try {
            if (item.getMenu().getParentScreen() == null) {
                MinecraftClient.getInstance().setScreen(null);
            } else {
                MinecraftClient.getInstance().setScreen(item.getMenu().getParentScreen());
            }
            return null;
        } catch (Exception ignored){
            return "Cannot find parent screen";
        }
    }
}
