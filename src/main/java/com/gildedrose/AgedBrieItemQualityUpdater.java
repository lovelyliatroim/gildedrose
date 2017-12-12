package com.gildedrose;

public class AgedBrieItemQualityUpdater implements ItemQualityUpdater {

    @Override
    public void updateQuality(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        item.sellIn--;
        if (item.sellIn < 0 && item.quality < 50 ) {
            item.quality++;
        }

    }
}
