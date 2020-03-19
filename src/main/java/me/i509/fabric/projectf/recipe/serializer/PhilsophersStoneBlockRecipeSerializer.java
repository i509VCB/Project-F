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

package me.i509.fabric.projectf.recipe.serializer;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.i509.fabric.projectf.recipe.PhilosophersStoneBlockRecipe;
import net.minecraft.block.Block;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class PhilsophersStoneBlockRecipeSerializer implements RecipeSerializer<PhilosophersStoneBlockRecipe> {
	@Override
	public PhilosophersStoneBlockRecipe read(Identifier id, JsonObject json) {
		String inputBlockName = JsonHelper.getString(json, "input");
		String outputBlockName = JsonHelper.getString(json, "output");
		int durabilityUse = JsonHelper.getInt(json, "damage");

		Block input = Registry.BLOCK.getOrEmpty(Identifier.tryParse(inputBlockName)).orElseThrow(() -> new JsonParseException("Failed to find input block of id:" + inputBlockName));
		Block output = Registry.BLOCK.getOrEmpty(Identifier.tryParse(outputBlockName)).orElseThrow(() -> new JsonParseException("Failed to find input block of id:" + outputBlockName));

		return new PhilosophersStoneBlockRecipe(id, input, output, durabilityUse);
	}

	@Override
	public PhilosophersStoneBlockRecipe read(Identifier id, PacketByteBuf buf) {
		Identifier inputIdentifier = buf.readIdentifier();
		Identifier outputIdentifier = buf.readIdentifier();

		Block input = Registry.BLOCK.getOrEmpty(inputIdentifier).orElseThrow(() -> new JsonParseException("Failed to find input block of id:" + inputIdentifier.toString()));
		Block output = Registry.BLOCK.getOrEmpty(outputIdentifier).orElseThrow(() -> new JsonParseException("Failed to find input block of id:" + outputIdentifier.toString()));

		return new PhilosophersStoneBlockRecipe(id, input, output, buf.readInt());
	}

	@Override
	public void write(PacketByteBuf buf, PhilosophersStoneBlockRecipe recipe) {
		buf.writeIdentifier(Registry.BLOCK.getId(recipe.getInputBlock()));
		buf.writeIdentifier(Registry.BLOCK.getId(recipe.getOutputBlock()));
		buf.writeInt(recipe.getDurabilityUse());
	}
}
