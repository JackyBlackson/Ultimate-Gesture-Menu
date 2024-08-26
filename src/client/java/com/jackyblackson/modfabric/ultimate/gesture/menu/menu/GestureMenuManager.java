package com.jackyblackson.modfabric.ultimate.gesture.menu.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.BaseGestureMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GestureMenuManager {

    @Getter
    private static GestureMenuManager instance = new GestureMenuManager();

    //=============================

    private Map<String, IGestureMenu> menuMap = new HashMap<>();

    @Getter
    @Setter
    private String currentMenu = null;

    public Stack<String> menuHistory = new Stack<>();

    private GestureMenuManager () {

    }

    public boolean isNameOccupied(String name) {
        return this.menuMap.get(name) != null;
    }

    public boolean addMenu(String name, IGestureMenu menu) {
        return this.menuMap.put(name, menu) == null;
    }

    public BaseGestureMenu getMenuWithId(String menuId) {
        return (BaseGestureMenu) this.menuMap.get(menuId);
    }
}
