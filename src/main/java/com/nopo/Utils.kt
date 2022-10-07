package com.nopo

import com.nopo.events.SlotClickEvent
import net.minecraft.client.Minecraft
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.inventory.Slot
import net.minecraft.item.Item
import java.util.*

fun getSlotOrOff(i: Int): Int {
    return if (i < 9) i else -1
}
fun getSlotToBind(slot: Slot): Int {
    val item = slot.stack?.item ?: return -1
    return when (item) {
        getBlock(item) -> getSlotOrOff(SkywarsSorter.config.blocksSlot)
        getSword(item) -> getSlotOrOff(SkywarsSorter.config.swordSlot)
        getThrowable(item) -> getSlotOrOff(SkywarsSorter.config.projectileSlot)
        Items.potionitem -> getSlotOrOff(SkywarsSorter.config.potionSlot)
        Items.bow -> getSlotOrOff(SkywarsSorter.config.bowSlot)
        Items.golden_apple -> getSlotOrOff(SkywarsSorter.config.gappleSlot)
        Items.ender_pearl -> getSlotOrOff(SkywarsSorter.config.pearlSlot)
        Items.fishing_rod -> getSlotOrOff(SkywarsSorter.config.rodSlot)
        Items.water_bucket -> getSlotOrOff(SkywarsSorter.config.waterSlot)
        Items.lava_bucket -> getSlotOrOff(SkywarsSorter.config.lavaSlot)
        getAxe(item) -> getSlotOrOff(SkywarsSorter.config.axeSlot)
        else -> -1
    }
}

fun getSlotToBind(slotId: Int, slotClickEvent: SlotClickEvent): Int {
    if (slotId == -1) return -1
    if (slotClickEvent.slot.stack.stackSize == 1) return isItemBetter(slotId, slotClickEvent)
    if (Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slotId]?.stackSize == null) return slotId
    val itemStack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slotId]
    if (!areItemsTheSame(slotClickEvent.slot.stack.item, itemStack.item) || slotClickEvent.slot.stack.stackSize >= itemStack.stackSize) return slotId
    return -1
}

fun areItemsTheSame(item: Item, item2: Item): Boolean {
    if (item == Item.getItemFromBlock(Blocks.stone) && item2 == Item.getItemFromBlock(Blocks.planks)) return true
    if (item == Item.getItemFromBlock(Blocks.planks) && item2 == Item.getItemFromBlock(Blocks.stone)) return true
    if (item == Items.egg && item2 == Items.snowball) return true
    if (item == Items.snowball && item2 == Items.egg) return true
    return item == item2
}

fun isItemBetter(slotId: Int, slotClickEvent: SlotClickEvent): Int {
    val item = slotClickEvent.slot.stack.item
    if (getWeaponEnum(item) != WEAPON.NONE) {
        if (Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slotId]?.stackSize != null) {
            if (getWeaponEnum(item) <= getWeaponEnum(Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slotId].item)) {
                return slotId
            }
        } else {
            return slotId
        }
    } else {
        return slotId
    }
    return -1
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

fun getAxe(item: Item?): Item? {
    return when (item) {
        Items.diamond_axe -> item
        Items.iron_axe -> item
        Items.golden_axe -> item
        Items.stone_axe -> item
        Items.wooden_axe -> item
        else -> null
    }
}

fun getWeaponEnum(item: Item?): WEAPON {
    return when (item) {
        Items.diamond_sword -> WEAPON.DIAMOND
        Items.diamond_axe -> WEAPON.DIAMOND
        Items.iron_sword -> WEAPON.IRON
        Items.iron_axe -> WEAPON.IRON
        Items.golden_sword -> WEAPON.GOLD
        Items.golden_axe -> WEAPON.GOLD
        Items.stone_sword -> WEAPON.STONE
        Items.stone_axe -> WEAPON.STONE
        Items.wooden_sword -> WEAPON.WOOD
        Items.wooden_axe -> WEAPON.WOOD
        else -> WEAPON.NONE
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

enum class WEAPON(level: Int) {
    DIAMOND(5), IRON(4), GOLD(3), STONE(2), WOOD(1), NONE(0)
}

fun shouldBeEnabled(): Boolean {
    if (!SkywarsSorter.config.enabled) return false
    if (!SkywarsSorter.config.skywarsOnly && !SkywarsSorter.config.duelsOnly) return true
    return hasSkywarsScoreboard

}

var hasSkywarsScoreboard = false

fun updateScoreboard() {
    val mc = Minecraft.getMinecraft()
    if (mc?.theWorld != null && mc.thePlayer != null) {
        if (mc.isSingleplayer || mc.thePlayer.clientBrand == null ||
            !mc.thePlayer.clientBrand.lowercase(Locale.getDefault()).contains("hypixel")
        ) {
            hasSkywarsScoreboard = false
            return
        }
        val scoreboard = mc.theWorld.scoreboard
        val sidebarObjective = scoreboard.getObjectiveInDisplaySlot(1)
        if (sidebarObjective != null) {
            val objectiveName = sidebarObjective.displayName.replace("(?i)\\u00A7.".toRegex(), "")
                if (objectiveName.startsWith("SKYWARS") && SkywarsSorter.config.skywarsOnly) {
                    hasSkywarsScoreboard = true
                    return
                } else if (objectiveName.startsWith("DUELS") && SkywarsSorter.config.duelsOnly) {
                    hasSkywarsScoreboard = true
                    return
                }
        }
        hasSkywarsScoreboard = false
    }
}