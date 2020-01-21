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

package me.i509.fabric.projectf.villager;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public class SellItemFactory implements TradeOffers.Factory {
	private final ItemStack sell;
	private final int price;
	private final int count;
	private final int maxUses;
	private final int experience;
	private final float multiplier;

	public SellItemFactory(Block block, int price, int count, int maxUses, int experience) {
		this(new ItemStack(block), price, count, maxUses, experience);
	}

	public SellItemFactory(Item item, int price, int count, int experience) {
		this(new ItemStack(item), price, count, 12, experience);
	}

	public SellItemFactory(Item item, int price, int count, int maxUses, int experience) {
		this(new ItemStack(item), price, count, maxUses, experience);
	}

	public SellItemFactory(ItemStack itemStack, int price, int count, int maxUses, int expirence) {
		this(itemStack, price, count, maxUses, expirence, 0.05F);
	}

	public SellItemFactory(ItemStack itemStack, int price, int count, int maxUses, int experience, float multiplier) {
		this.sell = itemStack;
		this.price = price;
		this.count = count;
		this.maxUses = maxUses;
		this.experience = experience;
		this.multiplier = multiplier;
	}

	public TradeOffer create(Entity entity, Random random) {
		return new TradeOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
	}
}
