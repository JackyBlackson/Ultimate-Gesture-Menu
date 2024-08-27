package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.OpenMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.DefaultMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MenuManagerMenu extends BaseGestureMenu{

    public MenuManagerMenu(Screen parent) {
        super("menu_manager", parent);
        this.updateMenuItems();
    }

    public void updateMenuItems() {
        this.getMenuItemMap().clear();
        this.initCentralItem();
        int itemCount = (int) Math.ceil(Math.sqrt(GestureMenuManager.getInstance().getMenuCount() + 1));
        int start = itemCount/2;    // odd -> (odd-1)/2;
        int x = -start;
        int y = -start;
        for(var menu : GestureMenuManager.getInstance().getMenuMap().values()) {
            if(x==0 && y==0) x+=1;
            MenuItemCoordinate c = new MenuItemCoordinate(x, y);
            DefaultMenuItem i = new DefaultMenuItem();
            // Random r = new Random(menu.getMenuId().hashCode());
            Random r = new Random();
            i.setText(Text.literal(menu.getMenuId()).fillStyle(Style.EMPTY
                    .withBold(true)
                    .withUnderline(true)
            ));
            i.setBackgroundColor(Color.of(
                    r.nextInt(40,255),
                    r.nextInt(40,255),
                    r.nextInt(40,255),
                    170
            ));
            i.setItemIdString("bookshelf");
            i.addAction(new OpenMenuAction(menu.getMenuId()));
            this.setItem(c, i);
            x += 1;
            if(x > itemCount) {
                x = -itemCount;
                y += 1;
            }
        }
    }
}
