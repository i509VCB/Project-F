package me.i509.fabric.projectf.client.render.feature;

import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class BipedFMCArmorFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FMCArmorFeatureRenderer<T, M, A> {
	public BipedFMCArmorFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);
	}
}
