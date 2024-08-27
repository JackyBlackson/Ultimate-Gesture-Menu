package com.jackyblackson.modfabric.ultimate.gesture.menu.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.BaseGestureMenu;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.screen.Screen;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GestureMenuManager {

    @Getter
    private static GestureMenuManager instance = new GestureMenuManager();

    //=============================

    @Getter
    private Map<String, IGestureMenu> menuMap = new HashMap<>();

    @Getter
    @Setter
    private String currentMenu = null;

    public Stack<String> menuHistory = new Stack<>();

    private GestureMenuManager () {

    }

    public int getMenuCount() {
        return this.menuMap.size();
    }

    public boolean isNameOccupied(String name) {
        return this.menuMap.get(name) != null;
    }

    public boolean addMenu(String name, IGestureMenu menu) {
        return this.menuMap.put(name, menu) == null;
    }

    public BaseGestureMenu getMenuWithId(String menuId, Screen parent) {
        var menu = (BaseGestureMenu) this.menuMap.get(menuId);
        if(menu == null) {
            return null;
        }
        menu.setParent(parent);
        menu.initMenu();
        return menu;
    }
}
