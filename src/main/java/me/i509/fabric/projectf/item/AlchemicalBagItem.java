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

package me.i509.fabric.projectf.item;

import me.i509.fabric.projectf.bridge.AlchemicalBagBridge_PlayerEntity;
import me.i509.fabric.projectf.inventory.AlchemicalBagInventory;
import me.i509.fabric.projectf.registry.PFContainers;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AlchemicalBagItem extends Item {
	private final DyeColor color;

	public AlchemicalBagItem(DyeColor color, Settings settings) {
		super(settings);
		this.color = color;
	}

	public DyeColor getColor() {
		return color;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient()) {
			return super.use(world, user, hand);
		}

		ContainerProviderRegistry.INSTANCE.openContainer(PFContainers.ALCHEMICAL_BAG, user, this::writeData);

		return TypedActionResult.success(user.getStackInHand(hand));
	}

	private void writeData(PacketByteBuf byteBuf) {
		byteBuf.writeEnumConstant(this.getColor());
	}

	public static AlchemicalBagInventory getInventory(PlayerEntity playerEntity, DyeColor color) {
		return ((AlchemicalBagBridge_PlayerEntity) playerEntity).bridge$getInventory(color);
	}
}
