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

package me.i509.fabric.projectf.recipe;

import me.i509.fabric.projectf.inventory.PhilosophersStoneBlockInventory;
import me.i509.fabric.projectf.registry.PFRecipeSerializers;
import me.i509.fabric.projectf.registry.PFRecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class PhilosophersStoneBlockRecipe implements Recipe<PhilosophersStoneBlockInventory> {
	private final Identifier id;
	private final Block inputBlock;
	private final Block outputBlock;
	private final int useDurability;

	public PhilosophersStoneBlockRecipe(Identifier id, Block inputBlock, Block outputBlock, int durabilityUse) {
		this.id = id;
		this.inputBlock = inputBlock;
		this.outputBlock = outputBlock;
		this.useDurability = durabilityUse;
	}

	public Block getInputBlock() {
		return this.inputBlock;
	}

	public Block getOutputBlock() {
		return this.outputBlock;
	}

	public int getDurabilityUse() {
		return this.useDurability;
	}

	@Override
	public boolean matches(PhilosophersStoneBlockInventory inv, World world) {
		return inv.getBlock().equals(this.inputBlock);
	}

	@Override
	public ItemStack craft(PhilosophersStoneBlockInventory inv) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean fits(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return PFRecipeSerializers.PHILOSOPHERS_STONE_BLOCK;
	}

	@Override
	public RecipeType<?> getType() {
		return PFRecipeTypes.PHILOSOPHERS_STONE_BLOCK;
	}
}
