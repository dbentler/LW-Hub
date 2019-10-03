package me.ezjamo.hub;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, String... loreString) {

        ItemStack item;
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<String> lore = new ArrayList();

        item = new ItemStack(Material.getMaterial(materialId), amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName (color(displayName));
        for (String s : loreString) {
            lore.add(color(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot, item);
        return item;
    }
}
