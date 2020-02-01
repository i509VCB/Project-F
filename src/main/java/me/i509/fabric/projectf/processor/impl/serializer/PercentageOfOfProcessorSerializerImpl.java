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

import com.google.gson.JsonElement;
import me.i509.fabric.projectf.api.processor.type.PercentageOfProcessor;
import me.i509.fabric.projectf.api.processor.serializer.PercentageOfProcessorSerializer;
import me.i509.fabric.projectf.processor.impl.type.PercentageOfProcessorImpl;
import net.minecraft.util.PacketByteBuf;

public class PercentageOfOfProcessorSerializerImpl implements PercentageOfProcessorSerializer {
	@Override
	public PercentageOfProcessor deserialize(JsonElement element) {
		return new PercentageOfProcessorImpl();
	}

	@Override
	public JsonElement serialize(PercentageOfProcessor processor) {
		return null;
	}

	@Override
	public PacketByteBuf toPacket(PercentageOfProcessor processor, PacketByteBuf buf) {
		buf.writeIdentifier(processor.getId());
		return buf;
	}

	@Override
	public PercentageOfProcessor fromPacket(PacketByteBuf buf) {
		return new PercentageOfProcessorImpl();
	}
}
