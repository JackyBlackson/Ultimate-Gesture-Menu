package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.TestMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import net.minecraft.client.gui.screen.Screen;

public class TestGestureMenu extends BaseGestureMenu{
    public TestGestureMenu(String title, Screen parent) {
        super(title, parent);
        var c = new MenuItemCoordinate(-1, 0);
        this.setItem(
                c,
                new TestMenuItem(c, "dirt", Color.of(151, 109, 90, 100))
        );

        c = new MenuItemCoordinate(-1, -1);
        this.setItem(
                c,
                new TestMenuItem(c, "grass_block", Color.of(126, 191, 153, 100))
        );

        c = new MenuItemCoordinate(1, 1);
        this.setItem(
                c,
                new TestMenuItem(c, "gold_block", Color.of(255, 187, 114, 100))
        );
    }
}
