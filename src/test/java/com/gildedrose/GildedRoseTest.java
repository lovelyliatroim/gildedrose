package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("FieldCanBeLocal")
public class GildedRoseTest {

    private final int qualityDecreaseSpeed = 1;
    private final String genericItemName = "genericItemName";
    private final int positiveSellIn = 40;
    private final int anyQuality = 30;

    @Test
    public void givenAnItemWithPositiveSellInAndPositiveQualityThenTheQualityShouldDecrease() {
        Item item = new Item(genericItemName, positiveSellIn, anyQuality);

        new GildedRose(new Item[] { item }).updateQuality();

        assertEquals(anyQuality - qualityDecreaseSpeed, item.quality);
    }


}
