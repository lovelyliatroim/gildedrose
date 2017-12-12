package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({ "FieldCanBeLocal", "LocalCanBeFinal" })
public class GildedRoseTest {

    private static final String GENERIC_NAME = "GENERIC_NAME";
    private static final String AGED_BRIE = "Aged Brie";
    private static final int QUALITY = 30;
    private static final int MINIMUM_QUALITY = 0;
    private static final int SELL_IN = 10;
    private static final int POSITIVE_SELL_IN = 40;
    private static final int NEGATIVE_SELL_IN = -40;
    private static final int QUALITY_INCREASE_SPEED = 1;
    private static final int QUALITY_DECREASE_SPEED = 1;
    private static final int DOUBLE_QUALITY_DECREASE_SPEED = QUALITY_DECREASE_SPEED * 2;
    private static final int SELL_IN_DECREASE_SPEED = 1;
    private static final int MAXIMUM_QUALITY = 50;
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    public void givenAGenericItemWithPositiveSellInThenTheQualityShouldDecrease() {
        Item item = new Item(GENERIC_NAME, POSITIVE_SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(QUALITY - QUALITY_DECREASE_SPEED, item.quality);
    }

    @Test
    public void givenAGenericItemWithPositiveSellInThenSellInShouldDecrease() {
        Item item = new Item(GENERIC_NAME, POSITIVE_SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(POSITIVE_SELL_IN - SELL_IN_DECREASE_SPEED, item.sellIn);
    }

    @Test
    public void givenAGenericItemWithNegativeSellInThenTheQualityShouldDecreaseTwiceAsFast() {
        Item item = new Item(GENERIC_NAME, NEGATIVE_SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(QUALITY - DOUBLE_QUALITY_DECREASE_SPEED, item.quality);
    }

    @Test
    public void givenAGenericItemWithNegativeSellInAndMinimumQualityThenTheQualityShouldStayTheSame() {
        Item item = new Item(GENERIC_NAME, NEGATIVE_SELL_IN, MINIMUM_QUALITY);

        updateItem(item);

        assertEquals(MINIMUM_QUALITY, item.quality);
    }

    @Test
    public void givenAnAgedBrieThenTheQualityShouldIncrease() {
        Item item = new Item(AGED_BRIE, POSITIVE_SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(QUALITY + QUALITY_INCREASE_SPEED, item.quality);
    }

    @Test
    public void givenAnAgedBrieWithMaximumQualityThenTheQualityShouldStayTheSame() {
        Item item = new Item(AGED_BRIE, POSITIVE_SELL_IN, MAXIMUM_QUALITY);

        updateItem(item);

        assertEquals(MAXIMUM_QUALITY, item.quality);
    }

    @Test
    public void givenASulfurasThenTheQualityShouldStayTheSame() {
        Item item = new Item(SULFURAS, SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(QUALITY, item.quality);
    }

    @Test
    public void givenASulfurasThenTheSellInShouldStayTheSame() {
        Item item = new Item(SULFURAS, SELL_IN, QUALITY);

        updateItem(item);

        assertEquals(SELL_IN, item.sellIn);
    }

    private void updateItem(Item item) {
        new GildedRose(new Item[] { item }).updateQuality();
    }

}
