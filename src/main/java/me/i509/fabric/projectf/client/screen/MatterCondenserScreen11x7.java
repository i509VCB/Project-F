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

package me.i509.fabric.projectf.client.screen;

import java.util.function.LongSupplier;
import com.mojang.blaze3d.systems.RenderSystem;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.container.MatterCondenserScreenHandler11x7;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MatterCondenserScreen11x7 extends HandledScreen<MatterCondenserScreenHandler11x7> {
	private static final Identifier TEXTURE = ProjectF.id("textures/gui/container/mattercondenser_11x7.png");
	private final LongSupplier fmcValueSupplier;

	public MatterCondenserScreen11x7(MatterCondenserScreenHandler11x7 container, PlayerInventory playerInventory, LongSupplier fmcValueSupplier, Text name) {
		super(container, playerInventory, name);
		this.backgroundHeight = 123 + 7 * 18;
		this.backgroundWidth += 36;
		this.fmcValueSupplier = fmcValueSupplier;
	}

	public static HandledScreen<MatterCondenserScreenHandler11x7> create(MatterCondenserScreenHandler11x7 container) {
		return new MatterCondenserScreen11x7(container, container.getPlayerInventory(), container.getLongSupplier(), container.getDisplayName());
	}

	@Override
	public void render(int mouseX, int mouseY, float lastFrameDuration) {
		this.renderBackground();
		this.drawBackground(lastFrameDuration, mouseX, mouseY);
		super.render(mouseX, mouseY, lastFrameDuration);
		this.drawMouseoverTooltip(mouseX, mouseY);
	}

	@Override
	protected void drawForeground(int mouseX, int mouseY) {
		this.textRenderer.draw(Long.toString(this.fmcValueSupplier.getAsLong()), 132.0F, 7.0F, 4210752);
		this.textRenderer.draw(this.playerInventory.getDisplayName().asFormattedString(), 8.0F, (float) (this.backgroundHeight - 114 + 2), 4210752);
	}

	@Override
	protected void drawBackground(float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.backgroundWidth) / 2;
		int j = (this.height - this.backgroundHeight) / 2;
		this.drawTexture(i, j, 0, 0, this.backgroundWidth, 7 * 18 + 17);
		this.drawTexture(i, j + 7 * 18, 0, 126, this.backgroundWidth, 114);
		this.drawTexture(i + 23, j + 5, 0, 240, this.backgroundWidth, 16); // Draw the bar
	}
}
