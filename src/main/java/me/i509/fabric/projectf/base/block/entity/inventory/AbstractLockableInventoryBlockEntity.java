package me.i509.fabric.projectf.base.block.entity.inventory;

import me.i509.fabric.projectf.api.inventory.SerializableInventory;
import me.i509.fabric.projectf.block.entity.LockableBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Nameable;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class AbstractLockableInventoryBlockEntity<I extends SerializableInventory> extends AbstractInventoryBlockEntity<I> implements LockableBlockEntity, Nameable {
	private ContainerLock lock;
	private Text customName;

	protected AbstractLockableInventoryBlockEntity(BlockEntityType<?> type, I inventory) {
		super(type, inventory);
		this.lock = ContainerLock.EMPTY;
	}

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.lock = ContainerLock.fromTag(tag);
		if (tag.contains("CustomName", 8)) {
			this.customName = Text.Serializer.fromJson(tag.getString("CustomName"));
		}

	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		this.lock.toTag(tag);
		if (this.customName != null) {
			tag.putString("CustomName", Text.Serializer.toJson(this.customName));
		}

		return tag;
	}

	@Override
	public Text getName() {
		return this.customName != null ? this.customName : this.getContainerName();
	}

	@Override
	public Text getDisplayName() {
		return this.getName();
	}

	@Override
	@Nullable
	public Text getCustomName() {
		return this.customName;
	}

	@Override
	public boolean checkUnlocked(PlayerEntity player) {
		return checkUnlocked(player, this.lock, this.getDisplayName());
	}

	protected abstract Text getContainerName();

	public static boolean checkUnlocked(PlayerEntity player, ContainerLock lock, Text containerName) {
		if (!player.isSpectator() && !lock.canOpen(player.getMainHandStack())) {
			player.addChatMessage(new TranslatableText("container.isLocked", containerName), true);
			player.playSound(SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return false;
		} else {
			return true;
		}
	}
}
