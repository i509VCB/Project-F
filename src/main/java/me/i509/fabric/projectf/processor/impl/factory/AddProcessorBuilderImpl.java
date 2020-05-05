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

package me.i509.fabric.projectf.processor.impl.factory;

import static com.google.common.base.Preconditions.checkNotNull;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.api.processor.type.AddProcessor;
import me.i509.fabric.projectf.api.processor.factory.AddProcessorBuilder;
import me.i509.fabric.projectf.processor.impl.type.AddProcessorImpl;

public class AddProcessorBuilderImpl implements AddProcessorBuilder {
	private Processor first;
	private Processor second;

	@Override
	public AddProcessorBuilder first(Processor processor) {
		this.first = processor;
		return this;
	}

	@Override
	public AddProcessorBuilder second(Processor processor) {
		this.second = processor;
		return this;
	}

	@Override
	public AddProcessor build() {
		checkNotNull(first, "First Processor cannot be null.");
		checkNotNull(second, "Second Processor cannot be null.");
		return new AddProcessorImpl(this.first, this.second);
	}
}
