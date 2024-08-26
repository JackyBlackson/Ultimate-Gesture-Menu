package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.IGestureMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.BackToParentMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class BackToParentItem extends BaseMenuItem{
    public BackToParentItem(IGestureMenu menu, MenuItemCoordinate coordinate, IGestureMenuAction action) {
        super();
        this.addAction(new BackToParentMenuAction());
    }

    @Override
    public void render(
            DrawContext context, TextRenderer textRenderer,
            int startX, int startY,
            int width, int height,
            int mouseX, int mouseY,
            float delta
    ) {
        context.fill(startX, startY, startX + width, startY + height, Color.of(190, 76, 79, 130).toRgbAInt());
        context.drawCenteredTextWithShadow(textRenderer,
                Text.literal("BACK"),
                startX + width/2,
                startY + width/2 - 10/2,
                -1
        );
    }
}
