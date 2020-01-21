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

package me.i509.fabric.projectf.data;

import java.util.Map;
import java.util.Optional;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.i509.fabric.projectf.FMCManager;
import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.processor.type.Processor;
import me.i509.fabric.projectf.api.processor.serializer.ProcessorSerializer;
import me.i509.fabric.projectf.util.gson.GsonUtils;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FMCReader {
	public static void read(FMCManager fmcManager, JsonObject object) {
		ProjectF projectF = fmcManager.getProjectF();

		for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
			try {
				Identifier id = GsonUtils.asIdentifierOrThrow(entry.getKey());
				Item item = Registry.ITEM.getOrEmpty(id).orElseThrow(() -> new JsonParseException("Could not find item of id within registry: " + id.toString()));
				JsonObject elementObject = GsonUtils.asObjectOrThrow(entry.getValue());

				if (elementObject.entrySet().size() > 1) {
					throw new JsonParseException("Cannot specify multiple processors as a list of elements, use the 'projectf:and' or 'projectf:of_items' processors instead for more complex values.");
				}

				for (Map.Entry<String, JsonElement> elementEntry : elementObject.entrySet()) {
					String processorId = elementEntry.getKey();
					Optional<ProcessorSerializer<?, ?>> processorContainer = projectF.getProcessorRegistry().getSerializer(processorId);

					if (!processorContainer.isPresent()) {
						throw new JsonParseException("No processor of with id: " + processorId + " exists.");
					}

					ProcessorSerializer<?, ?> processorSerializer = processorContainer.get();
					Processor processor = processorSerializer.deserialize(elementEntry.getValue());

					fmcManager.register(item, processor);
				}
			} catch (Throwable e) {
				ProjectF.getLogger().error("Failed to load an FMC Entry due to error:", e);
			}
		}
	}
}
