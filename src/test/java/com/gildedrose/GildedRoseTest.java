package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/* Gilded Rose.java updateQuality()
If item is Aged Brie, check quality > 50, quality++,
If item is Backstage Pass, check quality > 50, quality++,
    then if: sellIn < 11, quality < 50 still, quality++
    also if: sellIn < 6, quality < 50 still, quality++
If item is Sulfuras, and quality > 0, quality--

If item is NOT Sulfuras, sellIn--

If sellIn < 0:
    If item is Aged Brie, check quality < 50, quality ++
    If item is Backstage Pass, set quality to quality - quality (set to 0)
    Otherwise, if quality > 0, and item is NOT Sulfuras, quality--
    (If Sulfuras, ignore in practice)
*/

class GildedRoseTest {
    @Test
    void checkBackStagePass_SellInLessThan6_QualityOf45() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +3
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void checkBackStagePass_SellInLessThan6_QualityOf48() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +2
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void checkBackStagePass_SellInLessThan6_QualityOf47() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +2
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void checkBackStagePass_SellInOf6_QualityOf45() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +1
        assertEquals(47, app.items[0].quality);
    }

    @Test
    void checkBackStagePass_SellInLessThan11_QualityOf48(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +2
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void checkBackStagePass_SellInGreaterThan12_QualityOf48(){
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +1
        assertEquals(49, app.items[0].quality);
    }

    @Test
    void checkQualityAndSellIn() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality -1
        assertEquals(19, app.items[0].quality);
        // sellin -1
        assertEquals(9, app.items[0].sellIn);
    }

    @Test
    void checkWithoutQualityAndSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality same
        assertEquals(80, app.items[0].quality);
        // sellin same
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void checkSellInNegative() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", -1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality -2
        assertEquals(18, app.items[0].quality);
        // sellin -1
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void checkIncreasesInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +1
        assertEquals(1, app.items[0].quality);
        // sellin -1
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void checkNoIncreasesQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality same
        assertEquals(50, app.items[0].quality);
        // sellin -1
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void checkIncreasesInQualityWithNegativeSellin() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality +2
        assertEquals(12, app.items[0].quality);
        // sellin -1
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void checkNoIncreasesQualityWithNegativeSellin() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality same
        assertEquals(50, app.items[0].quality);
        // sellin 1
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void checkOnNotAgedAndNotBackStageSellinIsNegative() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1,  70) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // quality same
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void checkQualityDropsToZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality drops to 0
        assertEquals(0, app.items[0].quality);
        // sellin decreases by 1
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    void checkQualityNeverMoreThan50() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();

        // quality 50
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void checkQualityNeverNegative() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 0
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void checkOnSellingNothiger() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 1,  70) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        // quality same
        assertNotEquals(0, app.items[0].quality);
    }

    @Test
    void checkSulfurasSellInIsZero() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 0
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void checkNegativeSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 80
        assertEquals(80, app.items[0].quality);
        // sellIn -1
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void checkSellInZero() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality -2
        assertEquals(18, app.items[0].quality);
        // sellIn -1
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void checkQualityUnderOne(){
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 0
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void checkQualityUnderTwo(){
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 0
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void checkAgedBrieQuality49(){
        Item[] items = new Item[] { new Item("Aged Brie", -1, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 1
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void checkAgedBrieQuality50(){
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // quality 0
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void testItemToString(){
        Item item = new Item("Aged Brie", -1, 49);
        assertEquals("Aged Brie, -1, 49", item.toString());
    }


}
