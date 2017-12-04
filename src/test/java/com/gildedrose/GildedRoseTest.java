package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private static final String DEFAULT_ITEM_NAME = "foo";

    private static final int DEFAULT_QUALITY = 10;
    private static final int QUALITY_UPPERLIMIT = 50;

    private static final String ITEM_AGEDBRIE = "Aged Brie";
    private static final String ITEM_SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String ITEM_BACKSTAGEPASSES = "Backstage passes to a TAFKAL80ETC concert";

    private static final int DEFAULT_SELLIN = 10;
    private static final int SELLIN_PASSED = -1;
    private static final int SELLIN_ZERO = 0;
    private static final int SELLIN_LESSTHAN5 = 3;
    private static final int SELLIN_OVER5LESS10 = 7;
    private static final int SELLIN_OVER10 = 11;

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

        assertEquals(DEFAULT_QUALITY - 1, app.items[0].quality);
    }

    @Test
    public void updateValues_shouldDecreaseSellIn() {
        Item[] items = new Item[]{new TestItem().withSellIn(DEFAULT_SELLIN).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(DEFAULT_SELLIN - 1, app.items[0].sellIn);
    }

    @Test
    public void updateValues_givenAnItemWithPassedSellIn_thenQualityDecreasesTwiceAsFast() {
        Item[] items = new Item[]{new TestItem().withSellIn(SELLIN_PASSED).withQuality(3).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void updateValues_shouldNotResultInANegativeQuality() {
        Item[] items = {new TestItem().withQuality(0).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void updateValues_givenAgedBree_shouldIncreaseItsQualityOverTime() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withName(ITEM_AGEDBRIE).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        int expectedIncreasedQuality = DEFAULT_QUALITY + 1;
        assertEquals(expectedIncreasedQuality, app.items[0].quality);
    }

    @Test
    public void updateValues_givenAgedBreeWithPassedSellIn_shouldIncreaseItsQualityByTwo() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withSellIn(SELLIN_PASSED).withName(ITEM_AGEDBRIE).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        int expectedQualityIncreasedByTwo = DEFAULT_QUALITY + 2;
        assertEquals(expectedQualityIncreasedByTwo, app.items[0].quality);
    }

    @Test
    public void updateValues_givenAgedBrie_shouldNotBreachOver50() {
        Item[] items = {new TestItem().withQuality(QUALITY_UPPERLIMIT).withName(ITEM_AGEDBRIE).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(QUALITY_UPPERLIMIT, app.items[0].quality);
    }

    @Test
    public void updateValues_shouldNotHaveAnEffectOnTheQualityAndTheSellInOfSulfuras() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withSellIn(DEFAULT_SELLIN).withName(ITEM_SULFURAS).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(DEFAULT_QUALITY, app.items[0].quality);
        assertEquals(DEFAULT_SELLIN, app.items[0].sellIn);
    }

    @Test
    public void updateValues_givenBackstagePassesWithSellInOver10_shouldIncreaseQualityByOne() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withSellIn(SELLIN_OVER10).withName(ITEM_BACKSTAGEPASSES).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        int expectedQualityDecreasedByOne = DEFAULT_QUALITY + 1;
        assertEquals(expectedQualityDecreasedByOne, app.items[0].quality);
    }

    @Test
    public void updateValues_givenBackstagePassesWithSellInOver5Less10_shouldIncreaseQualityByTwo() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withSellIn(SELLIN_OVER5LESS10).withName(ITEM_BACKSTAGEPASSES).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        int expectedQualityDecreasedByTwo = DEFAULT_QUALITY + 2;
        assertEquals(expectedQualityDecreasedByTwo, app.items[0].quality);
    }

    @Test
    public void updateValues_givenBackstagePassesWithSellInLess5_shouldIncreaseQualityByThree() {
        Item[] items = {new TestItem().withQuality(DEFAULT_QUALITY).withSellIn(SELLIN_LESSTHAN5).withName(ITEM_BACKSTAGEPASSES).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        int expectedQualityDecreasedByThree = DEFAULT_QUALITY + 3;
        assertEquals(expectedQualityDecreasedByThree, app.items[0].quality);
    }

    @Test
    public void updateValues_givenBackstagePassesWithSellOf0_shouldDropQualityTo0() {
        Item[] items = {new TestItem().withSellIn(SELLIN_ZERO).withName(ITEM_BACKSTAGEPASSES).toItem()};
        GildedRose app = new GildedRose(items);

        app.updateValues();

        assertEquals(0, app.items[0].quality);
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
