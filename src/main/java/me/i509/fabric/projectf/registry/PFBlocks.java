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
import me.i509.fabric.projectf.block.AlchemicalTorchBlock;
import me.i509.fabric.projectf.block.AlchemicalWallTorchBlock;
import me.i509.fabric.projectf.block.DamagingBaseFluidBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public final class PFBlocks {
	public static final Block LIQUID_FMC = registerNoItem("liquid_fmc_block", new DamagingBaseFluidBlock(PFFluids.STILL_FMC, FabricBlockSettings.copy(Blocks.WATER).dropsNothing().build()));
	public static final Block ALCHEMICAL_TORCH = registerNoItem("alchemical_torch", new AlchemicalTorchBlock(FabricBlockSettings.copy(Blocks.TORCH).lightLevel(15).build()));
	public static final Block ALCHEMICAL_WALL_TORCH = registerNoItem("alchemical_wall_torch", new AlchemicalWallTorchBlock(FabricBlockSettings.copy(PFBlocks.ALCHEMICAL_TORCH).lightLevel(15).dropsLike(PFBlocks.ALCHEMICAL_TORCH).build()));

	public static Block registerNoItem(String path, Block block) {
		return Registry.register(Registry.BLOCK, ProjectF.id(path), block);
	}

	public static Block register(String path, Block block, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, ProjectF.id(path), block);
		Registry.register(Registry.ITEM, ProjectF.id(path), new BlockItem(b, settings));
		return b;
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFBlocks() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
