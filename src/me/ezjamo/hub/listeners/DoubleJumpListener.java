package me.ezjamo.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import me.ezjamo.hub.selector.SelectorListener;

import org.bukkit.event.Listener;

public class DoubleJumpListener implements Listener {
        @SuppressWarnings("deprecation")
        @EventHandler
        public void onPlayerMove(PlayerMoveEvent e) {
            if (e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ()) return;
                Player p = e.getPlayer();
                if (p.getGameMode() == GameMode.CREATIVE) {
                        return;
                	}
                if (!p.getAllowFlight() && (!p.isOnGround())) {
                        p.setAllowFlight(true);
                }
        }
        @EventHandler
        public void onLaunch(PlayerToggleFlightEvent e) {
                Player p = e.getPlayer();
                e.setCancelled(true);
                p.setAllowFlight(false);
                p.setFlying(false);
                p.setFallDistance(0.0f);
                p.setVelocity(p.getLocation().getDirection().multiply(2.5f).setY(1.5));
                p.playSound(p.getLocation(), Sound.EXPLODE, 1.2f, 1.2f);
        }
        
        @EventHandler
        public void onPlateLaunch(PlayerMoveEvent e) {
        	Player p = (Player) e.getPlayer();
            if (p.getLocation().getBlock().getType() == Material.GOLD_PLATE) {
            	p.setVelocity(p.getLocation().getDirection().multiply(3.5).setY(1.0));
            	p.playSound(p.getLocation(), Sound.EXPLODE, 1.2f, 1.2f);
            }
        }
        
        @EventHandler
        public void onWaterTouch(PlayerMoveEvent e) {
        	Player p = (Player) e.getPlayer();
        	if (e.getPlayer().getLocation().getBlock().getType() == Material.STATIONARY_WATER || e.getPlayer().getLocation().getBlock().getType() == Material.WATER) {
        		Location loc = new Location(p.getWorld(), 0.500, 101, 0.500);
    			p.teleport(loc);
        		SelectorListener.selector(p);
        	}
        }
}