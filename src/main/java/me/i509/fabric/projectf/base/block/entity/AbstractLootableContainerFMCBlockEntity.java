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

package me.i509.fabric.projectf.base.block.entity;

import grondag.fluidity.base.storage.discrete.SingleArticleStore;
import me.i509.fabric.projectf.api.block.entity.BlockEntityInventoryProvider;
import me.i509.fabric.projectf.api.block.entity.FMCBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;

public abstract class AbstractLootableContainerFMCBlockEntity extends LootableContainerBlockEntity implements FMCBlockEntity, BlockEntityInventoryProvider {
	private final long capacity;
	protected final SingleArticleStore store;

	protected AbstractLootableContainerFMCBlockEntity(BlockEntityType<? extends AbstractLootableContainerFMCBlockEntity> type, long capacity) {
		super(type);
		this.capacity = capacity;
		this.store = new SingleArticleStore(this.capacity);
	}

	@Override
	public SingleArticleStore getStore() {
		return this.store;
	}

	@Override
	protected final Container createContainer(int syncId, PlayerInventory playerInventory) {
		return null; // We use Fabric's API instead.
	}
}
