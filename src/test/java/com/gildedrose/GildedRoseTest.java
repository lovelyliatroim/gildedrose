package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({ "FieldCanBeLocal", "LocalCanBeFinal" })
public class GildedRoseTest {

    private static final String GENERIC_NAME = "GENERIC_NAME";
    private static final int QUALITY = 30;
    private static final int POSITIVE_SELL_IN = 40;
    private static final int NEGATIVE_SELL_IN = -40;

    private static final int QUALITY_DECREASE_SPEED = 1;
    private static final int DOUBLE_QUALITY_DECREASE_SPEED = QUALITY_DECREASE_SPEED * 2;
    private static final int SELL_IN_DECREASE_SPEED = 1;
    private static final int MINIMUM_QUALITY = 0;

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

    private void updateItem(Item item) {
        new GildedRose(new Item[] { item }).updateQuality();
    }

}
