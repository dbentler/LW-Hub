package me.ezjamo.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

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
        public void onPlateLaunch(PlayerInteractEvent e) {
        	Player p = (Player) e.getPlayer();
            if(e.getAction().equals(Action.PHYSICAL)){
                        p.setVelocity(p.getLocation().getDirection().multiply(2.7f).setY(2.7f));
                        p.playSound(p.getLocation(), Sound.EXPLODE, 1.2f, 1.2f);
                }
        }
}