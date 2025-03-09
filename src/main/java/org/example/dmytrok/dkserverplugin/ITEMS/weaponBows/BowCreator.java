package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BowCreator {


    public static ItemStack getWoodenBow() {
        ItemStack woodenBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta woodenBowMeta = woodenBow.getItemMeta();
        woodenBowMeta.setDisplayName("Wooden Bow");
        woodenBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§7Newbie bow");
        lore.add("");
        lore.add("§d§lRarity: §8Common");
        lore.add("§a§lLvl: §e2");
        lore.add("§4§lDamage: §74");
        lore.add("");
        lore.add("");
        lore.add("");
        woodenBowMeta.setLore(lore);
        woodenBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        woodenBow.setItemMeta(woodenBowMeta);
        return woodenBow;
    }
}
