package com.jackyblackson.modfabric.ultimate.gesture.menu.menu;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

public interface IGestureMenuItem {
    public MenuItemCoordinate getCoordinate();

    public int getX();
    public int getY();

    public IGestureMenuAction getAction();

    public void render(
            DrawContext context, TextRenderer textRenderer,
            int startX, int startY,
            int width, int height,
            int mouseX, int mouseY,
            float delta
    );

    /**
     * Do the selected item's action
     * @return Null, if success; the message to display, otherwise.
     */
    public String act();

    public IGestureMenu getMenu();

    public void setPosition(MenuItemCoordinate position);

    public void setMenu(IGestureMenu menu);

    public void setAction(IGestureMenuAction action);
}
