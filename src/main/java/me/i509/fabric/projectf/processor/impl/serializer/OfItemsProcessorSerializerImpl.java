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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import me.i509.fabric.projectf.api.processor.type.OfItemsProcessor;
import me.i509.fabric.projectf.api.processor.serializer.OfItemsProcessorSerializer;
import me.i509.fabric.projectf.processor.impl.type.OfItemsProcessorImpl;
import me.i509.fabric.projectf.util.gson.GsonUtils;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class OfItemsProcessorSerializerImpl implements OfItemsProcessorSerializer {
	@Override
	public OfItemsProcessor deserialize(JsonElement obj) {
		if (obj instanceof JsonArray) {
			JsonArray array = (JsonArray) obj;
			List<Item> items = new ArrayList<>();

			for (JsonElement element : array) {
				if (element instanceof JsonPrimitive) {
					String str = element.getAsString();
					Identifier id = GsonUtils.asIdentifierOrThrow(str);
					Item item = Registry.ITEM.getOrEmpty(id).orElseThrow(() -> new JsonParseException("Could not find item of id within registry: " + id.toString()));

					items.add(item);
				} else {
					throw new JsonParseException("Element in Of Items Processor was not a String.");
				}
			}

			return new OfItemsProcessorImpl(items);
		}

		throw new JsonParseException("Of Items Processor requires an array of items.");
	}

	@Override
	public JsonElement serialize(OfItemsProcessor processor) {
		return null;
	}

	@Override
	public PacketByteBuf toPacket(OfItemsProcessor processor, PacketByteBuf buf) {
		return null;
	}

	@Override
	public OfItemsProcessor fromPacket(PacketByteBuf buf) {
		return null;
	}
}
