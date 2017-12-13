package com.gildedrose;

import java.util.HashMap;
import java.util.Map;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";

    private final Map<String, ItemUpdater> CUSTOM_ITEM_UPDATERS = new HashMap<>();
    private final ItemUpdater REGULAR_ITEM_UPDATER = new RegularItemUpdater();

    Item[] items;

    public GildedRose(Item[] items) {
        this.initCustomItemUpdaters();
        this.items = items;
    }

    private void initCustomItemUpdaters() {
        this.CUSTOM_ITEM_UPDATERS.put(AGED_BRIE, new AgedBrieItemUpdater());
        this.CUSTOM_ITEM_UPDATERS.put(BACKSTAGE_PASS, new BackStagePassItemUpdater());
        this.CUSTOM_ITEM_UPDATERS.put(SULFURAS, new SulfurasItemUpdater());
        this.CUSTOM_ITEM_UPDATERS.put(CONJURED, new ConjuredItemUpdater());
    }

    public void updateQuality() {
        for (Item item : items) {
            this.getQualityUpdater(item).update(item);
        }
    }

    private ItemUpdater getQualityUpdater(Item item) {
        final ItemUpdater updater = this.CUSTOM_ITEM_UPDATERS.get(item.name);

        return updater != null ? updater : this.REGULAR_ITEM_UPDATER;
    }

}