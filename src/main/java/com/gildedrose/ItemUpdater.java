package com.gildedrose;

public interface ItemUpdater {

    /**
     * Updates a given item according to the business rules.
     * Can change its quality and/or sellIn properties.
     * @param item
     */
    void update(Item item);
}
