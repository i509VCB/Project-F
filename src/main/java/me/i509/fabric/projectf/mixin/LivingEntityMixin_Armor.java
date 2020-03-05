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

package me.i509.fabric.projectf.mixin;

import me.i509.fabric.projectf.api.item.EquippableItem;
import me.i509.fabric.projectf.api.item.ContextualProtectionItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_Armor extends Entity {
	public LivingEntityMixin_Armor(EntityType<?> type, World world) {
		super(type, world);
	}

	@Shadow public abstract Iterable<ItemStack> getArmorItems();

	@Shadow public int stuckArrowTimer;

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), method = "onEquipStack(Lnet/minecraft/item/ItemStack;)V", cancellable = true)
	private void projectf_equipSound(ItemStack stack, CallbackInfo ci) {
		if (stack.getItem() instanceof EquippableItem) {
			EquippableItem item = (EquippableItem) stack.getItem();

			this.playSound(item.getEquipSound(), 1.0F, 1.0F);
			ci.cancel();
		}
	}

	@Inject(at = @At("TAIL"), method = "getArmor()I", locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
	private void projectf_getActualArmorValue(CallbackInfoReturnable<Integer> cir, EntityAttributeInstance armorAttributeInstance) {
		double newValue = cir.getReturnValueI();

		for (ItemStack stack : this.getArmorItems()) {
			if (stack.isEmpty()) {
				continue;
			}

			if (stack.getItem() instanceof ContextualProtectionItem) {
				if (stack.getItem() instanceof ArmorItem) {
					newValue -= ((ArmorItem) stack.getItem()).getProtection();
				}

				newValue += ((ContextualProtectionItem) stack.getItem()).getProtection((LivingEntity) (Object) this, stack);
			}
		}

		double clampedValue = armorAttributeInstance.getAttribute().clamp(newValue);
		cir.setReturnValue(MathHelper.floor(clampedValue));
	}
}
