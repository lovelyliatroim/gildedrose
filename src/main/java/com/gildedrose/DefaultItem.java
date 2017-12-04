package com.gildedrose;

/**
 * @author David Leitner
 * @since 04.12.2017
 */
public class DefaultItem implements GildedRoseItem {
    private Item legacyItem;

    DefaultItem(Item legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void updateQuality() {
        if (legacyItem.quality <= 0) return;

        legacyItem.quality = legacyItem.quality - 1;
        if (legacyItem.sellIn < 1) {
            legacyItem.quality = legacyItem.quality - 1;
        }
    }

    @Override
    public void updateSellIn() {
        legacyItem.sellIn = legacyItem.sellIn - 1;
    }
}
