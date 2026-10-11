package com.calebtsb43.brassknucklesmod;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("brassknucklesmod")
public
class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Brass Knuckles Mod is initializing...");
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        player.sendSystemMessage(Component.literal("Brass Knuckles Mod loaded!");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("brassknuckles").executes(context -> {
            ServerPlayer player = context.getSource().getPlayer();
            if (player != null) {
                player.sendSystemMessage(Component.literal("Brass Knuckles Mod Commands:"));
                player.sendSystemMessage(Component.literal("/brassknuckles reload - Reload animations"));
            }
            return 1;
        }).then(Commands.literal("reload").executes(context -> {
            ServerPlayer player = context.getSource().getPlayer();
            if (player != null) {
                player.sendSystemMessage(Component.literal("Reloading animations...");
                // Add reload logic here
            }
            return 1;
        })));
    }
}
