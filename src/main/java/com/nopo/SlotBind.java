package com.nopo;

import com.nopo.events.SlotClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.nopo.UtilsKt.getSlotToBind;
import static com.nopo.UtilsKt.shouldBeEnabled;

public class SlotBind {

    private static final SlotBind INSTANCE = new SlotBind();

    public static SlotBind getInstance() {
        return INSTANCE;
    }

    public static class LockedSlot {
        //public boolean locked = false;
        public int boundTo = -1;

        public LockedSlot(int boundTo) {
            this.boundTo = boundTo;
        }
    }

    public int slot1 = -1;


    @SubscribeEvent
    public void onWindowClick(SlotClickEvent slotClickEvent) {
        if (!shouldBeEnabled()) return;
        if (SkywarsSorter.config.getChestOnly() && !(Minecraft.getMinecraft().currentScreen instanceof GuiChest)) return;

        LockedSlot locked = new LockedSlot(getSlotToBind(getSlotToBind(slotClickEvent.slot), slotClickEvent));

        if (locked.boundTo == -1) return;
        if (slotClickEvent.clickType == 1) {
            Slot boundSlot = slotClickEvent.guiContainer.inventorySlots.getSlotFromInventory(
                    Minecraft.getMinecraft().thePlayer.inventory,
                    locked.boundTo
            );
            if (boundSlot == null) return;

            int from, to;
            int id = slotClickEvent.slotId;
            if ((id <= 35 || (slotClickEvent.guiContainer instanceof GuiChest && id < 54)) && 0 <= locked.boundTo && locked.boundTo <= 8) {
                from = id;
                to = locked.boundTo;

            } else if (((0 <= id && id < 8)) && locked.boundTo <= 39) {
                from = boundSlot.slotNumber;
                to = id;

            } else {
                return;
            }
            Minecraft.getMinecraft().playerController.windowClick(
                    slotClickEvent.guiContainer.inventorySlots.windowId,
                    from, to, 2, Minecraft.getMinecraft().thePlayer
            );
            slotClickEvent.setCanceled(true);
        }
    }
}
