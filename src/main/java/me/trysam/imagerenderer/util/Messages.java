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

package me.trysam.imagerenderer.util;

public class Messages {

    public static final String PREFIX = "§8[§dImageParticles§8]§r ";

    public static final String CREATING_IMG_DIRECTORY = PREFIX+"§aCouldn't find §eimg§a directory. Creating a new one.";

    public static final String ENABLED = PREFIX+"§aPlugin successfully enabled.";
    public static final String DISABLED = PREFIX+"§cPlugin successfully disabled.";

    public static final String MUST_BE_PLAYER = PREFIX+"§cCommand executor must be a player!";
    public static final String NO_PERMISSIONS = PREFIX+"§cYou are not permitted to use this command!";
    public static final String INVALID_SYNTAX = PREFIX+"§cInvalid Syntax! Error in argument {0}";
    public static final String USAGE = PREFIX+"§7Usage§8: §e/{0}";
    public static final String ERROR = PREFIX+"§cSomething went wrong. Check your input!";
    public static final String COULD_NOT_FIND_IMAGE = PREFIX+"§cCould not find image!";
    public static final String WRONG_IMAGE_DIMENSIONS = PREFIX+"§cImage pixel sum must be less than or equal 64^2!";
    public static final String SUCCESS = PREFIX+"§aSuccessfully rendered image.";

    /**
     * Replaces all {n} placeholders substitute arguments.<br>
     * {0} refers to the substitutes array at index 0.<br>
     * If there are {n} bigger than the highest substitutes index,
     * the {n} will just be ignored
     * @param input The string where the substitutes should go.
     * @param substitutes The substitute array.
     * @return input, all {n} replaced with substitutes
     */
    public static String replace(String input, String... substitutes) {
        for (int i = 0; i < substitutes.length; i++) {
            input = input.replace("{"+i+"}", substitutes[i]);
        }
        return input;
    }

}
