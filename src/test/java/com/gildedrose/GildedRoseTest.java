package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GildedRoseTest {

    final int NEGATIVE_SELL_IN = -20;
    final int ZERO_SELL_IN = 0;
    final int POSITIVE_SELL_IN = 20;
    final int SULFURAS_QUALITY = 80;
    final int POSITIVE_QUALITY = 14;
    final int POSITIVE_QUALITY_LESS_THAN_50 = 30;
    final int POSITIVE_QUALITY_50 = 50;
    final int SELL_IN_MORE_THAN_10_DAYS = 15;
    final int SELL_IN_10_DAYS_OR_LESS = 10;
    final int SELL_IN_5_DAYS_OR_LESS = 5;
    final int ONE_QUALITY = 1;
    final int ZERO_QUALITY = 0;

    // Regular item tests
    @Test
    public void testRegularItemQualityIsNeverNegative() {
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createRegularItem(NEGATIVE_SELL_IN, ZERO_QUALITY));
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createRegularItem(ZERO_SELL_IN, ZERO_QUALITY));
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createRegularItem(POSITIVE_SELL_IN, ZERO_QUALITY));
    }

    @Test
    public void testRegularItemQualityDecreasesByOneBeforeSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 1, this.createRegularItem(POSITIVE_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testRegularItemQualityDecreasesByTwoAtSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 2, this.createRegularItem(ZERO_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testRegularlItemQualityDegradesTwiceAsFastAfterSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 2, this.createRegularItem(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testRegularItemSellInDecreasesByOne() {
        this.assertSellInAfterItemUpdated(POSITIVE_SELL_IN - 1, this.createRegularItem(POSITIVE_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(ZERO_SELL_IN - 1, this.createRegularItem(ZERO_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(NEGATIVE_SELL_IN - 1, this.createRegularItem(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    // Sulfuras tests
    @Test
    public void testSulfurasNeverLoosesQuality() {
        this.assertQualityAfterItemUpdated(SULFURAS_QUALITY, this.createSulfuras(POSITIVE_SELL_IN, SULFURAS_QUALITY));
        this.assertQualityAfterItemUpdated(SULFURAS_QUALITY, this.createSulfuras(ZERO_SELL_IN, SULFURAS_QUALITY));
        this.assertQualityAfterItemUpdated(SULFURAS_QUALITY, this.createSulfuras(NEGATIVE_SELL_IN, SULFURAS_QUALITY));
    }

    @Test
    public void testSulfurasSellInIsNeverDecreased() {
        this.assertSellInAfterItemUpdated(NEGATIVE_SELL_IN, this.createSulfuras(NEGATIVE_SELL_IN, SULFURAS_QUALITY));
        this.assertSellInAfterItemUpdated(ZERO_SELL_IN, this.createSulfuras(ZERO_SELL_IN, SULFURAS_QUALITY));
        this.assertSellInAfterItemUpdated(POSITIVE_SELL_IN, this.createSulfuras(POSITIVE_SELL_IN, SULFURAS_QUALITY));
    }

    // Aged Brie tests
    @Test
    public void testAgedBrieQualityIncreasesWithOneWhenSellInPositiveAndQualityLessThan50() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY_LESS_THAN_50 + 1, this.createAgedBrie(POSITIVE_SELL_IN, POSITIVE_QUALITY_LESS_THAN_50));
    }

    @Test
    public void testAgedBrieQualityWillNotIncreaseForQualityEqual50() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY_50, this.createAgedBrie(POSITIVE_SELL_IN, POSITIVE_QUALITY_50));
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY_50, this.createAgedBrie(NEGATIVE_SELL_IN, POSITIVE_QUALITY_50));
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY_50, this.createAgedBrie(ZERO_SELL_IN, POSITIVE_QUALITY_50));
    }

    @Test
    public void testAgedBrieQualityIncreasesWithTwoWhenSellInNegativeAndQualityLessThan50() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY_LESS_THAN_50 + 2, this.createAgedBrie(NEGATIVE_SELL_IN, POSITIVE_QUALITY_LESS_THAN_50));
    }

    @Test
    public void testAgedBrieItemSellInDecreasesByOne() {
        this.assertSellInAfterItemUpdated(POSITIVE_SELL_IN - 1, this.createAgedBrie(POSITIVE_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(ZERO_SELL_IN - 1, this.createAgedBrie(ZERO_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(NEGATIVE_SELL_IN - 1, this.createAgedBrie(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    // Backstage pass tests
    @Test
    public void testBackstagePassQualityIncreasesWithOneIfMoreThanTenDaysBeforeSellIn() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY + 1, this.createBackstagePass(SELL_IN_MORE_THAN_10_DAYS, POSITIVE_QUALITY));
    }

    @Test
    public void testBackstagePassQualityIncreasesWithTwoIfTenDaysOrLessBeforeSellIn() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY + 2, this.createBackstagePass(SELL_IN_10_DAYS_OR_LESS, POSITIVE_QUALITY));
    }

    @Test
    public void testBackstagePassQualityIncreasesWithThreeIfFiveDaysOrLessBeforeSellIn() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY + 3, this.createBackstagePass(SELL_IN_5_DAYS_OR_LESS, POSITIVE_QUALITY));
    }

    @Test
    public void testBackstagePassQualityDecreasesToZeroWhenSellInZeroOrNegative() {
        this.assertQualityAfterItemUpdated(ZERO_QUALITY, this.createBackstagePass(ZERO_SELL_IN, POSITIVE_QUALITY));
        this.assertQualityAfterItemUpdated(ZERO_QUALITY, this.createBackstagePass(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testBackstagePassItemSellInDecreasesByOne() {
        this.assertSellInAfterItemUpdated(POSITIVE_SELL_IN - 1, this.createBackstagePass(POSITIVE_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(ZERO_SELL_IN - 1, this.createBackstagePass(ZERO_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(NEGATIVE_SELL_IN - 1, this.createBackstagePass(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    // Conjured items tests
    @Test
    public void testConjuredItemQualityDegradesTwiceAsFastAsRegularItemsBeforeSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 2, this.createConjuredItem(POSITIVE_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testConjuredItemQualityDegradesTwiceAsFastAsRegularItemsOnSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 4, this.createConjuredItem(ZERO_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testConjuredItemQualityDegradesTwiceAsFastAsRegularItemsWhenQualityIsOne() {
        this.assertQualityAfterItemUpdated(ZERO_QUALITY, this.createConjuredItem(POSITIVE_SELL_IN, ONE_QUALITY));
    }

    @Test
    public void testConjuredItemQualityDegradesTwiceAsFastAsRegularItemsAfterSellDate() {
        this.assertQualityAfterItemUpdated(POSITIVE_QUALITY - 4, this.createConjuredItem(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    @Test
    public void testConjuredItemSellInDecreasesByOne() {
        this.assertSellInAfterItemUpdated(POSITIVE_SELL_IN - 1, this.createConjuredItem(POSITIVE_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(ZERO_SELL_IN - 1, this.createConjuredItem(ZERO_SELL_IN, POSITIVE_QUALITY));
        this.assertSellInAfterItemUpdated(NEGATIVE_SELL_IN - 1, this.createConjuredItem(NEGATIVE_SELL_IN, POSITIVE_QUALITY));
    }

    private Item createConjuredItem(int sellIn, int quality) {
        return new Item("Conjured", sellIn, quality);
    }

    private Item createRegularItem(int sellIn, int quality) {
        return new Item("Regular", sellIn, quality);
    }

    private Item createSulfuras(int sellIn, int quality) {
        return new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    private Item createBackstagePass(int sellIn, int quality) {
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
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

    private void assertQualityGreaterThanZeroAfterItemUpdated(Item item) {
        final Item[] items = new Item[] {item};
        this.updateQuality(items);
        assertTrue(item.quality >= 0);
    }

    private void assertSellInAfterItemUpdated(int expected, Item item) {
        final Item[] items = new Item[] {item};
        this.updateQuality(items);
        assertEquals(expected, item.sellIn);
    }

}
