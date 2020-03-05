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

import java.util.List;
import grondag.fluidity.api.device.ItemComponentContext;
import grondag.fluidity.api.storage.Store;
import grondag.fluidity.base.storage.discrete.DiscreteStore;
import grondag.fluidity.base.storage.discrete.PortableSingleArticleStore;
import grondag.fluidity.base.storage.discrete.SingleArticleStore;
import me.i509.fabric.projectf.api.article.FMCArticle;
import me.i509.fabric.projectf.api.article.FMCArticleProvider;
import me.i509.fabric.projectf.api.item.FMCUsableItem;
import me.i509.fabric.projectf.api.item.VisibleArmorItem;
import me.i509.fabric.projectf.item.template.AbstractContextualArmorItem;
import me.i509.fabric.projectf.item.template.AbstractFMCItem;
import me.i509.fabric.projectf.util.TextMessages;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DefaultedList;
import net.minecraft.world.World;

public abstract class AbstractFMCArmorItem extends AbstractContextualArmorItem implements BasicDurabilityProvider, FMCArticleProvider, FMCUsableItem {
	private final long maxFMC;

	public AbstractFMCArmorItem(ArmorMaterial material, EquipmentSlot slot, long maxFMC, Settings settings) {
		super(material, slot, settings.maxDamage(-1));
		this.maxFMC = maxFMC;
		Store.STORAGE_COMPONENT.registerProvider(this::provideStoreFromContext, this);
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
		// Empty Stack
		stacks.add(new ItemStack(this));

		ItemStack full = new ItemStack(this);

		// And the Full Stack
		SingleArticleStore store = new SingleArticleStore(this.getMaxFMC());
		store.getConsumer().apply(FMCArticle.getArticle(), this.getMaxFMC(), false);
		full.getOrCreateTag();
		full.putSubTag(AbstractFMCItem.KEY, store.writeTag());
		stacks.add(full);
	}

	@Override
	public DiscreteStore provideStoreFromContext(ItemComponentContext ctx) {
		PortableSingleArticleStore store = new PortableSingleArticleStore(this.getMaxFMC(), AbstractFMCItem.KEY, ctx);
		return store;
	}

	@Override
	public long getMaxFMC() {
		return this.maxFMC;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		long value = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		tooltip.add(TextMessages.createFMCItemTooltip(value, this.getMaxFMC()));
	}
}
