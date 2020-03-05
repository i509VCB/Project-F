package me.i509.fabric.projectf.base.block.entity.inventory;

import me.i509.fabric.projectf.api.block.entity.BlockEntityInventoryProvider;
import me.i509.fabric.projectf.api.inventory.SerializableInventory;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public abstract class AbstractInventoryBlockEntity<I extends SerializableInventory> extends BlockEntity implements BlockEntityInventoryProvider<I> {
	protected final I inventory;

	protected AbstractInventoryBlockEntity(BlockEntityType<?> type, I inventory) {
		super(type);
		this.inventory = inventory;
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		ListTag inventoryTag = this.inventory.getTags();
		tag.put("Inventory", inventoryTag);
		return tag;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.inventory.readTags(tag.getList("Inventory", NbtType.COMPOUND));
	}

	@Override
	public I getInventory() {
		return this.inventory;
	}
}
