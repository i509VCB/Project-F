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

package me.i509.fabric.projectf.client;

import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.registry.PFFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class FluidResourceListener implements SimpleSynchronousResourceReloadListener {
	@Override
	public Identifier getFabricId() {
		return ProjectF.id("fluid_resource_listener");
	}

	@Override
	public void apply(ResourceManager manager) {
		FluidRenderHandler fmcRendererHandler = (blockRenderView, blockPos, fluidState) ->
				new Sprite[]{
				MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(ProjectF.id("block/liquid_fmc_still")),
				MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(ProjectF.id("block/liquid_fmc_flowing"))
				};

		FluidRenderHandlerRegistry.INSTANCE.register(PFFluids.STILL_FMC, fmcRendererHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(PFFluids.FLOWING_FMC, fmcRendererHandler);
	}
}
