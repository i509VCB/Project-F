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

package me.i509.fabric.projectf.processor.impl;

import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class PercentageOfProcessorImpl implements PercentageOfProcessor {
	private final long divisor;
	private final Processor processor;

	public PercentageOfProcessorImpl(Processor processor, long divisor) {
		this.processor = processor;
		this.divisor = divisor;
	}

	@Override
	public boolean isRecursive() {
		if (this.processor.equals(this)) {
			return true;
		}

		return this.processor.isRecursive();
	}

	@Override
	public long process(ItemStack stack) {
		return this.processor.process(stack) / this.divisor;
	}

	@Override
	public Identifier getId() {
		return ProjectF.id("percentage_of");
	}

	@Override
	public Processor getProcessor() {
		return this.processor;
	}

	@Override
	public long getDivisor() {
		return this.divisor;
	}
}
