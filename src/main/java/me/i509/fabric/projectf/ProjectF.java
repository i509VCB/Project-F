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

import me.i509.fabric.projectf.api.processor.Processor;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProjectF {
	public static final String MOD_ID = "projectf";
	public static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(
			() -> new RuntimeException("Failed to get ProjectF's mod container. This is a bug and should be reported"));
	private static final ProjectF INSTANCE = new ProjectF();
	private static final Logger LOGGER = LogManager.getLogger(ProjectF.class);

	private final Map<Class<? extends Processor>, Supplier<? extends Processor.Builder<?>>> builders
			= new IdentityHashMap<>();
	private final Map<Class<? extends Processor>, Supplier<? extends Processor.Serializer<?>>> serializers
			= new IdentityHashMap<>();

	private ProjectF() {
		// NO-OP
	}

	public static ProjectF getInstance() {
		return ProjectF.INSTANCE;
	}

	public static Logger getLogger() {
		return ProjectF.LOGGER;
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	private static void initProcessors() {
		// TODO: Setup
	}

	public <B extends Processor.Builder<P>, P extends Processor> B supplyBuilder(Class<P> processorClass) {
		return (B) ProjectF.getInstance().serializers.get(processorClass).get();
	}

	public <S extends Processor.Serializer<P>, P extends Processor> S supplySerializer(Class<P> processorClass) {
		return (S) ProjectF.getInstance().builders.get(processorClass).get();
	}
}
