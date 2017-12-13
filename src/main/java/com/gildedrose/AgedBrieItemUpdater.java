package com.gildedrose;

public class AgedBrieItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
        item.sellIn--;
        if (item.sellIn < 0 && item.quality < 50 ) {
            item.quality++;
        }

    }
}
