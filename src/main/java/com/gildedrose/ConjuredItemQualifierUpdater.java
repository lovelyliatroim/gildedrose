package com.gildedrose;

public class ConjuredItemQualifierUpdater implements ItemQualityUpdater {

    @Override
    public void updateQuality(Item item) {
        if (item.quality > 1) {
            item.quality = item.quality - 2;
        }

        item.sellIn--;

        if (item.sellIn < 0 && item.quality > 1) {
            item.quality = item.quality - 2;
        }
    }
}
