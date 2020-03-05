package me.i509.fabric.projectf.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class TestArmorType extends ArmorItem {
	public TestArmorType(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings.maxDamage(-1).maxCount(1));
	}
}
