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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.serializer.MinProcessorSerializer;
import me.i509.fabric.projectf.api.processor.serializer.ProcessorSerializer;
import me.i509.fabric.projectf.api.processor.type.MinProcessor;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.processor.ProcessorRegistry;
import me.i509.fabric.projectf.processor.impl.type.MinProcessorImpl;
import me.i509.fabric.projectf.util.gson.GsonUtils;
import net.minecraft.util.PacketByteBuf;

public class MinProcessorSerializerImpl implements MinProcessorSerializer {
	@Override
	public MinProcessor deserialize(JsonElement element) throws JsonParseException {
		JsonObject obj = GsonUtils.asObjectOrThrow(element);

		if (obj.entrySet().size() != 2) {
			throw new JsonParseException("Min Processor must have two entries");
		}

		List<Processor<?>> processors = new ArrayList<>();

		for (Map.Entry<String, JsonElement> stringJsonElementEntry : obj.entrySet()) {
			ProcessorSerializer<?, ?> serializer = ProjectF.getInstance().getProcessorRegistry().getSerializer(stringJsonElementEntry.getKey()).orElseThrow(() -> new JsonParseException("Could not find a processor with the following id: " + stringJsonElementEntry.getKey()));
			processors.add(serializer.deserialize(stringJsonElementEntry.getValue()));
		}

		if (processors.size() != 2) {
			throw new JsonParseException("Min Processor must have two entries");
		}

		return new MinProcessorImpl(processors.get(0), processors.get(1));
	}

	@Override
	public JsonElement serialize(MinProcessor processor) {
		return null;
	}

	@Override
	public PacketByteBuf toPacket(MinProcessor processor, PacketByteBuf buf) {
		ProcessorRegistry registry = ProjectF.getInstance().getProcessorRegistry();
		buf.writeIdentifier(processor.getId());
		ProcessorSerializer serializer1 = registry.getSerializer(processor.first().getId().toString()).get();
		ProcessorSerializer serializer2 = registry.getSerializer(processor.second().getId().toString()).get();
		serializer1.toPacket(processor.first(), buf);
		serializer2.toPacket(processor.second(), buf);
		return buf;
	}

	@Override
	public MinProcessor fromPacket(PacketByteBuf buf) {
		return null;
	}
}
