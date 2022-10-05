package com.nopo;

import com.nopo.commands.Commands;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "nopopvpmod", version = "1.0.0")
public class ExampleMod {

    Commands commands;
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(SlotBind.getInstance());
        commands = new Commands();
    }
}
