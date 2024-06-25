package com.gildedrose;

class GildedRose {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACK_STAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured";
    public final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case SULFURAS: { break; }
                case AGED_BRIE: {
                    if (item.quality < 50) item.quality++;
                    item.sellIn--;
                    if (item.sellIn < 0 && item.quality < 50) item.quality++;
                    break;
                }
                case BACK_STAGE: {
                    if (item.quality < 50) {
                        item.quality++;
                        if (item.sellIn < 11 && item.quality < 50) item.quality++;
                        if (item.sellIn < 6 && item.quality < 50) item.quality++;
                    }
                    item.sellIn--;
                    if (item.sellIn < 0) item.quality = 0;
                    break;
                }
                default: {
                    item.sellIn--;
                    if (item.name.startsWith("Conjured")) {
                        item.quality--;
                    }
                    // names that aren't above
                    item.quality--;
                    if (item.sellIn < 0 && item.quality > 0) {
                        if (item.name.startsWith("Conjured")) {
                            item.quality--;
                        }
                        item.quality--;
                    }
                    break;
                }
            }
            if (item.quality < 0) item.quality = 0;
        }
    }
}
