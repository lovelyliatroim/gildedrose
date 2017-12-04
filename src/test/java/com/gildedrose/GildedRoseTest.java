package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private static final String DEFAULT_ITEM_NAME = "foo";
    private static final int DEFAULT_SELLIN = 10;
    private static final int DEFAULT_QUALITY = 10;
    private static final int DEFAULT_PASSED_SELLIN = -1;

    @Test
    public void updateValues_shouldNotChangeTheNameOfAnItem() {
        Item[] items = new Item[]{new TestItem().withName(DEFAULT_ITEM_NAME).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(DEFAULT_ITEM_NAME, app.items[0].name);
    }

    @Test
    public void updateValues_shouldDecreaseQuality() {
        Item[] items = new Item[]{new TestItem().withQuality(DEFAULT_QUALITY).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(DEFAULT_QUALITY-1, app.items[0].quality);
    }

    @Test
    public void updateValues_shouldDecreaseSellIn() {
        Item[] items = new Item[]{new TestItem().withSellIn(DEFAULT_SELLIN).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(DEFAULT_SELLIN-1, app.items[0].sellIn);
    }

    @Test
    public void updateValues_givenAnItemWithPassedSellIn_thenQualityDecreasesTwiceAsFast() {
        Item[] items = new Item[]{new TestItem().withSellIn(DEFAULT_PASSED_SELLIN).withQuality(3).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(1, app.items[0].quality);
    }

    static class TestItem {

        private final Item item;

        private TestItem() {
            item = new Item(DEFAULT_ITEM_NAME, DEFAULT_SELLIN, DEFAULT_QUALITY);
        }

        TestItem withName(String name) {
            item.name = name;
            return this;
        }

        TestItem withQuality(int quality) {
            item.quality = quality;
            return this;
        }

        TestItem withSellIn(int sellIn) {
            item.sellIn = sellIn;
            return this;
        }

        Item toItem() {
            return item;
        }
    }

}
