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

package me.i509.fabric.projectf.block;

import me.i509.fabric.projectf.api.damage.source.FMCFluidDamageSource;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DamagingBaseFluidBlock extends FluidBlock {
	public DamagingBaseFluidBlock(BaseFluid fluid, Settings settings) {
		super(fluid, settings);
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (world.isClient()) {
			super.onEntityCollision(state, world, pos, entity);
			return;
		}

		int level = state.get(FluidBlock.LEVEL);
		IntProperty property = (IntProperty) stateManager.getProperty(FluidBlock.LEVEL.getName());
		int maximumValue = property.getValues().stream().mapToInt(i -> i).max().getAsInt();

		if (entity instanceof LivingEntity) {
			boolean isSafe = false;

			if (entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				// TODO: Protection item a player just need in their inventory
				// Trinkets for example is implemented via the external slots check.
				//isSafe = ProjectFHooks.testFluidSafetyCompat(player);
			}

			if (!isSafe) {
				float multiplier = (float) level / (float) maximumValue;
				entity.damage(FMCFluidDamageSource.fmcFluidDamage(level), level > 0 ? 4.0F * multiplier : 4.0F);
			}
		}

		super.onEntityCollision(state, world, pos, entity);
	}
}
