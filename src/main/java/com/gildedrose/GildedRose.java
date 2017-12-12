package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private boolean isAgedBrie(Item item) {
        return item != null && item.name.equals(AGED_BRIE);
    }

    private boolean isBackStagePass(Item item) {
        return item != null && item.name.equals(BACKSTAGE_PASS);
    }

    private boolean isSulfuras(Item item) {
        return item != null && item.name.equals(SULFURAS);
    }

    public void updateQuality() {
        for (Item item : items) {

            if (this.isSulfuras(item)) {
                continue;
            } else if (this.isAgedBrie(item)) {
                if (item.quality < 50) {
                    item.quality++;
                }
                item.sellIn--;
                if (item.sellIn < 0 && item.quality < 50 ) {
                    item.quality++;
                }
            } else if (this.isBackStagePass(item)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }

                item.sellIn--;
                if (item.sellIn < 0) {
                    item.quality = 0;
                }
            } else {
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
                item.sellIn--;
                if (item.sellIn < 0 && item.quality > 0) {
                    item.quality--;
                }
            }
        }
    }
}