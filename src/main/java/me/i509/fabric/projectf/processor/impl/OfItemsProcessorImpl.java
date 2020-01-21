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

import java.util.List;
import java.util.stream.Collectors;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.api.processor.type.OfItemsProcessor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class OfItemsProcessorImpl implements OfItemsProcessor {
	private final List<Item> items;

	public OfItemsProcessorImpl(List<Item> items) {
		this.items = items;
	}

	@Override
	public boolean isRecursive() {
		boolean detectedRecursion = false;

		for (Item item : items) {
			Processor itemProc = ProjectF.getInstance().getFMCManager().getProcessor(item);

			if (itemProc.equals(this)) { // If the processor of one of the dependant items, is ourself, we are recursive.
				return true;
			} else {
				detectedRecursion = itemProc.isRecursive();
			}

			if (detectedRecursion) {
				break;
			}
		}

		return detectedRecursion;
	}

	@Override
	public List<Item> getItems() {
		return this.items;
	}

	@Override
	public long process(ItemStack stack) {
		List<Processor> processors = this.items.stream().map(ProjectF.getInstance().getFMCManager()::getProcessor).collect(Collectors.toList());
		long fmcValue = 0;
		int posTracker = 0;

		for (Processor processor : processors) {
			fmcValue += processor.process(new ItemStack(this.items.get(posTracker)));
			posTracker++;
		}

		return fmcValue;
	}

	@Override
	public Identifier getId() {
		return ProjectF.id("of_items");
	}
}
