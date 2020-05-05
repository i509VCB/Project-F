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

package me.i509.fabric.projectf.api.processor.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import me.i509.fabric.projectf.api.processor.type.Processor;
import net.minecraft.network.PacketByteBuf;
import me.i509.fabric.projectf.api.processor.factory.ProcessorBuilder;

public interface ProcessorSerializer<P extends Processor, F extends ProcessorBuilder<P>> {
	P deserialize(JsonElement element) throws JsonParseException;

	JsonElement serialize(P processor);

	/**
	 * Converts a processor into it's PacketByteBuf representation.
	 *
	 * <p>The packet should start with the processor's Identifier.</p>
	 *
	 * @param processor The processor to make into a packet.
	 * @param buf The PacketByteBuf to write to.
	 * @return The processor's properties in a PacketByteBuf.
	 */
	PacketByteBuf toPacket(P processor, PacketByteBuf buf);

	/**
	 * Creates the processor from a PacketByteBuf.
	 *
	 * <p>When reading the packet, you should read as assuming the identifier you wrote into the PacketByteBuf on the server has already been consumed.</p>
	 *
	 * @param buf The PacketByteBuf to read.
	 * @return A processor from the PacketByteBuf.
	 */
	P fromPacket(PacketByteBuf buf);
}
