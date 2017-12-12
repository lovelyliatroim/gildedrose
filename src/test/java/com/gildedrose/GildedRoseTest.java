package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("FieldCanBeLocal")
public class GildedRoseTest {

    private final int qualityDecreaseSpeed = 1;
    private final String genericItemName = "genericItemName";
    private final int positiveSellIn = 40;
    private final int quality = 30;
    private final int sellInDecreaseSpeed = 1;

    @Test
    public void givenAGenericItemWithPositiveSellInThenTheQualityShouldDecrease() {
        Item item = new Item(genericItemName, positiveSellIn, quality);

        new GildedRose(new Item[] { item }).updateQuality();

        assertEquals(quality - qualityDecreaseSpeed, item.quality);
    }

    @Test
    public void givenAGenericItemWithPositiveSellInThenSellInShouldDecrease() {
        Item item = new Item(genericItemName, positiveSellIn, quality);

        new GildedRose(new Item[] { item }).updateQuality();

        assertEquals(positiveSellIn - sellInDecreaseSpeed, item.sellIn);
    }

}
