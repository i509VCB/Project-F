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

import me.i509.fabric.projectf.inventory.AlchemicalBagInventory;
import me.i509.fabric.projectf.inventory.slot.AlchemicalBagSlot;
import me.i509.fabric.projectf.item.AlchemicalBagItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class AlchemicalBagScreenHandler extends AbstractChestLikeScreenHandler<AlchemicalBagInventory> {
	public AlchemicalBagScreenHandler(int syncId, PlayerInventory playerInventory, AlchemicalBagInventory inventory) {
		super(syncId, AlchemicalBagSlot::new, playerInventory, inventory, new TranslatableText("item.projectf.alchemical_bag", inventory.getColor().toString()), 3);
	}

	public DyeColor getColor() {
		return this.inventory.getColor();
	}

	public static ScreenHandler create(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf byteBuf) {
		DyeColor bagColor = byteBuf.readEnumConstant(DyeColor.class);
		AlchemicalBagInventory inventory = AlchemicalBagItem.getInventory(playerEntity, bagColor);
		return new AlchemicalBagScreenHandler(syncId, playerEntity.inventory, inventory);
	}
}
