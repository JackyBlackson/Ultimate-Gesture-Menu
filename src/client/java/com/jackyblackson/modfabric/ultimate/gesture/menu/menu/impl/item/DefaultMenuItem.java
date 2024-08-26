package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.SendMessageAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.BaseGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.ItemUtils;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class DefaultMenuItem extends BaseMenuItem {
    private String itemIdString;
    private Color backgroundColor;
    public DefaultMenuItem(
            String itemId,
            Color bgColor
    ) {
        super();
        this.itemIdString = itemId;
        this.backgroundColor = bgColor;
    }

    @Override
    public void setPosition(MenuItemCoordinate position) {
        super.setPosition(position);
        this.addAction(new SendMessageAction(
                "Menu item in [" +
                        position.getX()
                        + ", "
                        + position.getY()
                        + "] displays a(n) " + this.itemIdString + "."
        ));
    }

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, int startX, int startY, int width, int height, int mouseX, int mouseY, float delta) {
        context.fill(startX, startY, startX + width, startY + height, this.backgroundColor.toRgbAInt());
        var stack = ItemUtils.getItemStack(this.itemIdString);
        if(stack.isPresent()) {
            int itemStackSize = 14;
            context.drawItem(
                    stack.get(),
                    startX + width/2 - itemStackSize/2,
                    startY + height/2 - itemStackSize/2
            );
        } else {
            context.drawCenteredTextWithShadow(textRenderer,
                    Text.literal("?:" + this.itemIdString).fillStyle(
                            Style.EMPTY
                                    .withColor(Color.of(78, 180, 231).toRgbInt())
                                    .withBold(true)
                                    .withUnderline(true)
                                    .withItalic(true)
                    ),
                    startX + width/2,
                    startY + width/2 - 10/2,
                    -1
            );
        }
    }
}
