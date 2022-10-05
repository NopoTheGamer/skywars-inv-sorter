package com.nopo

import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.inventory.Slot
import net.minecraft.item.Item

fun getSlotToBind(slot: Slot): Int {
    val item = slot.stack?.item ?: return -1
    return if (item == getBlock(item)) 8
    else if (item == getSword(item)) 3
    else if (item == getThrowable(item)) 2
    else if (item == Items.potionitem) 0
    else if (item == Items.bow) 4
    else if (item == Items.diamond_chestplate) 38
    else -1
}

fun getSword(item: Item?): Item? {
    return when (item) {
        Items.diamond_sword -> item
        Items.iron_sword -> item
        Items.golden_sword -> item
        Items.stone_sword -> item
        Items.wooden_sword -> item
        else -> null
    }
}

fun getThrowable(item: Item?): Item? {
    return when (item) {
        Items.snowball -> item
        Items.egg -> item
        else -> null
    }
}

fun getBlock(item: Item?): Item? {
    return when (item) {
        Item.getItemFromBlock(Blocks.stone) -> item
        Item.getItemFromBlock(Blocks.planks) -> item
        else -> null
    }
}

fun isChestplate(item: Item?): Boolean {
    return when (item) {
        Items.diamond_chestplate -> true
        Items.iron_chestplate -> true
        Items.golden_chestplate -> true
        Items.chainmail_chestplate -> true
        Items.leather_chestplate -> true
        else -> false
    }
}