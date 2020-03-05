package me.i509.fabric.projectf.inventory;

import me.i509.fabric.projectf.api.inventory.SerializableInventory;
import net.minecraft.inventory.BasicInventory;

public class AlchemicalChestInventory extends BasicInventory implements SerializableInventory {
	public AlchemicalChestInventory() {
		super(63);
	}
}
