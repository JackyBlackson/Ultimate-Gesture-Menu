package com.jackyblackson.modfabric.ultimate.gesture.menu;

import com.jackyblackson.modfabric.ultimate.gesture.menu.bstats.BstatsMetrics;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.GestureMenuManager;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.MenuItemCoordinate;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.action.OpenMenuAction;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.item.DefaultMenuItem;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.TestGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.menu.impl.menu.UserGestureMenu;
import com.jackyblackson.modfabric.ultimate.gesture.menu.util.Color;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class UltimateGestureMenuClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());

		int pluginId = 23175; // <-- Replace with the id of your plugin!
		BstatsMetrics metrics = new BstatsMetrics(Reference.MOD_ID, pluginId, true, true, true);

		setupTestEnvironment();
	}


	private void setupTestEnvironment() {
		TestGestureMenu menu1 = new TestGestureMenu("menu1", null);
		//menu1.init();
		var openMenu2Item = new DefaultMenuItem();
		openMenu2Item.setItemIdString("command_block");
		openMenu2Item.setBackgroundColor(Color.of(154, 169, 255, 200));
		openMenu2Item.addAction(new OpenMenuAction("menu2"));
		menu1.setItem(new MenuItemCoordinate(-2, 1), openMenu2Item);

		TestGestureMenu menu2 = new TestGestureMenu("menu2", null);
		//menu1.init();

		GestureMenuManager.getInstance().addMenu("menu1", menu1);
		GestureMenuManager.getInstance().addMenu("menu2", menu2);
		GestureMenuManager.getInstance().setCurrentMenu("menu1");
	}
}