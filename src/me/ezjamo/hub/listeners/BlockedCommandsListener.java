package me.ezjamo.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandsListener implements Listener {
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void cmd(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("/me")
				|| e.getMessage().equalsIgnoreCase("/execute") 
				|| (e.getMessage().contains("/execute ") 
				|| (e.getMessage().contains("/me ")
				|| (e.getMessage().contains("/server ")
				|| (e.getMessage().contains("/minecraft:me ")))))) {
			e.setCancelled(true);  
			e.getPlayer().kickPlayer("Disconnected");
		}
	}
}
