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

package me.i509.fabric.projectf.registry;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import me.i509.fabric.projectf.villager.BuyForOneEmeraldFactory;
import me.i509.fabric.projectf.villager.SellItemFactory;
import net.minecraft.village.TradeOffers;

public class PFTradeOffers {
	private static final ImmutableMap<Integer, TradeOffers.Factory[]> ALCHEMIST_TRADES = ImmutableMap.<Integer, TradeOffers.Factory[]>builder()
			.put(1, new TradeOffers.Factory[]{new SellItemFactory(PFItems.MATTER_GUN, 64, 1, 1, 10)})
			.put(2, new TradeOffers.Factory[]{new BuyForOneEmeraldFactory(PFItems.FMC_BUCKET, 2, 4, 1)})
			.build();

	public static void init() {
		// NO-OP
	}

	private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> immutableMap) {
		return new Int2ObjectOpenHashMap<>(immutableMap);
	}

	static {
		TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(PFVillagers.ALCHEMIST_PROFESSION, copyToFastUtilMap(PFTradeOffers.ALCHEMIST_TRADES));
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFTradeOffers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
