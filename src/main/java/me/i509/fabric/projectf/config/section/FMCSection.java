package me.i509.fabric.projectf.config.section;

import grondag.fluidity.api.fraction.Fraction;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class FMCSection {
	@Setting(comment = Comments.ARTICLE_TO_FLUID)
	private Fraction articleToFluid = new Fraction(1000, 16384);

	public Fraction getArticleToFluid() {
		return this.articleToFluid;
	}

	private static final class Comments {
		static final String ARTICLE_TO_FLUID = "Represents the amount of the discrete FMC article to the amount of liquid matter fluid.\n" +
			"By default this is 1 bucket to 16384 FMC";
	}
}
