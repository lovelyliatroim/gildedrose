package com.gildedrose;

public class RegularItemQualityUpdater implements ItemQualityUpdater {

    @Override
    public void updateQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        item.sellIn--;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
