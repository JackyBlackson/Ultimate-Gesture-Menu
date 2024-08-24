package com.jackyblackson.modfabric.ultimate.gesture.menu.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Color {
    private int r;
    private int g;
    private int b;
    private int a;

    public static Color of(int r, int g, int b) {
        return new Color(r, g, b);
    }

    public static Color of(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }

    public static Color of(String hexColor) {
        return new Color(hexColor);
    }

    private Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }

    private Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    private Color(String hexColor) {
        var color = ColorUtils.hexToRGBA(hexColor);
        assert color.length == 4;
        this.r = color[0];
        this.g = color[1];
        this.b = color[2];
        this.a = color[3];
    }

    public int toRgbInt() {
        return ColorUtils.rgbToInt(r, g, b);
    }

    public int toRgbAInt() {
        return ColorUtils.rgbaToInt(r, g, b, a);
    }

    public String toHexColor() {
        return ColorUtils.rgbaToHex(r,g,b,a);
    }

    public Color withAlpha(int alpha) {
        return new Color(r, g, b, alpha);
    }
}
