package com.nopo;

import com.nopo.events.SlotClickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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

    /**
     * Hotbar 0-8
     * <p>
     * Inv 9-35
     * <p>
     * Armor 36-39 (36 being boots)
    */

    @SubscribeEvent
    public void onWindowClick(SlotClickEvent slotClickEvent) {
        LockedSlot locked = new LockedSlot(GetSlotToBindKt.getSlotToBind(slotClickEvent.slot));

        System.out.println(slotClickEvent.slotId);

        if (slotClickEvent.clickType == 1 &&
                locked.boundTo != -1) {
            Slot boundSlot = slotClickEvent.guiContainer.inventorySlots.getSlotFromInventory(
                    Minecraft.getMinecraft().thePlayer.inventory,
                    locked.boundTo
            );

            if (boundSlot == null) {
                return;
            }

            //LockedSlot boundLocked = new LockedSlot(36);

            int from, to;
            int id = slotClickEvent.slotId;
            if ((id >= 9 || slotClickEvent.guiContainer instanceof GuiChest) && 0 <= locked.boundTo && locked.boundTo <= 8) {
                from = id;
                to = locked.boundTo;
                //boundLocked.boundTo = id;
            } else if (((0 <= id && id < 8) || (id > 35 && id < 40)) && locked.boundTo <= 39) {
                from = boundSlot.slotNumber;
                to = id;
            } else {
                return;
            }
            System.out.println("from: " + from);
            Minecraft.getMinecraft().playerController.windowClick(
                    slotClickEvent.guiContainer.inventorySlots.windowId,
                    from, to, 2, Minecraft.getMinecraft().thePlayer
            );
            slotClickEvent.setCanceled(true);
        }
    }
}
