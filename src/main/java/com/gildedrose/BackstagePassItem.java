package com.gildedrose;

/**
 * @author David Leitner
 * @since 04.12.2017
 */
public class BackstagePassItem implements GildedRoseItem {
    private Item legacyItem;

    BackstagePassItem(Item legacyItem) {
        this.legacyItem = legacyItem;
    }

    @Override
    public void updateQuality() {
        if (legacyItem.quality < 50) {
            legacyItem.quality = legacyItem.quality + 1;

        }
        if (legacyItem.sellIn < 11) {
            if (legacyItem.quality < 50) {
                legacyItem.quality = legacyItem.quality + 1;
            }
        }

        if (legacyItem.sellIn < 6) {
            if (legacyItem.quality < 50) {
                legacyItem.quality = legacyItem.quality + 1;
            }
        }
        if (legacyItem.sellIn < 1) {
            legacyItem.quality = 0;
        }
    }

    @Override
    public void updateSellIn() {
        legacyItem.sellIn = legacyItem.sellIn - 1;

    }
}
