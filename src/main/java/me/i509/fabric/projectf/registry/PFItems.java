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
import me.i509.fabric.projectf.item.AlchemicalBagItem;
import me.i509.fabric.projectf.item.MatterStarItem;
import me.i509.fabric.projectf.item.MatterGunItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

public final class PFItems {
	public static final BucketItem FMC_BUCKET = register("fmc_bucket", new BucketItem(PFFluids.STILL_FMC, PFItemSettings.BUCKET));
	public static final MatterGunItem MATTER_GUN = register("matter_gun", new MatterGunItem(PFItemSettings.FMC_ITEM));

	// Matter Stars

	public static final MatterStarItem MATTER_STAR_1 = register("matter_star_1", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_1));
	public static final MatterStarItem MATTER_STAR_2 = register("matter_star_2", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_2));
	public static final MatterStarItem MATTER_STAR_3 = register("matter_star_3", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_3));
	public static final MatterStarItem MATTER_STAR_4 = register("matter_star_4", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_4));
	public static final MatterStarItem MATTER_STAR_5 = register("matter_star_5", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_5));
	public static final MatterStarItem MATTER_STAR_6 = register("matter_star_6", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_6));
	public static final MatterStarItem MATTER_STAR_7 = register("matter_star_7", new MatterStarItem(PFItemSettings.MATTER_STAR, MatterStarItem.LEVEL_7));

	// Alchemical Bags

	public static final AlchemicalBagItem WHITE_ALCHEMICAL_BAG = register("white_alchemical_bag", new AlchemicalBagItem(DyeColor.WHITE, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem ORANGE_ALCHEMICAL_BAG = register("orange_alchemical_bag", new AlchemicalBagItem(DyeColor.ORANGE, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem MAGENTA_ALCHEMICAL_BAG = register("magenta_alchemical_bag", new AlchemicalBagItem(DyeColor.MAGENTA, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem LIGHT_BLUE_ALCHEMICAL_BAG = register("light_blue_alchemical_bag", new AlchemicalBagItem(DyeColor.LIGHT_BLUE, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem YELLOW_ALCHEMICAL_BAG = register("yellow_alchemical_bag", new AlchemicalBagItem(DyeColor.YELLOW, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem LIME_ALCHEMICAL_BAG = register("lime_alchemical_bag", new AlchemicalBagItem(DyeColor.LIME, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem PINK_ALCHEMICAL_BAG = register("pink_alchemical_bag", new AlchemicalBagItem(DyeColor.PINK, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem GRAY_ALCHEMICAL_BAG = register("gray_alchemical_bag", new AlchemicalBagItem(DyeColor.GRAY, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem LIGHT_GRAY_ALCHEMICAL_BAG = register("light_gray_alchemical_bag", new AlchemicalBagItem(DyeColor.LIGHT_GRAY, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem CYAN_ALCHEMICAL_BAG = register("cyan_alchemical_bag", new AlchemicalBagItem(DyeColor.CYAN, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem PURPLE_ALCHEMICAL_BAG = register("purple_alchemical_bag", new AlchemicalBagItem(DyeColor.PURPLE, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem BLUE_ALCHEMICAL_BAG = register("blue_alchemical_bag", new AlchemicalBagItem(DyeColor.BLUE, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem BROWN_ALCHEMICAL_BAG = register("brown_alchemical_bag", new AlchemicalBagItem(DyeColor.BROWN, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem GREEN_ALCHEMICAL_BAG = register("green_alchemical_bag", new AlchemicalBagItem(DyeColor.GREEN, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem RED_ALCHEMICAL_BAG = register("red_alchemical_bag", new AlchemicalBagItem(DyeColor.RED, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));
	public static final AlchemicalBagItem BLACK_ALCHEMICAL_BAG = register("black_alchemical_bag", new AlchemicalBagItem(DyeColor.BLACK, PFItemSettings.ALCHEMICAL_BAG_SETTINGS));

	public static <I extends Item> I register(String path, I item) {
		return Registry.register(Registry.ITEM, ProjectF.id(path), item);
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFItems() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
