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

package me.i509.fabric.projectf;

import java.util.List;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.processor.ProcessorRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;

public class ProjectF {
	public static final String MODID = "projectf";
	private static final ProjectF INSTANCE = new ProjectF();
	private static final Logger LOGGER = LogManager.getLogger(ProjectF.class, new AbstractMessageFactory() {
		@Override
		public Message newMessage(String message, Object... params) {
			return new ParameterizedMessage("[ProjectF] " + message, params);
		}
	});

	private ProjectF() {
		// NO-OP
	}

	final void init() {
		processorRegistry = new ProcessorRegistry();
		fmcManager = new FMCManager(this);
	}

	private ProcessorRegistry processorRegistry;
	private FMCManager fmcManager;

	public FMCManager getFMCManager() {
		return this.fmcManager;
	}

	public ProcessorRegistry getProcessorRegistry() {
		return this.processorRegistry;
	}

	public static ProjectF getInstance() {
		return ProjectF.INSTANCE;
	}

	public static Logger getLogger() {
		return ProjectF.LOGGER;
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}

	@Environment(EnvType.CLIENT)
	public void addFMCTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		Processor processor = this.getFMCManager().getProcessor(stack.getItem());

		long fmcValue = processor.process(stack);

		if (fmcValue <= 0) {
			return;
		}

		tooltip.add(new TranslatableText("fmc.amount.each", fmcValue));

		if (stack.getCount() > 1) {
			tooltip.add(new TranslatableText("fmc.amount.total", fmcValue * stack.getCount()));
		}
	}
}
