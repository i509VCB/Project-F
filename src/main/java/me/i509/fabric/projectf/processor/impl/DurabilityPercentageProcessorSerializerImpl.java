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

package me.i509.fabric.projectf.processor.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import me.i509.fabric.projectf.api.processor.type.DurabilityPercentageProcessor;
import me.i509.fabric.projectf.api.processor.serializer.DurabilityPercentageProcessorSerializer;
import net.minecraft.util.PacketByteBuf;

public class DurabilityPercentageProcessorSerializerImpl implements DurabilityPercentageProcessorSerializer {
	@Override
	public DurabilityPercentageProcessor deserialize(JsonElement element) {
		if (element instanceof JsonPrimitive) {
			long value;

			try {
				value = element.getAsLong();
			} catch (Throwable e) {
				throw new JsonParseException("Failed to parse Durability Percentage Processor", e);
			}

			return new DurabilityPercentageProcessorImpl(value);
		}

		throw new JsonParseException("Durability Percentage Processor value was not a long");
	}

	@Override
	public JsonElement serialize(DurabilityPercentageProcessor processor) {
		return null;
	}

	@Override
	public PacketByteBuf toPacket(DurabilityPercentageProcessor processor, PacketByteBuf buf) {
		buf.writeIdentifier(processor.getId());
		buf.writeLong(processor.getFullDurabilityValue());
		return buf;
	}

	@Override
	public DurabilityPercentageProcessor fromPacket(PacketByteBuf buf) {
		long fullDurabilityValue = buf.readLong();
		return new DurabilityPercentageProcessorImpl(fullDurabilityValue);
	}
}