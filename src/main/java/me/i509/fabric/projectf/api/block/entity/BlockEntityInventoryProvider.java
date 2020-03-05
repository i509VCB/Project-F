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

package me.i509.fabric.projectf.api.block.entity;

import java.util.Optional;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BlockEntityInventoryProvider<I extends Inventory> {
	Identifier CONTENTS = new Identifier("contents");

	void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName);

	I getInventory();

	public static <I extends Inventory> Optional<I> getInventory(Class<I> inventoryClazz, World world, BlockPos blockPos) {
		BlockEntity blockEntity = world.getBlockEntity(blockPos);

		if (blockEntity instanceof BlockEntityInventoryProvider) {
			if (((BlockEntityInventoryProvider) blockEntity).getInventory().getClass().isAssignableFrom(inventoryClazz)) {
				return Optional.of((I) ((BlockEntityInventoryProvider) blockEntity).getInventory());
			}
		}

		return Optional.empty();
	}
}
