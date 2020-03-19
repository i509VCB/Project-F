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

package me.i509.fabric.projectf.processor.impl.serializer;

import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.serializer.MultiplyProcessorSerializer;
import me.i509.fabric.projectf.api.processor.serializer.ProcessorSerializer;
import me.i509.fabric.projectf.api.processor.type.MultiplyProcessor;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.processor.ProcessorRegistry;
import me.i509.fabric.projectf.processor.impl.type.MultiplyProcessorImpl;
import me.i509.fabric.projectf.util.gson.GsonUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class MultiplyProcessorSerializerImpl implements MultiplyProcessorSerializer {
	@Override
	public MultiplyProcessor deserialize(JsonElement element) throws JsonParseException {
		JsonObject obj = GsonUtils.asObjectOrThrow(element);

		if (obj.entrySet().size() != 2) {
			throw new JsonParseException("Multiply Processor must have two entries");
		}

		Processor<?> processor = null;
		double multiplier = 0.0D;

		for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
			if (entry.getKey().equals("multiplier")) {
				multiplier = entry.getValue().getAsDouble();
				continue;
			}

			// We can only assume the processor is the other one
			ProcessorSerializer<?, ?> serializer = ProjectF.getInstance().getProcessorRegistry().getSerializer(entry.getKey()).orElseThrow(() -> new JsonParseException("Could not find a processor with the following id: " + entry.getKey()));
			processor = serializer.deserialize(entry.getValue());
		}

		if (processor == null) {
			throw new JsonParseException("Multiply Processor must specify a processor");
		}

		return new MultiplyProcessorImpl(processor, multiplier);
	}

	@Override
	public JsonElement serialize(MultiplyProcessor processor) {
		return null;
	}

	@Override
	public PacketByteBuf toPacket(MultiplyProcessor processor, PacketByteBuf buf) {
		buf.writeIdentifier(processor.getId());

		ProcessorRegistry registry = ProjectF.getInstance().getProcessorRegistry();
		buf.writeIdentifier(processor.getId());
		ProcessorSerializer serializer = registry.getSerializer(processor.processor().getId().toString()).get();
		serializer.toPacket(processor.processor(), buf);

		buf.writeDouble(processor.multiplier());
		return buf;
	}

	@Override
	public MultiplyProcessor fromPacket(PacketByteBuf buf) {
		ProcessorRegistry registry = ProjectF.getInstance().getProcessorRegistry();
		Identifier processorId = buf.readIdentifier();
		ProcessorSerializer processorSerializer = registry.getSerializer(processorId.toString()).get();
		Processor<?> processor = processorSerializer.fromPacket(buf);

		double multiplier = buf.readDouble();
		return new MultiplyProcessorImpl(processor, multiplier);
	}
}
