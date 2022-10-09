package com.nopo

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

object Config: Vigilant(File("./config/skywars-sorter.toml")) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Enabled",
        description = "If the 2 settings under are off then the mod will work everywhere",
        category = "Sorting"
    )
    var enabled = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Only Enable While In A Chest",
        description = "Doesn't sort when you are in your inventory",
        category = "Sorting"
    )
    var chestOnly = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Enable In Skywars",
        description = "Enable In Skywars",
        category = "Sorting"
    )
    var skywarsOnly = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Enable In Duels",
        description = "Enable In Duels",
        category = "Sorting"
    )
    var duelsOnly = true

    @Property(
        type = PropertyType.SELECTOR,
        name = "Sword",
        description = "Hotbar slot to sort swords into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var swordSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Axe",
        description = "Hotbar slot to sort axes into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var axeSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Blocks",
        description = "Hotbar slot to sort blocks into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var blocksSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Projectiles",
        description = "Hotbar slot to sort eggs and snowballs into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var projectileSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Potions",
        description = "Hotbar slot to sort potions into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var potionSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Bow",
        description = "Hotbar slot to sort bows into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var bowSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Gapple",
        description = "Hotbar slot to sort gapples into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var gappleSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Ender Pearls",
        description = "Hotbar slot to sort pearls into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var pearlSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Fishing Rod",
        description = "Hotbar slot to sort rods into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var rodSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Water Bucket",
        description = "Hotbar slot to sort water buckets into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var waterSlot = 9

    @Property(
        type = PropertyType.SELECTOR,
        name = "Lava Bucket",
        description = "Hotbar slot to sort lava buckets into",
        category = "Sorting",
        options = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "Off"]
    )
    var lavaSlot = 9

    fun init() {
        initialize()
    }
}