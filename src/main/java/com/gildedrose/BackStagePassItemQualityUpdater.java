package com.gildedrose;

public class BackStagePassItemQualityUpdater implements ItemQualityUpdater {
    @Override
    public void updateQuality(Item item) {
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
    }
}
