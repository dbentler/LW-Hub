package me.ezjamo.hub.listeners;

import me.ezjamo.hub.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToggleVisibilityListener implements Listener {
    ItemStack shown = new ItemStack(Material.INK_SACK, 1, (short) 10);
    ItemStack hidden = new ItemStack(Material.INK_SACK, 1, (short) 8);
    ItemMeta shownMeta = shown.getItemMeta();
    ItemMeta hiddenMeta = hidden.getItemMeta();

    private void hidePlayers(Player player) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(online);
        }
        player.getInventory().setItem(8, hidden);
        player.sendMessage(Utils.color("&cAll players have been hidden."));
    }

    private void showPlayers(Player player) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            player.showPlayer(online);
        }
        player.getInventory().setItem(8, shown);
        player.sendMessage(Utils.color("&aAll players are now visible."));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        shownMeta.setDisplayName(Utils.color("&a&oVisible"));
        hiddenMeta.setDisplayName(Utils.color("&7&oHidden"));
        shown.setItemMeta(shownMeta);
        hidden.setItemMeta(hiddenMeta);
        player.getInventory().setItem(8, shown);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (event.getAction() == Action.PHYSICAL || event.getItem() == null || event.getItem().getType() == Material.AIR ||
                event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (item != null && item.isSimilar(shown)) {
            event.setCancelled(true);
            hidePlayers(player);
        }
        if (item != null && item.isSimilar(hidden)) {
            event.setCancelled(true);
            showPlayers(player);
        }
    }
}
