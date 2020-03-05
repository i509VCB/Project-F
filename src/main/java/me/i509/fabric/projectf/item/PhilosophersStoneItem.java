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

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.inventory.PhilosophersStoneBlockInventory;
import me.i509.fabric.projectf.recipe.PhilosophersStoneBlockRecipe;
import me.i509.fabric.projectf.registry.PFRecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class PhilosophersStoneItem extends Item {
	public PhilosophersStoneItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getWorld().isClient()) {
			return super.useOnBlock(context);
		}

		if (context.getPlayer() == null) {
			return super.useOnBlock(context);
		}

		ServerPlayerEntity playerEntity = (ServerPlayerEntity) context.getPlayer();
		BlockPos target = context.getBlockPos();
		BlockState targettedState = context.getWorld().getBlockState(target);
		Block targettedBlock = targettedState.getBlock();

		// The usage context is not always reliable, so we raytrace ourselves as a sanity check.
		/*
		HitResult hitResult = rayTraceHit(playerEntity);

		if (hitResult instanceof BlockHitResult && !hitResult.getPos().equals(context.getHitPos())) {
			target = ((BlockHitResult) hitResult).getBlockPos();
			side = ((BlockHitResult) hitResult).getSide();
		}*/

		PhilosophersStoneBlockInventory inventory = new PhilosophersStoneBlockInventory(targettedBlock);
		Optional<PhilosophersStoneBlockRecipe> recipe = context.getWorld().getRecipeManager().getFirstMatch(PFRecipeTypes.PHILOSOPHERS_STONE_BLOCK, inventory, context.getWorld());

		if (!recipe.isPresent()) {
			return super.useOnBlock(context);
		}

		BlockState outputState = recipe.get().getOutputBlock().getDefaultState();

		// Now attempt to preserve the block state properties, this can be disabled in a config.
		// I make a best effort to preserve all properties I can but if I am not 100% sure the property exists on the new state, I don't carry it over.
		if (ProjectF.getInstance().getConfig().getPhilosophersStoneSection().shouldCopyBlockStateProperties()) {
			for (Property property : targettedState.getProperties()) {
				if (outputState.contains(property)) {
					Comparable value = targettedState.get(property);
					outputState = outputState.with(property, value);
				}
			}
		}

		ItemStack stack = playerEntity.getStackInHand(context.getHand());

		if (stack.getMaxDamage() - stack.getDamage() < recipe.get().getDurabilityUse()) {
			return ActionResult.FAIL;
		}
		// TODO: Play a sound for this, and possibly limit how frequently this can occur? Maybe an attribute modifier for this? Also add particle effects
		context.getWorld().setBlockState(target, outputState, 3);
		stack.damage(recipe.get().getDurabilityUse(), playerEntity, entity -> entity.setStackInHand(context.getHand(), stack));

		return ActionResult.SUCCESS;
	}

	public HitResult rayTraceHit(PlayerEntity player) {
		return rayTrace(player.getEntityWorld(), player, player.isSneaking() ? RayTraceContext.FluidHandling.SOURCE_ONLY : RayTraceContext.FluidHandling.NONE);
	}
}
