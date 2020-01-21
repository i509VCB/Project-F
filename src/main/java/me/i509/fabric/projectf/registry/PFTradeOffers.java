package me.i509.fabric.projectf.registry;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import me.i509.fabric.projectf.villager.BuyForOneEmeraldFactory;
import me.i509.fabric.projectf.villager.SellItemFactory;
import net.minecraft.village.TradeOffers;

public class PFTradeOffers {
	private static final ImmutableMap<Integer, TradeOffers.Factory[]> ALCHEMIST_TRADES = ImmutableMap.<Integer, TradeOffers.Factory[]>builder()
			.put(1, new TradeOffers.Factory[]{new SellItemFactory(PFItems.MATTER_GUN, 64, 1, 1, 10)})
			.put(2, new TradeOffers.Factory[]{new BuyForOneEmeraldFactory(PFItems.FMC_BUCKET, 2, 4, 1)})
			.build();

	public static void init() {
		// NO-OP
	}

	private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> immutableMap) {
		return new Int2ObjectOpenHashMap<>(immutableMap);
	}

	static {
		TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(PFVillagers.ALCHEMIST_PROFESSION, copyToFastUtilMap(PFTradeOffers.ALCHEMIST_TRADES));
	}

	// Suppress default constructor to ensure non-instantiability.
	private PFTradeOffers() {
		throw new AssertionError("You should not be attempting to instantiate this class.");
	}
}
