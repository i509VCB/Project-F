package me.i509.fabric.projectf.block.entity;

import me.i509.fabric.projectf.base.block.entity.inventory.AbstractLockableInventoryBlockEntity;
import me.i509.fabric.projectf.inventory.AlchemicalChestInventory;
import me.i509.fabric.projectf.registry.PFBlockEntities;
import me.i509.fabric.projectf.registry.PFContainers;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;

public class AlchemicalChestBlockEntity extends AbstractLockableInventoryBlockEntity<AlchemicalChestInventory> implements LockableBlockEntity, Nameable {
	public AlchemicalChestBlockEntity() {
		super(PFBlockEntities.ALCHEMICAL_CHEST, new AlchemicalChestInventory());
	}

	@Override
	public void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName) {
		ContainerProviderRegistry.INSTANCE.openContainer(PFContainers.ALCHEMICAL_CHEST, playerEntity, packetByteBuf -> {
			packetByteBuf.writeText(displayName);
			packetByteBuf.writeBlockPos(pos);
		});
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("projectf.blockentity.alchemical_chest");
	}
}
