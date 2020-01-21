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

package me.i509.fabric.projectf.api.processor;

import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.type.AddProcessor;
import me.i509.fabric.projectf.api.processor.factory.AddProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.ConstantProcessor;
import me.i509.fabric.projectf.api.processor.factory.ConstantProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.DurabilityPercentageProcessor;
import me.i509.fabric.projectf.api.processor.factory.DurabilityPercentageProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.OfItemsProcessor;
import me.i509.fabric.projectf.api.processor.factory.OfItemsProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import me.i509.fabric.projectf.api.processor.factory.PercentageOfProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.Processor;

/**
 * Represents an enumeration of all builtin processors.
 */
public final class Processors {
	/**
	 * Represents a {@link Processor} whose value is a combination of two {@link Processor}s.
	 */
	public static final AddProcessorFactory ADD = ProjectF.getInstance().getProcessorRegistry().get(AddProcessor.class);

	/**
	 * Represents a {@link Processor} which has a constant value.
	 */
	public static final ConstantProcessorFactory CONSTANT = ProjectF.getInstance().getProcessorRegistry().get(ConstantProcessor.class);

	/**
	 * Represents a {@link Processor} whose value is a constant value, which is scaled by the item's durability.
	 */
	public static final DurabilityPercentageProcessorFactory DURABILITY_PERCENTAGE = ProjectF.getInstance().getProcessorRegistry().get(DurabilityPercentageProcessor.class);

	/**
	 * Represents a {@link Processor} whose value is the {@link Processor}s of other items.
	 *
	 * <p>Note this processor will be ignored if it is detected to be recursive.</p>
	 */
	public static final OfItemsProcessorFactory OF_ITEMS = ProjectF.getInstance().getProcessorRegistry().get(OfItemsProcessor.class);

	/**
	 * Represents a {@link Processor} whoses result is a part of another {@link Processor}'s value.
	 */
	public static final PercentageOfProcessorFactory PERCENTAGE_OF = ProjectF.getInstance().getProcessorRegistry().get(PercentageOfProcessor.class);
}
