package me.i509.fabric.projectf.api.block.entity;

import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface SidedBlockEntityInventoryProvider<S extends SidedInventory> extends BlockEntityInventoryProvider<S>, SidedInventory {
	@Override
	S getInventory();

	// SIDED INVENTORY METHODS

	@Override
	default int[] getAvailableSlots(Direction side) {
		return this.getInventory().getAvailableSlots(side);
	}

	@Override
	default boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return this.getInventory().canInsert(slot, stack, dir);
	}

	@Override
	default boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return this.getInventory().canExtract(slot, stack, dir);
	}
}
