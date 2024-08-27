package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.ClientUtils;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class OpenMenuAction extends BaseMenuAction{
    private final String menuId;
    public OpenMenuAction(@NotNull String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String act(IGestureMenuItem item) {
        var menu = GestureMenuManager.getInstance().getMenuWithId(this.menuId, (Screen)item.getMenu());
        if(menu == null) {
            return StringUtils.translate(
                    "ultimate-gesture-menu.menu.action.open_menu_action.fail.menu_not_found",
                    this.menuId
            );
        }
        GestureMenuManager.getInstance().menuHistory.push(menu.getMenuId());
        ClientUtils.getClient().setScreen(menu);
        return null;
    }
}
