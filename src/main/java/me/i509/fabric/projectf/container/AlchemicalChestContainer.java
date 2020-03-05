package me.i509.fabric.projectf.container;

import java.util.Optional;
import me.i509.fabric.projectf.api.block.entity.BlockEntityInventoryProvider;
import me.i509.fabric.projectf.inventory.AlchemicalChestInventory;
import me.i509.fabric.projectf.util.SlotFactory;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class AlchemicalChestContainer extends AbstractChestLikeContainer<AlchemicalChestInventory> {
	protected AlchemicalChestContainer(int syncId, SlotFactory factory, PlayerInventory playerInventory, AlchemicalChestInventory inventory, Text title) {
		super(syncId, factory, playerInventory, inventory, title, 6);
	}

	public static AlchemicalChestContainer create(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf byteBuf) {
		Text title = byteBuf.readText();
		BlockPos pos = byteBuf.readBlockPos();
		Optional<AlchemicalChestInventory> inventory = BlockEntityInventoryProvider.getInventory(AlchemicalChestInventory.class, playerEntity.world, pos);

		return new AlchemicalChestContainer(syncId, Slot::new, playerEntity.inventory, inventory.get(), title);
	}
}
