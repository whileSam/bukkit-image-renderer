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
