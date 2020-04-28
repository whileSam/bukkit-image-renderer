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

package me.trysam.imagerenderer.particle;

import org.bukkit.entity.Player;

import java.util.Collection;

public interface Renderer {

    void render(Player player);

    /**
     * Executes the method {@link #render(Player)} for more than one player.
     * @param players The players for which the method {@link #render(Player)} should be executed.
     */
    default void render(Collection<? extends Player> players) {
        players.forEach(this::render);
    }

}
