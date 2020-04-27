package me.trysam.imagerenderer;

import me.trysam.imagerenderer.command.RenderImage;
import me.trysam.imagerenderer.event.ImageRenderingEvent;
import me.trysam.imagerenderer.util.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ImageRendererPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        //Create img directory if not exists.
        File imgDir = new File(getDataFolder(), "/img/");

        if(imgDir.mkdirs()) {
            getServer().getConsoleSender().sendMessage(Messages.CREATING_IMG_DIRECTORY);
        }

        //Command registration
        getCommand("renderimage").setExecutor(new RenderImage(this));

        getServer().getConsoleSender().sendMessage(Messages.ENABLED);
    }

    ///ri steve.jpg 2160 155 -1022 x90 z270

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Messages.DISABLED);
    }
}
