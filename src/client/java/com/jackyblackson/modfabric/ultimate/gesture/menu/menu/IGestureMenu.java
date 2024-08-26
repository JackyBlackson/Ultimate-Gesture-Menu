package com.jackyblackson.modfabric.ultimate.gesture.menu.menu;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Map;

public interface IGestureMenu {
    /**
     * Returns a list of all the items in this menu
     * @return The List of menu items
     */
    public Map<MenuItemCoordinate, IGestureMenuItem> getMenuItemMap();

    /**
     * Returns the Text of this menu
     * @return The text of title
     */
    public String getMenuId();

    public IGestureMenuItem getSelectedItem();

    public IGestureMenuItem getItemAt(int x, int y);

    /**
     * Do the selected item's action
     * @return Null, if success; the message to display, otherwise.
     */
    public String act();

    public Screen getParentScreen();
    public boolean handleMouseMove(int mouseX, int mouseY);

    public void setItem(MenuItemCoordinate position, IGestureMenuItem menuItem);
}
