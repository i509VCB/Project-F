package me.i509.fabric.projectf.api.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public interface SerializableInventory extends Inventory {
	default void readTags(ListTag listTag) {
		for (int slot = 0; slot < this.size(); ++slot) {
			this.setStack(slot, ItemStack.EMPTY);
		}

		for (int slot = 0; slot < listTag.size(); ++slot) {
			CompoundTag compoundTag = listTag.getCompound(slot);
			int k = compoundTag.getByte("Slot") & 255;

			if (k < this.size()) {
				this.setStack(k, ItemStack.fromTag(compoundTag));
			}
		}
	}

	default ListTag getTags() {
		ListTag listTag = new ListTag();

		for (int slot = 0; slot < this.size(); ++slot) {
			ItemStack itemStack = this.getStack(slot);

			if (!itemStack.isEmpty()) {
				CompoundTag compoundTag = new CompoundTag();
				compoundTag.putByte("Slot", (byte) slot);
				itemStack.toTag(compoundTag);
				listTag.add(compoundTag);
			}
		}

		return listTag;
	}
}
