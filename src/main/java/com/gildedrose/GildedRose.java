package com.gildedrose;

class GildedRose {

    Item[] items;

    GildedRose(Item[] items) {
        this.items = items;
    }

    void updateValues() {
        for (Item currentItem : items) {
            GildedRoseItem gildedRoseItem = transformToGildedRoseItem(currentItem);
            gildedRoseItem.updateQuality();
            gildedRoseItem.updateSellIn();

        }
    }

    private GildedRoseItem transformToGildedRoseItem(Item legacyItem) {
        if (isAgedBrie(legacyItem))
            return new AgedBrieItem(legacyItem);
        if (isSulfuras(legacyItem))
            return new SulfurasItem(legacyItem);
        if (isBackstagePass(legacyItem))
            return new BackstagePassItem(legacyItem);
        return new DefaultItem(legacyItem);
    }

    private boolean isBackstagePass(Item currentItem) {
        return currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item currentItem) {
        return currentItem.name.equals("Aged Brie");
    }

    private boolean isSulfuras(Item currentItem) {
        return currentItem.name.equals("Sulfuras, Hand of Ragnaros");
    }
}