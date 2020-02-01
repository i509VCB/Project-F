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

package me.i509.fabric.projectf.processor;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.FMCProcessorEntrypoint;
import me.i509.fabric.projectf.api.processor.factory.ProcessorFactory;
import me.i509.fabric.projectf.api.processor.serializer.ProcessorSerializer;
import me.i509.fabric.projectf.api.processor.type.AddProcessor;
import me.i509.fabric.projectf.api.processor.type.ConstantProcessor;
import me.i509.fabric.projectf.api.processor.type.MultiplyProcessor;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import me.i509.fabric.projectf.api.processor.type.MaxProcessor;
import me.i509.fabric.projectf.api.processor.type.MinProcessor;
import me.i509.fabric.projectf.api.processor.type.OfItemsProcessor;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.processor.impl.factory.AddProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.ConstantProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.MultiplyProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.PercentageOfOfProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.MaxProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.MinProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.factory.OfItemsProcessorFactoryImpl;
import me.i509.fabric.projectf.processor.impl.serializer.AddProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.ConstantProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.MultiplyProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.PercentageOfOfProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.MaxProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.MinProcessorSerializerImpl;
import me.i509.fabric.projectf.processor.impl.serializer.OfItemsProcessorSerializerImpl;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class ProcessorRegistry {
	private Map<Identifier, ProcessorSerializer<?, ?>> idToSerializer = new HashMap<>();
	private Map<Class<? extends Processor>, Supplier<? extends ProcessorFactory<?>>> classToFactoryMap = new HashMap<>();

	public ProcessorRegistry() {
		this.register(ProjectF.id("add"), AddProcessor.class, AddProcessorFactoryImpl::new, new AddProcessorSerializerImpl());
		this.register(ProjectF.id("constant"), ConstantProcessor.class, ConstantProcessorFactoryImpl::new, new ConstantProcessorSerializerImpl());
		this.register(ProjectF.id("of_items"), OfItemsProcessor.class, OfItemsProcessorFactoryImpl::new, new OfItemsProcessorSerializerImpl());
		this.register(ProjectF.id("max"), MaxProcessor.class, MaxProcessorFactoryImpl::new, new MaxProcessorSerializerImpl());
		this.register(ProjectF.id("min"), MinProcessor.class, MinProcessorFactoryImpl::new, new MinProcessorSerializerImpl());
		this.register(ProjectF.id("multiply"), MultiplyProcessor.class, MultiplyProcessorFactoryImpl::new, new MultiplyProcessorSerializerImpl());
		this.register(ProjectF.id("percentage_of"), PercentageOfProcessor.class, PercentageOfOfProcessorFactoryImpl::new, new PercentageOfOfProcessorSerializerImpl());

		List<FMCProcessorEntrypoint> entrypoints = FabricLoader.getInstance().getEntrypoints("fmc:processor", FMCProcessorEntrypoint.class);
		entrypoints.forEach(e -> e.registerProcessors(this::register));
	}

	/**
	 * Registers a new processor type.
	 * @param identifier The identifer of the processor.
	 * @param clazz The class type of the processor.
	 * @param factory The processor factory which creates processor instances.
	 * @param serializer The processor serializer.
	 * @param <F> The type of the processor factory.
	 * @param <P> The type of the processor.
	 * @param <S> The type of the processor's serializer.
	 */
	public <F extends ProcessorFactory<P>, P extends Processor, S extends ProcessorSerializer<P, F>> void register(Identifier identifier, Class<P> clazz, Supplier<F> factory, S serializer) {
		checkNotNull(identifier, "Identifier cannot be null");
		checkNotNull(factory, "Factory cannot be null");
		checkNotNull(serializer, "Serializer cannot be null");

		if (idToSerializer.containsKey(identifier)) {
			throw new IllegalArgumentException("Cannot register two processors with the same identifier: " + identifier.toString());
		}

		this.idToSerializer.put(identifier, serializer);
		this.classToFactoryMap.put(clazz, factory);
		ProjectF.getLogger().info("Registered Processor of id: " + identifier.toString());
	}

	public <F extends ProcessorFactory<P>, P extends Processor<F>> F get(Class<P> processorClass) {
		return (F) this.classToFactoryMap.get(processorClass).get();
	}

	public Optional<ProcessorSerializer<?, ?>> getSerializer(String processorId) {
		Identifier id = Identifier.tryParse(processorId);

		if (id != null) {
			return Optional.ofNullable(this.idToSerializer.get(id));
		}

		return Optional.empty();
	}

	public <F extends ProcessorFactory<P>, P extends Processor<F>> Optional<ProcessorSerializer<P, F>> getSerializer(P processor) {
		return Optional.ofNullable((ProcessorSerializer) idToSerializer.get(processor.getId()));
	}
}
