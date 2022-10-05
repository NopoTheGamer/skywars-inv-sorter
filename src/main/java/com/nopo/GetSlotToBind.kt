package com.nopo

import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.inventory.Slot
import net.minecraft.item.Item

fun getSlotToBind(slot: Slot): Int {
    val item = slot.stack?.item ?: return -1
    if (item == Item.getItemFromBlock(Blocks.stone)) return 8
    else if (item == getSword(item)) return 3
    else if (item == getThrowable(item)) return 2
    else if (item == Items.potionitem) return 0
    else if (item == Items.bow) return 4
    else return -1
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
    val a = when (item) {
        Item.getItemFromBlock(Blocks.stone) -> item
        else -> null
    }
    println(a)
    return null
}