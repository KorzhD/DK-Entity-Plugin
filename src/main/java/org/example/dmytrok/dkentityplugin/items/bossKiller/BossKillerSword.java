package org.example.dmytrok.dkentityplugin.items.bossKiller;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BossKillerSword {

    public static ItemStack getBossKiller() {
        ItemStack bossKiller = new ItemStack(Material.STICK);
        ItemMeta bossKillerMeta = bossKiller.getItemMeta();
        bossKillerMeta.setDisplayName("Boss Killer");
        bossKillerMeta.addEnchant(Enchantment.DAMAGE_ALL, 1000, true);
        bossKiller.setItemMeta(bossKillerMeta);
        return bossKiller;
    }
}
