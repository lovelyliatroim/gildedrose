package com.gildedrose;

class GildedRose {

    Item[] items;

    GildedRose(Item[] items) {
        this.items = items;
    }

    void updateValues() {
        for (Item currentItem : items) {
            boolean isAgedBrieOrBackstagePass = isAgedBrie(currentItem) || isBackstagePass(currentItem);

            if (isAgedBrieOrBackstagePass) {
                if (currentItem.quality < 50) {
                    currentItem.quality = currentItem.quality + 1;

                    if (isBackstagePass(currentItem)) {
                        if (currentItem.sellIn < 11) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }

                        if (currentItem.sellIn < 6) {
                            if (currentItem.quality < 50) {
                                currentItem.quality = currentItem.quality + 1;
                            }
                        }
                    }
                }
            } else {
                if (currentItem.quality > 0) {
                    if (!currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                        currentItem.quality = currentItem.quality - 1;
                    }
                }
            }

            if (!currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                currentItem.sellIn = currentItem.sellIn - 1;
            }

            if (currentItem.sellIn < 0) {
                if (!isAgedBrie(currentItem)) {
                    if (!isBackstagePass(currentItem)) {
                        if (currentItem.quality > 0) {
                            if (!currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                                currentItem.quality = currentItem.quality - 1;
                            }
                        }
                    } else {
                        currentItem.quality = 0;
                    }
                } else {
                    if (currentItem.quality < 50) {
                        currentItem.quality = currentItem.quality + 1;
                    }
                }
            }
        }
    }

    private boolean isBackstagePass(Item currentItem) {
        return currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(Item currentItem) {
        return currentItem.name.equals("Aged Brie");
    }
}