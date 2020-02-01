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

package me.i509.fabric.projectf.item.armor.matter.dark;

import grondag.fluidity.base.storage.discrete.PortableSingleArticleStore;
import me.i509.fabric.projectf.api.item.ContextualProtectionItem;
import me.i509.fabric.projectf.item.template.AbstractFMCItem;

public interface DarkMatterProtectionContexts {
	ContextualProtectionItem HELMET = (livingEntity, stack) -> {
		long amount = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		return amount > 0 ? 5 : 0;
	};

	ContextualProtectionItem CHEST = (livingEntity, stack) -> {
		long amount = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		return amount > 0 ? 9 : 0;
	};

	ContextualProtectionItem LEGS = (livingEntity, stack) -> {
		long amount = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		return amount > 0 ? 8 : 0;
	};

	ContextualProtectionItem BOOTS = (livingEntity, stack) -> {
		long amount = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		return amount > 0 ? 6 : 0;
	};
}
