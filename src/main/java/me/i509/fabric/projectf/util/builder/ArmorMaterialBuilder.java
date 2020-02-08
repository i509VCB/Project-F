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

package me.i509.fabric.projectf.util.builder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.function.Supplier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Lazy;

public class ArmorMaterialBuilder {
	private String name;
	private int[] protectionAmounts;
	private int enchantability;
	private SoundEvent soundEvent;
	private float toughness = 0.0F;
	private Supplier<Ingredient> ingredient = () -> Ingredient.EMPTY;

	public ArmorMaterialBuilder name(String name) {
		this.name = checkNotNull(name, "Name cannot be null");
		return this;
	}

	public ArmorMaterialBuilder protectionValues(int[] protectionAmounts) {
		checkArgument(protectionAmounts.length == 4, "Specified array for protection amounts must be 4 values long");
		this.protectionAmounts = protectionAmounts;
		return this;
	}

	public ArmorMaterialBuilder enchantability(int enchantability) {
		this.enchantability = enchantability;
		return this;
	}

	public ArmorMaterialBuilder equipSound(SoundEvent event) {
		this.soundEvent = checkNotNull(event, "Equip sound cannot be null");
		return this;
	}

	public ArmorMaterialBuilder toughness(float toughness) {
		this.toughness = toughness;
		return this;
	}

	public ArmorMaterialBuilder ingredient(Ingredient ingredient) {
		Ingredient temp = checkNotNull(ingredient, "Cannot have null ingredient. Use Ingredient.EMPTY instead");
		this.ingredient = () -> temp;
		return this;
	}

	public ArmorMaterial build() {
		checkNotNull(name, "Armor Material Name cannot be null");
		checkNotNull(soundEvent, "Sound Event cannot be null");
		return new Material(this.name, this.protectionAmounts, this.enchantability, this.soundEvent, this.toughness, this.ingredient);
	}

	static final class Material implements ArmorMaterial {
		private final String name;
		private final int[] protectionAmounts;
		private final int enchantability;
		private final SoundEvent equipSound;
		private final float toughness;
		private final Lazy<Ingredient> ingredientSupplier;

		Material(String name, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, Supplier<Ingredient> ingredientSupplier) {
			this.name = name;
			this.protectionAmounts = protectionAmounts;
			this.enchantability = enchantability;
			this.equipSound = equipSound;
			this.toughness = toughness;
			this.ingredientSupplier = new Lazy<>(ingredientSupplier);
		}

		@Override
		public int getDurability(EquipmentSlot slot) {
			return 100;
		}

		@Override
		public int getProtectionAmount(EquipmentSlot slot) {
			return this.protectionAmounts[slot.getEntitySlotId()];
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public SoundEvent getEquipSound() {
			return this.equipSound;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return this.ingredientSupplier.get();
		}

		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}

		@Override
		public float method_24355() {
			return 0;
		}
	}
}
