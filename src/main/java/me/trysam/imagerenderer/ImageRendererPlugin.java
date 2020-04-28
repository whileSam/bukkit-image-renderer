/*
 * Bukkit Image Renderer - A simple plugin to display images in Minecraft via particles.
 * Copyright (c) 2020. Samuel Eichelmann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

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
