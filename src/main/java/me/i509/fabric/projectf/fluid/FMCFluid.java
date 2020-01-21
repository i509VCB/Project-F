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

package me.i509.fabric.projectf.fluid;

import me.i509.fabric.projectf.registry.PFBlocks;
import me.i509.fabric.projectf.registry.PFFluids;
import me.i509.fabric.projectf.registry.PFItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class FMCFluid extends AbstractFiniteFluid {
	@Override
	public Item getBucketItem() {
		return PFItems.FMC_BUCKET;
	}

	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		return PFBlocks.LIQUID_FMC.getDefaultState().with(FluidBlock.LEVEL, this.method_15741(fluidState));
	}

	@Override
	public Fluid getFlowing() {
		return PFFluids.FLOWING_FMC;
	}

	@Override
	public Fluid getStill() {
		return PFFluids.STILL_FMC;
	}

	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid.equals(this.getFlowing()) || this.equals(this.getStill());
	}

	public static class Still extends FMCFluid {
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
	}

	public static class Flowing extends FMCFluid {
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}

		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> stateFactoryBuilder) {
			super.appendProperties(stateFactoryBuilder);
			stateFactoryBuilder.add(LEVEL);
		}
	}
}
