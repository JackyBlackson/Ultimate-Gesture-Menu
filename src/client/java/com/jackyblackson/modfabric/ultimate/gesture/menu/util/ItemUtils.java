package com.jackyblackson.modfabric.ultimate.gesture.menu.util;

import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import javax.swing.text.html.Option;
import java.util.Optional;

public class ItemUtils {
    public static Optional<ItemStack> getItemStack(String itemIdString) {
        Identifier itemId = Identifier.tryParse(itemIdString);
        if (itemId == null || !Registries.ITEM.containsId(itemId)) {
            return Optional.empty();
        } else {
            return Optional.of(new ItemStack(Registries.ITEM.get(itemId)));
        }
    }
}
