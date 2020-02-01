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
import me.i509.fabric.projectf.api.processor.factory.AddProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.ConstantProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.PercentageOfProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.MaxProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.MinProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.MultiplyProcessorFactory;
import me.i509.fabric.projectf.api.processor.factory.OfItemsProcessorFactory;
import me.i509.fabric.projectf.api.processor.type.AddProcessor;
import me.i509.fabric.projectf.api.processor.type.ConstantProcessor;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import me.i509.fabric.projectf.api.processor.type.MaxProcessor;
import me.i509.fabric.projectf.api.processor.type.MinProcessor;
import me.i509.fabric.projectf.api.processor.type.MultiplyProcessor;
import me.i509.fabric.projectf.api.processor.type.OfItemsProcessor;
import me.i509.fabric.projectf.api.processor.type.Processor;

/**
 * Represents an enumeration of all builtin processor factories.
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
	 * Represents a {@link Processor} whose value is the {@link Processor}s of other items.
	 *
	 * <p>Note this processor will be ignored if it is detected to be recursive.</p>
	 */
	public static final OfItemsProcessorFactory OF_ITEMS = ProjectF.getInstance().getProcessorRegistry().get(OfItemsProcessor.class);

	/**
	 * Represents a {@link Processor} whose value is the maximum of two {@link Processor}s.
	 */
	public static final MaxProcessorFactory MAX = ProjectF.getInstance().getProcessorRegistry().get(MaxProcessor.class);

	/**
	 * Represents a {@link Processor} whose value is the minumum of two {@link Processor}s.
	 */
	public static final MinProcessorFactory MIN = ProjectF.getInstance().getProcessorRegistry().get(MinProcessor.class);

	/**
	 * Represents a {@link Processor} whose value is another {@link Processor} multiplied by a value.
	 */
	public static final MultiplyProcessorFactory MULTIPLY = ProjectF.getInstance().getProcessorRegistry().get(MultiplyProcessor.class);

	/**
	 * Represents a {@link Processor} whose value a percentage of an item's durability.
	 *
	 * <p>Likely you will want to {@link MultiplyProcessor multiply} this value since this is a direct scaling of the item's durability.</p>
	 */
	public static final PercentageOfProcessorFactory PERCENTAGE_OF = ProjectF.getInstance().getProcessorRegistry().get(PercentageOfProcessor.class);
}
