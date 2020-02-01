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
import me.i509.fabric.projectf.container.AlchemicalBagContainer;
import me.i509.fabric.projectf.container.AlchemicalChestContainer11x7;
import me.i509.fabric.projectf.util.PFUtils;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public final class PFContainers {
	public static final Identifier ALCHEMICAL_BAG = ProjectF.id("alchemical_bag");
	public static final Identifier ALCHEMICAL_CHEST = ProjectF.id("achemical_chest");
	private static final Unit DUMMY_ALCHEMICAL_BAG_CONTAINER = PFUtils.dummyRegister(() -> ContainerProviderRegistry.INSTANCE.registerFactory(ALCHEMICAL_BAG, AlchemicalBagContainer::create));
	private static final Unit DUMMY_ALCHEMICAL_CHEST_CONTAINER = PFUtils.dummyRegister(() -> ContainerProviderRegistry.INSTANCE.registerFactory(ALCHEMICAL_CHEST, AlchemicalChestContainer11x7::create));

	public static void init() {
		// NO-OP
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFContainers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
