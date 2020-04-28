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

package me.trysam.imagerenderer.command;

import me.trysam.imagerenderer.ImageRendererPlugin;
import me.trysam.imagerenderer.api.ParticleImageRenderingAPI;
import me.trysam.imagerenderer.math.Quaternion;
import me.trysam.imagerenderer.math.Vec3d;
import me.trysam.imagerenderer.math.Vec3f;
import me.trysam.imagerenderer.util.Messages;
import me.trysam.imagerenderer.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Command, that can be used in-game to render an image.
 */
public class RenderImage implements CommandExecutor {


    private ImageRendererPlugin plugin;

    public RenderImage(ImageRendererPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //For code simplicity, the command can only be executed by a player.
        if(!(sender instanceof Player)) {
            sender.sendMessage(Messages.MUST_BE_PLAYER);
            return false;
        }

        //Check player permissions
        if(!(sender.hasPermission(Permissions.USE_RENDER_IMAGE_COMMAND))) {
            sender.sendMessage(Messages.NO_PERMISSIONS);
            return false;
        }

        Player player = (Player) sender;

        String imageFile;
        Vec3d location;

        //Create an "empty" quaternion, with 0° rotation.
        Quaternion rotation = new Quaternion(new Vec3f(1, 0, 0), 0);

        if(args.length == 1) {
            imageFile = args[0];
            location = Vec3d.fromLocation(player.getLocation());
        }else if(args.length >= 4) {
            imageFile = args[0];

            double x;
            double y;
            double z;

            try {
                x = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, "1 (\""+args[1]+"\")"));
                return false;
            }

            try {
                y = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, "2 (\""+args[2]+"\")"));
                return false;
            }

            try {
                z = Double.parseDouble(args[3]);
            } catch (NumberFormatException e) {
                player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, "3 (\""+args[3]+"\")"));
                return false;
            }

            location = new Vec3d(x,y,z);

            //Read the remaining arguments and translate them into rotations.
            for (int i = 4; i < args.length; i++) {
                String argument = args[i];

                if(argument.length() < 2) {
                    player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, i+" (\""+args[i]+"\")"));
                    return false;
                }

                Vec3f axis;

                if(argument.toLowerCase().startsWith("x")) {
                    axis = new Vec3f(1, 0, 0);
                }else if(argument.toLowerCase().startsWith("y")) {
                    axis = new Vec3f(0, 1, 0);
                }else if(argument.toLowerCase().startsWith("z")) {
                    axis = new Vec3f(0, 0, 1);
                }else {
                    player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, i+" (\""+args[i]+"\")"));
                    return false;
                }

                String number = argument.substring(1);
                double angle;

                try {
                    angle = Double.parseDouble(number);
                } catch (NumberFormatException e) {
                    player.sendMessage(Messages.replace(Messages.INVALID_SYNTAX, i+" (\""+args[i]+"\")"));
                    return false;
                }

                rotation = rotation.multiplied(new Quaternion(axis, angle));
            }
        }else {
            player.sendMessage(Messages.replace(Messages.USAGE, "renderimage <filename> [<x> <y> <z>] [rotation]"));
            return false;
        }

        if(imageFile == null) {
            player.sendMessage(Messages.ERROR);
            return false;
        }

        try {
            ParticleImageRenderingAPI api = new ParticleImageRenderingAPI(new File(plugin.getDataFolder(), "/img/"+ imageFile), location);

            api.setRotation(rotation);
            api.renderImage(Bukkit.getOnlinePlayers());

            player.sendMessage(Messages.SUCCESS);

            return true;
        } catch (IOException e) {
            player.sendMessage(Messages.COULD_NOT_FIND_IMAGE);
        } catch (IllegalArgumentException ex) {
            player.sendMessage(Messages.WRONG_IMAGE_DIMENSIONS);
        }
        System.out.println();

        return false;
    }
}
