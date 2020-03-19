package me.i509.fabric.projectf.inventory.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class OneItemSlot extends Slot {
	public OneItemSlot(Inventory inventory, int invSlot, int xPosition, int yPosition) {
		super(inventory, invSlot, xPosition, yPosition);
	}

	public int getMaxStackAmount() {
		return 1;
	}
}
