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

import me.i509.fabric.projectf.entity.PlayerTrackedData;
import me.i509.fabric.projectf.registry.PFItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin_AnimationTrackers extends LivingEntity implements PlayerTrackedData {
	protected PlayerEntityMixin_AnimationTrackers(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker")
	private void pf_initDataTracker(CallbackInfo ci) {
		this.dataTracker.startTracking(ANIMATION_PROGRESS, 0);
		this.dataTracker.startTracking(MATTER_GUN_DELAY, 0);
	}

	@Inject(at = @At("TAIL"), method = "tick")
	private void pf_tick(CallbackInfo ci) {
		this.dataTracker.set(MATTER_GUN_DELAY, this.dataTracker.get(MATTER_GUN_DELAY) - 1);
		ItemStack stack = this.getMainHandStack();

		if (stack.getItem() != PFItems.MATTER_GUN) {
			if (this.dataTracker.get(ANIMATION_PROGRESS) != 0) {
				this.dataTracker.set(ANIMATION_PROGRESS, 0);
			}

			return;
		}

		int animationProgress = this.dataTracker.get(ANIMATION_PROGRESS);

		if (animationProgress == 20) {
			return;
		}

		animationProgress++;

		this.dataTracker.set(ANIMATION_PROGRESS, animationProgress);
	}
}
