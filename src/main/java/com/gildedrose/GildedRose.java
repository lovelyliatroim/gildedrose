package com.gildedrose;

class GildedRose {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured";

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            this.getQualityUpdater(item).updateQuality(item);
        }
    }

    private ItemQualityUpdater getQualityUpdater(Item item) {
        if (this.isSulfuras(item)) {
            return new SulfurasItemQualityUpdater();
        } else if (this.isAgedBrie(item)) {
            return new AgedBrieItemQualityUpdater();
        } else if (this.isBackStagePass(item)) {
            return new BackStagePassItemQualityUpdater();
        } else if (this.isConjured(item)) {
            return new ConjuredItemQualifierUpdater();
        }
        return new RegularItemQualityUpdater();
    }

    private boolean isAgedBrie(Item item) {
        return item != null && item.name.equals(AGED_BRIE);
    }

    private boolean isConjured(Item item) {
        return item != null && item.name.equals(CONJURED);
    }

    private boolean isBackStagePass(Item item) {
        return item != null && item.name.equals(BACKSTAGE_PASS);
    }

    private boolean isSulfuras(Item item) {
        return item != null && item.name.equals(SULFURAS);
    }
}