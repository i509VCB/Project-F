package me.i509.fabric.projectf.container;

import java.util.Optional;
import me.i509.fabric.projectf.api.block.entity.BlockEntityInventoryProvider;
import me.i509.fabric.projectf.inventory.AlchemicalChestInventory;
import me.i509.fabric.projectf.util.SlotFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class AlchemicalChestScreenHandler extends AbstractChestLikeScreenHandler<AlchemicalChestInventory> {
	protected AlchemicalChestScreenHandler(int syncId, SlotFactory factory, PlayerInventory playerInventory, AlchemicalChestInventory inventory, Text title) {
		super(syncId, factory, playerInventory, inventory, title, 6);
	}

	public static AlchemicalChestScreenHandler create(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf byteBuf) {
		Text title = byteBuf.readText();
		BlockPos pos = byteBuf.readBlockPos();
		Optional<AlchemicalChestInventory> inventory = BlockEntityInventoryProvider.getInventory(AlchemicalChestInventory.class, playerEntity.world, pos);

		return new AlchemicalChestScreenHandler(syncId, Slot::new, playerEntity.inventory, inventory.get(), title);
	}
}
