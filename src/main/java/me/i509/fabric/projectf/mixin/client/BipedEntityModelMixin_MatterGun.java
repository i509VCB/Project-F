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

package me.i509.fabric.projectf.mixin.client;

import me.i509.fabric.projectf.entity.PlayerTrackedData;
import me.i509.fabric.projectf.registry.PFItems;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin_MatterGun extends AnimalModel<LivingEntity> {
	@Shadow public ModelPart rightArm;
	@Shadow public ModelPart leftArm;

	@Inject(at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;handSwingProgress:F", ordinal = 0, opcode = Opcodes.GETFIELD), method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V")
	private void projectf_setAngles_matterGun(LivingEntity livingEntity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch, CallbackInfo ci) {
		if (livingEntity.getMainHandStack().getItem() != PFItems.MATTER_GUN) {
			return;
		}

		if (livingEntity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) livingEntity;
			int animationProgress = player.getDataTracker().get(PlayerTrackedData.ANIMATION_PROGRESS);

			if (animationProgress == 20) {
				return;
			}

			boolean rightHanded = player.getMainArm() == Arm.RIGHT;
			float progress = 0.92F * (1.0F - ((float) animationProgress / 20.0F));

			if (rightHanded) {
				float offset = 0.92F;
				this.rightArm.pitch = this.rightArm.pitch + progress;
			} else {
				float offset = 0.92F;
				this.leftArm.pitch = this.leftArm.pitch + progress;
			}
		}
	}
}
