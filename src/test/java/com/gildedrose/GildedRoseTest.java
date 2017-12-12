package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Ignore;
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

    @Test
    public void testBackstagePassQualityIncreasesWithSellInDecrease() {
        this.assertQualityAfterItemUpdated(31, this.createBackstagePass(20, 30));
        this.assertQualityAfterItemUpdated(31, this.createBackstagePass(11, 30));
        this.assertQualityAfterItemUpdated(32, this.createBackstagePass(10, 30));
        this.assertQualityAfterItemUpdated(32, this.createBackstagePass(6, 30));
        this.assertQualityAfterItemUpdated(33, this.createBackstagePass(5, 30));
        this.assertQualityAfterItemUpdated(33, this.createBackstagePass(1, 30));
    }

    @Test
    public void testBackstagePassQualityDecreasesToZeroWhenSellInZeroOrNegative() {
        this.assertQualityAfterItemUpdated(0, this.createBackstagePass(0, 30));
        this.assertQualityAfterItemUpdated(0, this.createBackstagePass(-1, 30));
    }

    @Test
    public void testQualityofAnItemIsNeverNegative() {
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createBackstagePass(4, 0));
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createBackstagePass(-1, 0));
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createAgedBrie(4, 0));
        this.assertQualityGreaterThanZeroAfterItemUpdated(this.createAgedBrie(-1, 0));
    }

    @Test
    public void testNonSpecialItemQualityDecreasesByOne() {
        final int sellIn = 10;
        this.assertQualityAfterItemUpdated(29, this.createRegularItem(sellIn, 30));
        this.assertQualityAfterItemUpdated(0, this.createRegularItem(sellIn, 1));
    }

    @Test
    public void testRegularItemSellInDecreasesByOne() {
        final int quality = 10;
        this.assertSellInAfterItemUpdated(9, this.createRegularItem(10, quality));
        this.assertSellInAfterItemUpdated(0, this.createRegularItem( 1, quality));
        this.assertSellInAfterItemUpdated(-1, this.createRegularItem( 0, quality));
        this.assertSellInAfterItemUpdated(-11, this.createRegularItem( -10, quality));
    }

    @Test
    public void testRegularlItemQualityDegradesTwiceAsFastAfterSellDate() {
        final int afterSellDate = -2;
        this.assertQualityAfterItemUpdated(18, this.createRegularItem(afterSellDate, 20));

    }

    @Test
    @Ignore
    public void testConjuredItemQualityDegradesTwiceAsFastAsNormaItemsBeforeSellDate() {
        final int beforeSellDate = 1;
        this.assertQualityAfterItemUpdated(8, this.createConjuredItem(beforeSellDate, 10));
        this.assertQualityAfterItemUpdated(8, this.createConjuredItem(0, 10));
    }

    @Test
    @Ignore
    public void testConjuredItemQualityDegradesTwiceAsFastAsNormaItemsAfterSellDate() {
        final int afterSellDate = -1;
        this.assertQualityAfterItemUpdated(6, this.createConjuredItem(afterSellDate, 10));
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
