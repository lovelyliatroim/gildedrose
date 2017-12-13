package com.gildedrose;

public class RegularItemUpdater implements ItemUpdater {

    @Override
    public void update(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }

        item.sellIn--;
        if (item.sellIn < 0 && item.quality > 0) {
            item.quality--;
        }
    }
}
