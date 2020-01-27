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

package me.i509.fabric.projectf.item;

import grondag.fluidity.api.device.ItemComponentContext;
import grondag.fluidity.base.storage.discrete.DiscreteStore;
import grondag.fluidity.base.storage.discrete.PortableSingleArticleStore;
import me.i509.fabric.projectf.item.template.AbstractFMCItem;

public class MatterStarItem extends AbstractFMCItem {
	public static final long LEVEL_1 = 16384;
	public static final long LEVEL_2 = 65536;
	public static final long LEVEL_3 = 262144;
	public static final long LEVEL_4 = 1048576;
	public static final long LEVEL_5 = 4194304;
	public static final long LEVEL_6 = 16777216;
	public static final long LEVEL_7 = 150994944;

	public MatterStarItem(Settings settings, long maxFMC) {
		super(settings, maxFMC);
	}

	@Override
	public DiscreteStore provideStoreFromContext(ItemComponentContext ctx) {
		PortableSingleArticleStore store = new PortableSingleArticleStore(this.getMaxFMC(), AbstractFMCItem.KEY, ctx);
		return store;
	}
}
