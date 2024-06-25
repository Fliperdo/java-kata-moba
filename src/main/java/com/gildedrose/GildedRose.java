package com.gildedrose;

/*
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

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACK_STAGE = "Backstage passes to a TAFKAL80ETC concert";
    public final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            // group 1
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACK_STAGE)) {
                if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                    item.quality--;
                }
            } else {
                if (item.quality < 50) {
                    item.quality++;

                    if (item.name.equals(BACK_STAGE)) {
                        if (item.sellIn < 11 && item.quality < 50) {
                            item.quality++;
                        }

                        if (item.sellIn < 6 && item.quality < 50) {
                            item.quality++;
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn--;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(BACK_STAGE)) {
                        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                            item.quality--;
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality++;
                    }
                }
            }

        }
    }
}
