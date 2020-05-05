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
	default int[] getInvAvailableSlots(Direction side) {
		return this.getInventory().getInvAvailableSlots(side);
	}

	@Override
	default boolean canInsertInvStack(int slot, ItemStack stack, @Nullable Direction dir) {
		return this.getInventory().canInsertInvStack(slot, stack, dir);
	}

	@Override
	default boolean canExtractInvStack(int slot, ItemStack stack, Direction dir) {
		return this.getInventory().canExtractInvStack(slot, stack, dir);
	}
}
