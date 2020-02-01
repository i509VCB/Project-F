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

package me.i509.fabric.projectf.item.template;

import static net.minecraft.item.ArmorItem.DISPENSER_BEHAVIOR;
import me.i509.fabric.projectf.api.item.EquippableItem;
import me.i509.fabric.projectf.api.item.DurabilityProvider;
import me.i509.fabric.projectf.api.item.ContextualProtectionItem;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class AbstractContextualArmorItem extends Item implements ContextualProtectionItem, DurabilityProvider, EquippableItem {
	private final EquipmentSlot slot;

	public AbstractContextualArmorItem(EquipmentSlot slot, Settings settings) {
		super(settings);
		this.slot = slot;
		DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
	}

	@Override
	public EquipmentSlot getEquippableSlot(ItemStack item) {
		return this.slot;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack handStack = user.getStackInHand(hand);
		EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(handStack);
		ItemStack targetSlot = user.getEquippedStack(equipmentSlot);

		if (targetSlot.isEmpty()) {
			user.equipStack(equipmentSlot, handStack.copy());
			handStack.setCount(0);
			return TypedActionResult.success(handStack);
		} else {
			return TypedActionResult.fail(handStack);
		}
	}
}
