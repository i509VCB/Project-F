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

import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.recipe.PhilosophersStoneBlockRecipe;
import me.i509.fabric.projectf.recipe.serializer.PhilsophersStoneBlockRecipeSerializer;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

public final class PFRecipeSerializers {
	public static final RecipeSerializer<PhilosophersStoneBlockRecipe> PHILOSOPHERS_STONE_BLOCK = register("philosphers_stone_block", new PhilsophersStoneBlockRecipeSerializer());

	public static <C extends Inventory, T extends Recipe<C>> RecipeSerializer<T> register(String name, RecipeSerializer<T> serializer) {
		return Registry.register(Registry.RECIPE_SERIALIZER, ProjectF.id(name), serializer);
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFRecipeSerializers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
