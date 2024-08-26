package com.jackyblackson.modfabric.ultimate.gesture.menu.gui;

import com.jackyblackson.modfabric.ultimate.gesture.menu.Reference;
import fi.dy.masa.malilib.gui.MaLiLibIcons;
import fi.dy.masa.malilib.gui.interfaces.IGuiIcon;
import fi.dy.masa.malilib.render.RenderUtils;
import net.minecraft.util.Identifier;

public class CustomGuiIcons {
    public static IGuiIcon CHECKBOX_CHECKED = new Icon(
            0, 0, 8, 8,
            new Identifier(Reference.MOD_ID, "gui/icon/toggle_box/checked.png")
    );

    public static IGuiIcon CHECKBOX_UNCHECKED = new Icon(
            0, 0, 8, 8,
            new Identifier(Reference.MOD_ID, "gui/icon/toggle_box/unchecked.png")
    );

    public static class Icon implements IGuiIcon {

        private final int u;
        private final int v;
        private final int w;
        private final int h;
        private final int hoverOffU;
        private final int hoverOffV;

        private final Identifier texture;

        public Icon(int u, int v, int w, int h, Identifier texture)
        {
            this(u, v, w, h, w, 0, texture);
        }

        public Icon(int u, int v, int w, int h, int hoverOffU, int hoverOffV, Identifier texture)
        {
            this.u = u;
            this.v = v;
            this.w = w;
            this.h = h;
            this.hoverOffU = hoverOffU;
            this.hoverOffV = hoverOffV;
            this.texture = texture;
        }

        @Override
        public int getWidth()
        {
            return this.w;
        }

        @Override
        public int getHeight()
        {
            return this.h;
        }

        @Override
        public int getU()
        {
            return this.u;
        }

        @Override
        public int getV()
        {
            return this.v;
        }

        @Override
        public void renderAt(int x, int y, float zLevel, boolean enabled, boolean selected)
        {
            int u = this.u;
            int v = this.v;

            if (enabled)
            {
                u += this.hoverOffU;
                v += this.hoverOffV;
            }

            if (selected)
            {
                u += this.hoverOffU;
                v += this.hoverOffV;
            }

            RenderUtils.drawTexturedRect(x, y, u, v, this.w, this.h, zLevel);
        }

        @Override
        public Identifier getTexture()
        {
            return this.texture;
        }
    }
}
