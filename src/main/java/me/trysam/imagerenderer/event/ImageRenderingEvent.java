package me.trysam.imagerenderer.event;

import me.trysam.imagerenderer.particle.ImageRenderer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class ImageRenderingEvent extends Event implements Cancellable {

    protected static HandlerList handlers = new HandlerList();

    private boolean cancelled;

    private ImageRenderer renderer;

    public ImageRenderingEvent(ImageRenderer renderer) {
        this.renderer = renderer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public ImageRenderer getRenderer() {
        return renderer;
    }
}
