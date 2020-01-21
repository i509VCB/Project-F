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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import me.i509.fabric.projectf.api.processor.factory.PercentageOfProcessorFactory;

public class PercentageOfProcessorFactoryImpl implements PercentageOfProcessorFactory {
	private Processor processor;
	private long divisor;

	@Override
	public PercentageOfProcessorFactory processor(Processor processor) {
		this.processor = processor;
		return this;
	}

	@Override
	public PercentageOfProcessorFactory divisor(long divisor) {
		this.divisor = divisor;
		return this;
	}

	@Override
	public PercentageOfProcessor create() {
		checkNotNull(processor, "Processor cannot be null");
		checkArgument(divisor > 0, "Divisor must be greater than 0");
		return new PercentageOfProcessorImpl(this.processor, this.divisor);
	}
}
