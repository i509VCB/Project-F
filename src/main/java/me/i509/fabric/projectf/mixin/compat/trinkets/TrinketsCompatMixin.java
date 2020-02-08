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

package me.i509.fabric.projectf.mixin.compat.trinkets;

// import dev.emi.trinkets.api.TrinketsApi;
import me.i509.fabric.projectf.api.item.FMCProtectorItem;
import me.i509.fabric.projectf.compat.ProjectFHooks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ProjectFHooks.class, remap = false, priority = 1001)
public abstract class TrinketsCompatMixin {
	/*
	@Inject(at = @At("HEAD"), method = "testFluidSafetyCompat(Lnet/minecraft/server/network/ServerPlayerEntity;)Z", cancellable = true)
	private static void pf_trinketsCompat_testExternalFluidSafety(ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		Inventory inv = TrinketsApi.getTrinketsInventory(player);
		int invSize = inv.getInvSize();

		for (int x = 0; x < invSize; x++) {
			ItemStack stack = inv.getInvStack(x);

			if (stack.isEmpty()) {
				continue;
			}

			if (stack.getItem() instanceof FMCProtectorItem) {
				cir.setReturnValue(true);
			}
		}
	}

	 */
}
