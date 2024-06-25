package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @Test
    void testBackstagePasses() {
        testItem(BACK_STAGE, 5, 45, 48);
        testItem(BACK_STAGE, 5, 48, 50);
        testItem(BACK_STAGE, 5, 47, 50);
        testItem(BACK_STAGE, 6, 45, 47);
        testItem(BACK_STAGE, 10, 48,50);
        testItem(BACK_STAGE, 12, 48,49);
        testItem(BACK_STAGE, -1, 70, 0);
        testItem(BACK_STAGE, -1, 20, 0);
        testItem(BACK_STAGE, -1, 70,0);
        testItem(BACK_STAGE, 0, 20, 0);
    }

    @Test
    void testSulfras() {
        testItem(SULFURAS, 1, 80, 80, 1);
        testItem(SULFURAS, -1, 80, 80, -1);
    }

    @Test
    void testAgedBrie() {
        testItem(AGED_BRIE, 2, 0, 1);
        testItem(AGED_BRIE, 2, 50, 50);
        testItem(AGED_BRIE, -1, 10, 12);
        testItem(AGED_BRIE, -1, 50, 50);
        testItem(AGED_BRIE, 2, 49, 50);
        testItem(AGED_BRIE, -1, 48, 50);
        testItem(AGED_BRIE, -1, 49, 50);
    }

    @Test
    void testConjured() {
        testItem("Conjured Hat", 10, 20, 18, 9);
        testItem("Conjured Wand", 0, 20, 16, -1);
    }

    @Test
    void testRandomItems() {
        testItem("+5 Dexterity Vest", 10, 0, 0);
        testItem("+5 Dexterity Vest", 0, 20, 18);
        testItem("+5 Dexterity Vest", 10, 20, 19);
        testItem("+5 Dexterity Vest", 0, 1, 0);
        testItem("+5 Dexterity Vest", 0, 2, 0);
        testItem("+5 Dexterity Vest", -1, 20, 18);
    }

    @Test
    void testItemToString() {
        Item item = new Item("Aged Brie", -1, 49);
        assertEquals("Aged Brie, -1, 49", item.toString());
    }

    void testItem(String name, int sellIn, int quality, Integer expectedQuality)
    {
        testItem(name, sellIn, quality, expectedQuality, sellIn - 1);
    }

    void testItem(String name, int sellIn, int quality, Integer expectedQuality, Integer expectedSellin)
    {
        if (null == expectedSellin) {
            expectedSellin = sellIn - 1;
        }
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(expectedQuality, app.items[0].quality);
        assertEquals(expectedSellin, app.items[0].sellIn);
    }
}

