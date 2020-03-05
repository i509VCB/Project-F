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

package me.i509.fabric.projectf.compat;

import me.i509.fabric.projectf.registry.PFFluids;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.injection.Inject;

/**
 * Represents all compatibility hooks. Any mods wishing to add certain condition should {@link Inject @Inject} into one of the static methods.
 */
public class ProjectFHooks {
	/**
	 * Tests where this player should recieve damage from colliding with {@link PFFluids#FLOWING_FMC} or {@link PFFluids#STILL_FMC}.
	 *
	 * <p>This is mainly used by trinkets in case an item exists which stops damage inside of liquid fmc.
	 *
	 * @param player The player to test
	 *
	 * @return Whether this player should take damage.
	 */
	public static boolean testFluidSafetyCompat(ServerPlayerEntity player) {
		return false;
	}
}
