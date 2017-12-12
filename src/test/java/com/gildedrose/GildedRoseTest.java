package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private final int qualityDecreaseSpeed = 1;

    @Test
    public void givenAnItemWithPositiveSellInAndPositiveQualityThenTheQualityShouldDecrease() {
        final int quality = 30;
        final int sellIn = 40;
        final Item item = new Item("foo", sellIn, quality);

        new GildedRose(new Item[] { item }).updateQuality();

        assertEquals(quality - qualityDecreaseSpeed, item.quality);
    }


}
