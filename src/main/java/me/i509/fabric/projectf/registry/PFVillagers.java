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

import com.google.common.collect.ImmutableSet;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.mixin.accessor.VillagerProfessionAccessor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.checkerframework.checker.nullness.qual.Nullable;

public class PFVillagers {
	public static final VillagerProfession ALCHEMIST_PROFESSION = register("alchemist", PFPointOfInterestTypes.ALCHEMIST, ImmutableSet.of(), ImmutableSet.of(), null);

	public static VillagerProfession register(String path, PointOfInterestType type, ImmutableSet<Item> gatherableItems, ImmutableSet<Block> secondaryJobSites, @Nullable SoundEvent soundEvent) {
		return Registry.register(Registry.VILLAGER_PROFESSION, ProjectF.id(path), VillagerProfessionAccessor.accessor$create(ProjectF.id(path).toString(), type, gatherableItems, secondaryJobSites, soundEvent));
	}

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFVillagers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
