package me.i509.fabric.projectf.block.entity;

import java.util.function.LongSupplier;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.article.FMCArticle;
import me.i509.fabric.projectf.base.block.entity.AbstractLockableInventoryFMCBlockEntity;
import me.i509.fabric.projectf.inventory.MatterCondenserInventory;
import me.i509.fabric.projectf.registry.PFBlockEntities;
import me.i509.fabric.projectf.registry.PFContainers;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

public class MatterCondenserBlockEntity extends AbstractLockableInventoryFMCBlockEntity<MatterCondenserInventory> implements BlockEntityClientSerializable, LockableBlockEntity, Tickable {
	private ContainerLock lock;
	//
	private long clientFMCValue = 0L;

	public MatterCondenserBlockEntity() {
		super(PFBlockEntities.MATTER_CONDENSER, new MatterCondenserInventory(), ProjectF.getInstance().getConfig().getBlockEntitySection().getMatterCondenserMaxStorage());
		this.store.onDirty(this::sync);
	}

	@Override
	public void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName) {
		ContainerProviderRegistry.INSTANCE.openContainer(PFContainers.MATTER_CONDENSER, playerEntity, packetByteBuf -> {
			packetByteBuf.writeText(displayName);
			packetByteBuf.writeBlockPos(pos);
		});
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("projectf.blockentity.matter_condenser");
	}

	@Override
	public void fromClientTag(CompoundTag compoundTag) {
		this.clientFMCValue = compoundTag.getLong("fmc");
	}

	@Override
	public CompoundTag toClientTag(CompoundTag compoundTag) {
		compoundTag.putLong("fmc", this.getStore().count());
		return compoundTag;
	}

	public LongSupplier getFmcSupplier() {
		return () -> this.clientFMCValue;
	}

	@Override
	public void tick() {
		this.store.getSupplier().apply(FMCArticle.getArticle(), 1, false);
	}
}
