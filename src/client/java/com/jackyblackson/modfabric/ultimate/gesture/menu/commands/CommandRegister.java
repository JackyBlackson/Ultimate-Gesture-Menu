package com.jackyblackson.modfabric.ultimate.gesture.menu.commands;

import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.UserGestureMenu;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class CommandRegister {
    public static void register(
            CommandDispatcher<FabricClientCommandSource> dispatcher,
            CommandRegistryAccess registryAccess
    ) {
        dispatcher.register(literal("gesture-menu")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.translatable("ultimate-gesture-menu.command.feedback"));
                    return 1;
                })
                .then(literal("create")
                        .executes(context -> {
                            context.getSource().sendFeedback(Text.translatable("ultimate-gesture-menu.command.create.feedback"));
                            return 1;
                        })
                        .then(argument("menu_name", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String menuName = StringArgumentType.getString(context, "menu_name");
                                    if(GestureMenuManager.getInstance().isNameOccupied(menuName)) {
                                        context.getSource().sendFeedback(Text.translatable("ultimate-gesture-menu.command.create.fail", menuName));
                                        context.getSource().sendFeedback(Text.translatable("ultimate-gesture-menu.command.create.duplicate", menuName));
                                        return 0;
                                    } else {
                                        GestureMenuManager.getInstance().addMenu(menuName, new UserGestureMenu(menuName));
                                        context.getSource().sendFeedback(Text.translatable("ultimate-gesture-menu.command.create.success", menuName));
                                        return 1;
                                    }
                                })
                        )
                )
        );
    }
}
