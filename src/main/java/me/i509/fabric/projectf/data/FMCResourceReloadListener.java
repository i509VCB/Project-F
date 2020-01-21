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

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import me.i509.fabric.projectf.FMCManager;
import me.i509.fabric.projectf.ProjectF;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class FMCResourceReloadListener implements SimpleSynchronousResourceReloadListener {
	private static final String RESOURCE_RELOAD_LISTENER = "fmc_reader";
	private static final String PATH = "fmc";
	private static final Gson GSON = new Gson();

	private final ProjectF instance;

	public FMCResourceReloadListener(ProjectF instance) {
		this.instance = instance;
	}

	@Override
	public Identifier getFabricId() {
		return ProjectF.id(RESOURCE_RELOAD_LISTENER);
	}

	@Override
	public void apply(ResourceManager manager) {
		FMCManager fmcManager = instance.getFMCManager();
		fmcManager.clear();

		Collection<Identifier> identifiers = manager.findResources(FMCResourceReloadListener.PATH, this::isJson);

		for (Identifier id : identifiers) {
			try (Resource resource = manager.getResource(id)) {
				JsonObject object = GSON.fromJson(new InputStreamReader(resource.getInputStream()), JsonObject.class);
				FMCReader.read(fmcManager, object);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JsonIOException e) {
				ProjectF.getLogger().error("Failed to read FMC Data from '" + id.toString() + "': ", e);
			} catch (JsonSyntaxException e) {
				ProjectF.getLogger().error("Failed to read FMC Data from '" + id.toString() + "' due to syntax errors: ", e);
			}
		}
	}

	private boolean isJson(String s) {
		return s.endsWith(".json") || s.endsWith(".json5");
	}
}
