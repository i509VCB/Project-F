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

import java.util.function.Supplier;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

public final class ToolMaterialBuilder {
	private int durability;
	private float miningSpeed;
	private float attackDmg;
	private int miningLevel;
	private int enchantability;
	private Ingredient ingredient = Ingredient.EMPTY;

	public ToolMaterialBuilder miningSpeed(float miningSpeed) {
		this.miningSpeed = miningSpeed;
		return this;
	}

	public ToolMaterialBuilder attackDamage(float atkDamage) {
		this.attackDmg = atkDamage;
		return this;
	}

	public ToolMaterialBuilder miningLevel(int miningLevel) {
		this.miningLevel = miningLevel;
		return this;
	}

	public ToolMaterialBuilder enchantability(int enchantability) {
		this.enchantability = enchantability;
		return this;
	}

	public ToolMaterialBuilder repairIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
		return this;
	}

	public ToolMaterial build() {
		return new ToolMaterialBuilder.Material(this.durability, this.miningSpeed, this.attackDmg, this.miningLevel, this.enchantability, () -> this.ingredient);
	}

	static final class Material implements ToolMaterial {
		private final int durability;
		private final float miningSpeed;
		private final float attackDamage;
		private final int miningLevel;
		private final int enchantability;
		private final Lazy<Ingredient> ingredientSupplier;

		public Material(int durability, float miningSpeed, float attackDmg, int miningLevel, int enchantability, Supplier<Ingredient> repairIngredient) {
			this.durability = durability;
			this.miningSpeed = miningSpeed;
			this.attackDamage = attackDmg;
			this.miningLevel = miningLevel;
			this.enchantability = enchantability;
			this.ingredientSupplier = new Lazy<>(repairIngredient);
		}

		@Override
		public int getDurability() {
			return this.durability;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return this.miningSpeed;
		}

		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}

		@Override
		public int getMiningLevel() {
			return this.miningLevel;
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return this.ingredientSupplier.get();
		}
	}
}
