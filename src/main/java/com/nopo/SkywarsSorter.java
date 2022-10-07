package com.nopo;

import com.nopo.commands.Commands;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.nopo.UtilsKt.updateScoreboard;

@Mod(modid = "skywars-sorter", version = "1.0")
public class SkywarsSorter {

    Commands commands;
    public static SkywarsSorter INSTANCE;
    public static Config config = Config.INSTANCE;
    public long joinedWorld = -1;
    public long unloadedWorld = -1;
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        INSTANCE = this;
        Config.INSTANCE.init();
        Config.INSTANCE.preload();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(SlotBind.getInstance());
        commands = new Commands();
    }
    public GuiScreen display = null;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (Minecraft.getMinecraft().theWorld == null) return;
        if (Minecraft.getMinecraft().thePlayer == null) return;
        if (System.currentTimeMillis() - joinedWorld > 500 &&
                System.currentTimeMillis() - unloadedWorld > 500) {
            updateScoreboard();
        }

        if (display != null) {
            Minecraft.getMinecraft().displayGuiScreen(display);
            display = null;
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        joinedWorld = System.currentTimeMillis();
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        unloadedWorld = System.currentTimeMillis();
    }
}
