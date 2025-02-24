package org.example.dmytrok.dkentityplugin.items.weapon;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WeaponCreator {


    public static ItemStack getFrostScythe() {
        ItemStack frostScythe = new ItemStack(Material.WOOD_SWORD);
        ItemMeta frostScytheMeta = frostScythe.getItemMeta();
        frostScytheMeta.setDisplayName("Frost Scythe");
        frostScytheMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§l§bThe blade is forged from the Guardian Core.");
        lore.add("§l§bThe handle is made from his flesh.");
        lore.add("§l§bCapable of cutting the highest mountain peaks.");
        frostScytheMeta.setLore(lore);
        frostScytheMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        frostScytheMeta.addEnchant(Enchantment.DAMAGE_ALL, 12, true);
        frostScythe.setItemMeta(frostScytheMeta);
        return frostScythe;
    }
    public static ItemStack getMoonlight() {
        ItemStack moonLight = new ItemStack(Material.WOOD_SWORD);
        ItemMeta moonLightMeta = moonLight.getItemMeta();
        moonLightMeta.setDisplayName("Moonlight");
        moonLightMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§bForged from icy peaks that fed on moonlight for eternity");
        moonLightMeta.setLore(lore);
        moonLightMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        moonLightMeta.addEnchant(Enchantment.DAMAGE_ALL,8, true);
        moonLight.setItemMeta(moonLightMeta);
        return moonLight;
    }

    public static ItemStack getDemonSlayer() {
        ItemStack demonSlayer = new ItemStack(Material.WOOD_SWORD);
        ItemMeta demonSlayerMeta = demonSlayer.getItemMeta();
        demonSlayerMeta.setDisplayName("Demon Slayer");
        demonSlayerMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§l§4A blade soaked in demon blood, waiting for the next battle");
        demonSlayerMeta.setLore(lore);
        demonSlayerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonSlayerMeta.addEnchant(Enchantment.DAMAGE_ALL, 11, true);
        demonSlayer.setItemMeta(demonSlayerMeta);
        return demonSlayer;
    }

    public static ItemStack getDemonBlade() {
        ItemStack demonBlade = new ItemStack(Material.WOOD_SWORD);
        ItemMeta demonBladeMeta = demonBlade.getItemMeta();
        demonBladeMeta.setDisplayName("Demon Blade");
        demonBladeMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§4Basic Attribute of the Demon King's Army");
        demonBladeMeta.setLore(lore);
        demonBladeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
        demonBlade.setItemMeta(demonBladeMeta);
        return demonBlade;
    }

    public static ItemStack getLegendarySword() {
        ItemStack legendarySword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta legendarySwordMeta = legendarySword.getItemMeta();
        legendarySwordMeta.setDisplayName("Legendary Sword");
        legendarySwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§eLegends say that this sword has gone through many wars.");
        lore.add("§eAnd he won each one.");
        legendarySwordMeta.setLore(lore);
        legendarySwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        legendarySwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        legendarySword.setItemMeta(legendarySwordMeta);
        return legendarySword;
    }

    public static ItemStack getEpicSword() {
        ItemStack epicSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta epicSwordMeta = epicSword.getItemMeta();
        epicSwordMeta.setDisplayName("Epic Sword");
        epicSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§7Skilled blacksmiths put their hand to this sword.");
        epicSwordMeta.setLore(lore);
        epicSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        epicSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
        epicSword.setItemMeta(epicSwordMeta);
        return epicSword;
    }
    public static ItemStack getMoltenSword() {
        ItemStack moltenSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta moltenSwordMeta = moltenSword.getItemMeta();
        moltenSwordMeta.setDisplayName("Molten Sword");
        moltenSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§cFresh from §6the §eoven fire.");
        moltenSwordMeta.setLore(lore);
        moltenSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        moltenSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        moltenSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        moltenSword.setItemMeta(moltenSwordMeta);
        return moltenSword;
    }
    public static ItemStack getHeroSword() {
        ItemStack heroSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta heroSwordMeta = heroSword.getItemMeta();
        heroSwordMeta.setDisplayName("Hero Sword");
        heroSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("The Sword of Heroes was created in the midst of a great war,");
        lore.add("where its owner was the last bastion in the defense of his people.");
        heroSwordMeta.setLore(lore);
        heroSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        heroSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        heroSword.setItemMeta(heroSwordMeta);
        return heroSword;
    }



}
