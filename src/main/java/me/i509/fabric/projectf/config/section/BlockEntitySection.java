package me.i509.fabric.projectf.config.section;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.asm.util.PrettyPrinter;

@ConfigSerializable
public class BlockEntitySection {

	@Setting(comment = Comments.ALCHEMICAL_CHEST_MAX_STORAGE)
	private long matterCondenserMaxStorage = 2000000L;

	public long getMatterCondenserMaxStorage() {
		if (this.matterCondenserMaxStorage <= 0) { // Oof you dun goofed.
			new PrettyPrinter(60).add("Invalid Max Storage Value for Alchemical Chest").centre().hr()
				.add("The maximum storage amount an alchemical chest can hold must be greater than 0")
				.print();
			return 2000000L;
		}

		return this.matterCondenserMaxStorage;
	}

	private static final class Comments {
		public static final String ALCHEMICAL_CHEST_MAX_STORAGE = "Represents the maximum ";
	}
}
