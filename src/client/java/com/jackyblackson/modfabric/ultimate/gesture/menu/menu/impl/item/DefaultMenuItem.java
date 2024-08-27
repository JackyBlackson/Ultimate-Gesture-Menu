package com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.SendMessageAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.BaseGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.ItemUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class DefaultMenuItem extends BaseMenuItem {
    @Getter
    @Setter
    private String itemIdString="";

    @Getter
    @Setter
    private Color backgroundColor=Color.of(154, 169, 255, 170);

    @Setter
    @Getter
    private Text text = null;



    @Override
    public void setPosition(MenuItemCoordinate position) {
        super.setPosition(position);
//        this.addAction(new SendMessageAction(
//                "Menu item in [" +
//                        position.getX()
//                        + ", "
//                        + position.getY()
//                        + "] displays a(n) " + this.itemIdString + "."
//        ));
    }

    @Override
    public void render(DrawContext context, TextRenderer textRenderer, int startX, int startY, int width, int height, int mouseX, int mouseY, float delta) {
        context.fill(startX, startY, startX + width, startY + height, this.backgroundColor.toRgbAInt());
        var stack = ItemUtils.getItemStack(this.itemIdString);
        if(stack.isPresent()) {
            int itemStackSize = 14;
            if(this.text != null){
                context.drawItem(
                        stack.get(),
                        startX + width / 2 - itemStackSize / 2,
                        startY + height / 2 - itemStackSize
                );
                context.drawCenteredTextWithShadow(textRenderer,
                        this.text,
                        startX + width/2,
                        startY + width/2 + 2,
                        -1
                );
            } else {
                context.drawItem(
                        stack.get(),
                        startX + width / 2 - itemStackSize / 2,
                        startY + height / 2 - itemStackSize / 2
                );

            }
        } else {
            Text t;
            if (this.text == null) t = Text.literal("UNKNOWN");
            else t = this.text;
            context.drawCenteredTextWithShadow(textRenderer,
                    t,
                    startX + width/2,
                    startY + width/2 - 12/2,
                    -1
            );
        }
    }
}
