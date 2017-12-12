package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void testSulfurasNeverLoosesQuality() {
        this.assertQualityAfterItemUpdated(3, this.createSulfuras(2, 3));
        this.assertQualityAfterItemUpdated(0, this.createSulfuras(2, 0));
        this.assertQualityAfterItemUpdated(10000, this.createSulfuras(2, 10_000));
    }

    @Test
    public void testSulfurasSellInIsNeverDecreased() {
        this.assertSellInAfterItemUpdated(-20, this.createSulfuras(-20, 3));
        this.assertSellInAfterItemUpdated(0, this.createSulfuras(0, 0));
        this.assertSellInAfterItemUpdated(220, this.createSulfuras(220, 10_000));
    }

    @Test
    public void testAgedBrieQualityIncreasesWithOneWhenSellInPositiveAndQualityLessThan50() {
        final int positiveSellIn = 15;
        this.assertQualityAfterItemUpdated(9, this.createAgedBrie(positiveSellIn, 8));
        this.assertQualityAfterItemUpdated(1, this.createAgedBrie(positiveSellIn, 0));
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(positiveSellIn, 49));
    }

    @Test
    public void testAgedBrieQualityIncreasesWithTwoWhenSellInNegativeAndQualityLessThan50() {
        final int negativeSellIn = -15;
        this.assertQualityAfterItemUpdated(10, this.createAgedBrie(negativeSellIn, 8));
        this.assertQualityAfterItemUpdated(2, this.createAgedBrie(negativeSellIn, 0));
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(negativeSellIn, 48));
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(negativeSellIn, 49));
    }

    @Test
    public void testAgedBrieQualityWillNotIncreaseForQualityGreaterOrEqual50() {
        final int positiveSellIn = 15;
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(positiveSellIn, 49));
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(positiveSellIn, 50));
        this.assertQualityAfterItemUpdated(65, this.createAgedBrie(positiveSellIn, 65));

        final int negativeSellIn = -15;
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(negativeSellIn, 49));
        this.assertQualityAfterItemUpdated(50, this.createAgedBrie(negativeSellIn, 50));
        this.assertQualityAfterItemUpdated(65, this.createAgedBrie(negativeSellIn, 65));
    }

    private Item createSulfuras(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    private Item createAgedBrie(int sellIn, int quality) {
        return new Item("Aged Brie", sellIn, quality);
    }

    private void updateQuality(Item[] items) {
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    private void assertQualityAfterItemUpdated(int expected, Item item) {
        final Item[] items = new Item[] {item};
        this.updateQuality(items);
        assertEquals(expected, item.quality);
    }

    private void assertSellInAfterItemUpdated(int expected, Item item) {
        final Item[] items = new Item[] {item};
        this.updateQuality(items);
        assertEquals(expected, item.sellIn);
    }

}
