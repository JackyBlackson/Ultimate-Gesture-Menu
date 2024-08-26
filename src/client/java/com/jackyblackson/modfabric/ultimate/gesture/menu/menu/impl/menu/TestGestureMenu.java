package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.TestMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import net.minecraft.client.gui.screen.Screen;

import java.util.Random;

public class TestGestureMenu extends BaseGestureMenu{
    public TestGestureMenu(String id, Screen parent) {
        super(id, parent);
        var random = new Random();
        var c = new MenuItemCoordinate(-1, 0);
        this.setItem(
                c,
                new TestMenuItem(c, "dirt", Color.of(
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        100))
        );

        c = new MenuItemCoordinate(-1, -1);
        this.setItem(
                c,
                new TestMenuItem(c, "grass_block", Color.of(
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        100))
        );

        c = new MenuItemCoordinate(2, 1);
        this.setItem(
                c,
                new TestMenuItem(c, "gold_block", Color.of(
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        random.nextInt(40, 200),
                        100))
        );
    }
}
