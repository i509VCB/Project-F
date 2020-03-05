/*
 * MIT License
 *
 * Copyright (c) 2020 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.projectf.container;

import java.util.Optional;
import java.util.function.LongSupplier;
import me.i509.fabric.projectf.api.block.entity.BlockEntityInventoryProvider;
import me.i509.fabric.projectf.block.entity.MatterCondenserBlockEntity;
import me.i509.fabric.projectf.inventory.MatterCondenserInventory;
import me.i509.fabric.projectf.inventory.slot.OneItemSlot;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;

public class MatterCondenserContainer11x7 extends Container {
	private final PlayerInventory playerInventory;
	private final MatterCondenserInventory inventory;
	private final Text name;
	private LongSupplier longSupplier;

	public MatterCondenserContainer11x7(int syncId, PlayerInventory playerInventory, MatterCondenserInventory inventory, LongSupplier longSupplier, Text name) {
		super(null, syncId);
		this.playerInventory = playerInventory;
		this.inventory = inventory;
		this.name = name;
		this.inventory.onInvOpen(playerInventory.player);
		this.longSupplier = longSupplier;

		this.addSlot(new OneItemSlot(inventory, 0,  5, 5));

		for (int row = 0; row < 6; ++row) {
			for (int column = 0; column < 11; ++column) {
				this.addSlot(new Slot(inventory, column + (row * 11) + 1, (column * 18) + 5, 26 + (row * 18)));
			}
		}

		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, column + row * 9 + 9 + 1, 23 + (column * 18), 102 + row * 18 + 45));
			}
		}

		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, 23 + (column * 18), 160 + 45));
		}
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUseInv(player);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int invSlot) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);

		if (slot != null && slot.hasStack()) {
			ItemStack itemStack2 = slot.getStack();
			itemStack = itemStack2.copy();

			if (invSlot < 77) {
				if (!this.insertItem(itemStack2, 7 * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(itemStack2, 0, 77, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return itemStack;
	}

	@Override
	public void close(PlayerEntity player) {
		super.close(player);
		this.inventory.onInvClose(player);
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public PlayerInventory getPlayerInventory() {
		return this.playerInventory;
	}

	public Text getDisplayName() {
		return this.name;
	}

	public static Container create(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf byteBuf) {
		Text title = byteBuf.readText();
		BlockPos pos = byteBuf.readBlockPos();

		Optional<MatterCondenserInventory> inventory = BlockEntityInventoryProvider.getInventory(MatterCondenserInventory.class, playerEntity.world, pos);
		return new MatterCondenserContainer11x7(syncId, playerEntity.inventory, inventory.get(), ((MatterCondenserBlockEntity) playerEntity.world.getBlockEntity(pos)).getFmcSupplier(), title);
	}

	@Environment(EnvType.CLIENT)
	public LongSupplier getLongSupplier() {
		return this.longSupplier;
	}
}
