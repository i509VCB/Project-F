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

package me.i509.fabric.projectf.api.article;

import grondag.fluidity.api.device.DeviceComponentAccess;
import grondag.fluidity.api.device.ItemComponentContext;
import grondag.fluidity.api.storage.Store;
import grondag.fluidity.base.storage.discrete.DiscreteStore;
import me.i509.fabric.projectf.util.Reference;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * Represents an item which can contain an amount of FMC within a DiscreteStore.
 *
 * <p>It is recommended to use one of the abstract implementations instead of making an item from scratch.
 */
public interface FMCItemArticleProvider {
	DiscreteStore provideStoreFromContext(ItemComponentContext ctx);

	long getMaxFMC();

	// Reference<ItemStack> stackRef = new Reference<>(player.getStackInHand(hand));
	// DeviceComponentAccess<Store> access = Store.STORAGE_COMPONENT.getAccessForHeldItem(() -> stackRef.get(), stackRef::set, (ServerPlayerEntity) player);
	default DeviceComponentAccess<Store> getStore(Reference<ItemStack> stackReference, ServerPlayerEntity player) {
		return Store.STORAGE_COMPONENT.getAccessForHeldItem(stackReference::get, stackReference::set, player);
	}
}
