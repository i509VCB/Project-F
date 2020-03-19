package me.i509.fabric.projectf.client.screen;

import me.i509.fabric.projectf.container.AlchemicalChestScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class AlchemicalChestScreen extends AbstractChestLikeScreen<AlchemicalChestScreenHandler> {
	public AlchemicalChestScreen(AlchemicalChestScreenHandler container, PlayerInventory playerInventory, Text name) {
		super(container, playerInventory, name);
	}

	public static AlchemicalChestScreen create(AlchemicalChestScreenHandler container) {
		return new AlchemicalChestScreen(container, container.getPlayerInventory(), container.getTitle());
	}
}
