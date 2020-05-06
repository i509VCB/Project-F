package me.i509.fabric.projectf.registry;

import me.i509.fabric.projectf.ProjectF;
import me.i509.fabric.projectf.api.fmc.FMCComponent;
import me.i509.fabric.projectf.util.PFUtils;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;

public final class PFComponents {
	public static final ComponentType<FMCComponent> FMC = ComponentRegistry.INSTANCE.registerIfAbsent(ProjectF.id("fmc"), FMCComponent.class);

	private PFComponents() {
		throw PFUtils.BOSS_MUSIC;
	}

	public static void init() {
		// NO-OP
	}
}
