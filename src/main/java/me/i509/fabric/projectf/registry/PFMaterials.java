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

package me.i509.fabric.projectf.registry;

import me.i509.fabric.projectf.util.builder.ArmorMaterialBuilder;
import me.i509.fabric.projectf.util.builder.ToolMaterialBuilder;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;

public final class PFMaterials {
	/*
	public static final ToolMaterial BLACK_MATTER_TOOL = new ToolMaterialBuilder()
			.miningLevel(4)
			.miningSpeed(12.0F)
			.attackDamage(3.25F)
			.enchantability(7)
			.build();

	public static final ToolMaterial RED_MATTER_TOOL = new ToolMaterialBuilder()
			.miningLevel(4)
			.miningSpeed(14.0F)
			.attackDamage(3.55F)
			.enchantability(5)
			.build();

	public static final ArmorMaterial BLACK_MATTER_ARMOR = new ArmorMaterialBuilder()
			.name("black_matter")
			.protectionValues(new int[]{4, 7, 9, 4})
			.enchantability(8)
			.equipSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC)
			.toughness(2.5F)
			.build();

	public static final ArmorMaterial RED_MATTER_ARMOR = new ArmorMaterialBuilder()
			.name("red_matter")
			.protectionValues(new int[]{5, 8, 10, 5})
			.enchantability(7)
			.equipSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC)
			.toughness(3.0F)
			.build();
	*/
	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFMaterials() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
