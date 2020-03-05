package me.i509.fabric.projectf.client.screen;

import me.i509.fabric.projectf.container.AlchemicalChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class AlchemicalChestScreen extends AbstractChestLikeScreen<AlchemicalChestContainer> {
	public AlchemicalChestScreen(AlchemicalChestContainer container, PlayerInventory playerInventory, Text name) {
		super(container, playerInventory, name);
	}

	public static AlchemicalChestScreen create(AlchemicalChestContainer container) {
		return new AlchemicalChestScreen(container, container.getPlayerInventory(), container.getTitle());
	}
}
