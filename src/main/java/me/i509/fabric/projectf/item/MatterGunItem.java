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
import com.google.common.collect.Multimap;
import grondag.fluidity.api.article.StoredArticleView;
import grondag.fluidity.api.device.DeviceComponentAccess;
import grondag.fluidity.api.device.ItemComponentContext;
import grondag.fluidity.api.storage.Store;
import grondag.fluidity.api.transact.Transaction;
import grondag.fluidity.base.storage.discrete.DiscreteStore;
import grondag.fluidity.base.storage.discrete.PortableSingleArticleStore;
import me.i509.fabric.projectf.api.article.FMCArticle;
import me.i509.fabric.projectf.entity.PlayerTrackedData;
import me.i509.fabric.projectf.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MatterGunItem extends AbstractFMCItem {
	private float attackSpeed = -3.0F;

	public MatterGunItem(Settings settings) {
		super(settings, 20000);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		long value = PortableSingleArticleStore.getAmount(stack, AbstractFMCItem.KEY);
		tooltip.add(new LiteralText(Long.toString(value) + "/" + this.getMaxFMC()));
	}

	@Override
	public Multimap<String, EntityAttributeModifier> getModifiers(EquipmentSlot slot) {
		Multimap<String, EntityAttributeModifier> multimap = super.getModifiers(slot);

		if (slot == EquipmentSlot.MAINHAND) {
			multimap.put(EntityAttributes.ATTACK_SPEED.getId(), new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Tool modifier", this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));
		}

		return multimap;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClient()) {
			if (player.getDataTracker().get(PlayerTrackedData.ANIMATION_PROGRESS) != 20) {
				return TypedActionResult.fail(player.getStackInHand(hand));
			}

			if (player.getDataTracker().get(PlayerTrackedData.MATTER_GUN_DELAY) > 0) {
				return TypedActionResult.fail(player.getStackInHand(hand));
			}

			Reference<ItemStack> stackRef = new Reference<>(player.getStackInHand(hand));
			DeviceComponentAccess<Store> access = Store.STORAGE_COMPONENT.getAccessForHeldItem(() -> stackRef.get(), stackRef::set, (ServerPlayerEntity) player);

			Store store = access.get();

			if (store.isEmpty()) {
				return TypedActionResult.fail(stackRef.get());
			}

			StoredArticleView view = store.view(0);

			if (view.article().isNothing()) {
				return TypedActionResult.fail(stackRef.get());
			}

			try (Transaction transaction = Transaction.open()) {
				transaction.enlistSelf(store);

				if (store.getSupplier().apply(FMCArticle.getArticle(), 200, false) == 200) {
					player.addChatMessage(new LiteralText("yeet"), false);
					player.getDataTracker().set(PlayerTrackedData.MATTER_GUN_DELAY, 20);
					transaction.commit();
					return TypedActionResult.success(stackRef.get());
				} else {
					transaction.rollback();
					return TypedActionResult.fail(stackRef.get());
				}
			}
		}

		return super.use(world, player, hand);
	}

	@Override
	public DiscreteStore provide(ItemComponentContext ctx) {
		PortableSingleArticleStore store = new PortableSingleArticleStore(this.getMaxFMC(), AbstractFMCItem.KEY, ctx);
		return store;
	}
}
