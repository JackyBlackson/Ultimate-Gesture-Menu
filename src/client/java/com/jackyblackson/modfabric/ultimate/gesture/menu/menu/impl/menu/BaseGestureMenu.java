package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.config.Configs;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.BackToParentMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.BackToParentItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.BaseMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseGestureMenu extends Screen implements IGestureMenu {
    @Getter
    @Setter
    private Screen parent;


    private final Map<MenuItemCoordinate, IGestureMenuItem> menuItemMap;

    private final Map<MenuItemCoordinate, IGestureMenuItem> renderItemMap;

    private IGestureMenuItem selectedItem;

    private int itemCount = 0;

    private int maxAbsCoordinate = 0;

    private Text title;

    @Setter
    @Getter
    private int maxItemSize = 40;

    private int lastMouseX = -1;
    private int lastMouseY = -1;

    private String menuId;

    public BaseGestureMenu(@NotNull String menuId, Screen parent) {
        super(Text.literal(menuId));
        this.menuId = menuId;
        this.renderItemMap = new HashMap<>();
        this.menuItemMap = new HashMap<>();
        this.parent = parent;
        initCentralItem();
    }

    @Override
    public void setItem(MenuItemCoordinate position, IGestureMenuItem menuItem) {
        menuItem.setMenu(this);
        menuItem.setPosition(position);
        this.menuItemMap.put(position, menuItem);
        this.refreshRenderItemMap();
    }

    public void refreshRenderItemMap() {
        var maxCoord = getMaxXY();
        maxAbsCoordinate = Math.max(maxCoord.getX(), maxCoord.getY());
        itemCount = maxAbsCoordinate * 2 + 1;
        this.renderItemMap.clear();
        for(int i = -maxAbsCoordinate; i <= maxAbsCoordinate; i++) {
            for(int j = -maxAbsCoordinate; j <= maxAbsCoordinate; j++) {
                var item = this.menuItemMap.get(new MenuItemCoordinate(i, j));
                if(item != null) {
                    this.renderItemMap.put(item.getCoordinate(), item);
                } else {
                    var placeholder = new BaseMenuItem();
                    var position = new MenuItemCoordinate(i, j);
                    placeholder.setPosition(position);
                    this.renderItemMap.put(position, placeholder);
                }
            }
        }
    }

    private void initCentralItem() {
        var c = new MenuItemCoordinate(0, 0);
        var item = new BackToParentItem(this, c, new BackToParentMenuAction());
        this.setItem(c, item);
        this.selectedItem = item;
    }

    @Override
    public Map<MenuItemCoordinate, IGestureMenuItem> getMenuItemMap() {
        return this.menuItemMap;
    }

    @Override
    public String getMenuId() {
        return this.menuId;
    }

    @Override
    public IGestureMenuItem getSelectedItem() {
        return this.selectedItem;
    }

    @Override
    public IGestureMenuItem getItemAt(int x, int y) {
        return this.menuItemMap.get(new MenuItemCoordinate(x, y));
    }

    public MenuItemCoordinate getMaxXY() {
        int maxX = 0; int maxY = 0;

        for (MenuItemCoordinate c : this.menuItemMap.keySet()) {
            if (Math.abs(c.getX()) > maxX) maxX = Math.abs(c.getX());
            if (Math.abs(c.getY()) > maxY) maxY = Math.abs(c.getY());
        }

        return new MenuItemCoordinate(maxX, maxY);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.lastMouseX = this.lastMouseX < 0 ? mouseX : lastMouseX;
        this.lastMouseY = this.lastMouseY < 0 ? mouseY : lastMouseY;
        boolean skipDefaultImpl = handleMouseMove(mouseX, mouseY);
        assert this.client != null;
        int windowHeight = this.height;
        int windowWidth = this.width;
        int windowCenterX = windowWidth  / 2;
        int windowCenterY = windowHeight / 2;
        int padding  =  30;
        int margin   =  Math.max(4, 30 / (itemCount-1));    // if have 3 items, then it will be 10
        int menuSize = Math.min(windowHeight-2*padding, windowWidth-2*padding);
        int itemSize = Math.min(this.maxItemSize, ((menuSize - (itemCount-1) * margin)) / itemCount);
        int menuStartX = windowCenterX - menuSize/2;
        int menuStartY = windowCenterY - menuSize/2;

        // draw title stuff
        int textX = menuStartX;
        int textY = menuStartY-12;
        for (var name : GestureMenuManager.getInstance().menuHistory) {
            textX = context.drawTextWithShadow(this.textRenderer, ">", textX, textY, -1);
            textX++;
            textX = context.drawTextWithShadow(this.textRenderer, name, textX, textY, -1);
            textX++;
        }


        // draw items
        for(var item : this.renderItemMap.values()) {
            var c = item.getCoordinate();
            int startX = windowCenterX + c.getX() * (itemSize + margin) - itemSize / 2;
            int startY = windowCenterY + c.getY() * (itemSize + margin) - itemSize / 2;
            if(
                    mouseX >= startX && mouseX <= startX + itemSize
                    &&
                    mouseY >= startY && mouseY <= startY + itemSize
                    &&
                    !skipDefaultImpl
            ) {
                this.selectedItem = item;
            }
            item.render(
                    context, this.textRenderer,
                    startX, startY,
                    itemSize,
                    itemSize,
                    mouseX, mouseY, delta
            );
            if(c.equals(this.selectedItem.getCoordinate())) {
                context.drawBorder(
                        startX - 2,
                        startY - 2,
                        itemSize + 4,
                        itemSize + 4,
                        Color.of(255, 255, 255).toRgbAInt()
                );
                context.fill(
                        startX, startY,
                        startX + itemSize,
                        startY + itemSize,
                        Color.of(255, 255, 255, 20).toRgbAInt()
                );
            } else {
                context.drawBorder(
                        startX - 2,
                        startY - 2,
                        itemSize + 4,
                        itemSize + 4,
                        Color.of(200, 200, 200, 180).toRgbAInt()
                );
            }
        }
        // super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean handleMouseMove(int mouseX, int mouseY) {
        /*
        boolean result = false;
        int deltaX = 0;
        int threshold = Configs.Generic.GESTURE_MOVEMENT_THRESHOLD.getIntegerValue();
        if(Math.abs(mouseX - lastMouseX) >= threshold) {
            deltaX = (mouseX - lastMouseX) / threshold;
            this.lastMouseX += deltaX * threshold;
        }
        int deltaY = 0;
        if(Math.abs(mouseY - lastMouseY) >= threshold) {
            deltaY = (mouseY - lastMouseY) / threshold ;
            this.lastMouseY += deltaY * threshold;
        }

        var item = this.renderItemMap.get(
                new MenuItemCoordinate(
                        this.selectedItem.getX() + deltaX,
                        this.selectedItem.getY() + deltaY
                )
        );
        if(item != null) {
            this.selectedItem = item;
            result = true;
        }


        return result;
        */
        return false;
    }

    @Override
    public String act() {
        return this.selectedItem.act();
    }

    @Override
    public Screen getParentScreen() {
        return this.parent;
    }

    public void initMenu() {
        super.init(
                MinecraftClient.getInstance().gameRenderer.getClient(),
                MinecraftClient.getInstance().getWindow().getScaledWidth(),
                MinecraftClient.getInstance().getWindow().getScaledHeight()
        );
    }
}
