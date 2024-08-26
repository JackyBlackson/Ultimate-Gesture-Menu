package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.BaseMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.ClientUtils;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import lombok.Getter;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BaseMenuItem implements IGestureMenuItem {

    private MenuItemCoordinate coordinate;
    private List<IGestureMenuAction> actionList;
    private IGestureMenu menu;

    public BaseMenuItem() {
        this.actionList = new ArrayList<>();
    }

    @Override
    public MenuItemCoordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public int getX() {
        return this.coordinate.getX();
    }

    @Override
    public int getY() {
        return this.coordinate.getY();
    }

    @Override
    public List<IGestureMenuAction> getActionList() {
        return this.actionList;
    }

    @Override
    public void render(
            DrawContext context, TextRenderer textRenderer,
            int startX, int startY,
            int width, int height,
            int mouseX, int mouseY,
            float delta
    ) {
        context.fill(startX, startY, startX + width, startY + height, Color.of(45, 42, 46, 130).toRgbAInt());
//        context.drawCenteredTextWithShadow(textRenderer,
//                Text.literal("N/A"),
//                startX + width/2,
//                startY + width/2 - 10/2,
//                -1
//                );
    }

    @Override
    public String act() {
        if(this.menu != null) {
            ClientUtils.getClient().setScreen(this.menu.getParentScreen());
        }
        StringBuilder retStr = new StringBuilder();
        for(var action : this.actionList) {
            String actResult = action.act(this);
            if (actResult != null) {
                retStr.append(actResult).append("\n");
            }
        }
        return retStr.toString();
    }

    @Override
    public IGestureMenu getMenu() {
        return this.menu;
    }

    @Override
    public void setPosition(MenuItemCoordinate position) {
        this.coordinate = position;
    }

    @Override
    public void setMenu(IGestureMenu menu) {
        this.menu = menu;
    }

    @Override
    public void setActionList(List<IGestureMenuAction> actions) {
        this.actionList = actions;
    }

    @Override
    public void addAction(IGestureMenuAction action) {
        this.actionList.add(action);
    }
}
