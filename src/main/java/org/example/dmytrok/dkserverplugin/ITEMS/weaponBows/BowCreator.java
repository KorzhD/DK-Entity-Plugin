package org.example.dmytrok.dkserverplugin.ITEMS.weaponBows;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BowCreator {

    public static ItemStack getBloodthirsty() {
        ItemStack  bloodthirsty = new ItemStack(Material.WOOD_SWORD);
        ItemMeta  bloodthirstyMeta =  bloodthirsty.getItemMeta();
         bloodthirstyMeta.setDisplayName("Bloodthirsty");
         bloodthirstyMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
         bloodthirstyMeta.setLore(lore);
         bloodthirstyMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
         bloodthirstyMeta.addEnchant(Enchantment.LUCK, 1, true);
         bloodthirsty.setItemMeta( bloodthirstyMeta);
        return  bloodthirsty;
    }
    public static ItemStack getLordOfLightning() {
        ItemStack lol = new ItemStack(Material.WOOD_SWORD);
        ItemMeta lolMeta = lol.getItemMeta();
        lolMeta.setDisplayName("Lord of Lightning");
        lolMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        lolMeta.setLore(lore);
        lolMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        lolMeta.addEnchant(Enchantment.LUCK, 1, true);
        lol.setItemMeta(lolMeta);
        return lol;
    }

    public static ItemStack getThePiercerOfSins() {
        ItemStack tpos = new ItemStack(Material.WOOD_SWORD);
        ItemMeta tposMeta = tpos.getItemMeta();
        tposMeta.setDisplayName("The Piercer of Sins");
        tposMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        tposMeta.setLore(lore);
        tposMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        tposMeta.addEnchant(Enchantment.LUCK, 1, true);
        tpos.setItemMeta(tposMeta);
        return tpos;
    }

    public static ItemStack getLurkingShadow() {
        ItemStack lurkingShadow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta lurkingShadowMeta = lurkingShadow.getItemMeta();
        lurkingShadowMeta.setDisplayName("Lurking Shadow");
        lurkingShadowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        lurkingShadowMeta.setLore(lore);
        lurkingShadowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        lurkingShadowMeta.addEnchant(Enchantment.LUCK, 1, true);
        lurkingShadow.setItemMeta(lurkingShadowMeta);
        return lurkingShadow;
    }

    public static ItemStack getHollowBow() {
        ItemStack hollowBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta hollowBowMeta = hollowBow.getItemMeta();
        hollowBowMeta.setDisplayName("Hollow Bow");
        hollowBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        hollowBowMeta.setLore(lore);
        hollowBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        hollowBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        hollowBow.setItemMeta(hollowBowMeta);
        return hollowBow;
    }

    public static ItemStack getFrozenFang() {
        ItemStack frozenFang = new ItemStack(Material.WOOD_SWORD);
        ItemMeta frozenFangMeta = frozenFang.getItemMeta();
        frozenFangMeta.setDisplayName("Frozen Fang");
        frozenFangMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        frozenFangMeta.setLore(lore);
        frozenFangMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        frozenFangMeta.addEnchant(Enchantment.LUCK, 1, true);
        frozenFang.setItemMeta(frozenFangMeta);
        return frozenFang;
    }

    public static ItemStack getIcedBow() {
        ItemStack icedBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta icedBowMeta = icedBow.getItemMeta();
        icedBowMeta.setDisplayName("Iced Bow");
        icedBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        icedBowMeta.setLore(lore);
        icedBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        icedBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        icedBow.setItemMeta(icedBowMeta);
        return icedBow;
    }

    public static ItemStack getDemonSoul() {
        ItemStack demonSoul = new ItemStack(Material.WOOD_SWORD);
        ItemMeta demonSoulMeta = demonSoul.getItemMeta();
        demonSoulMeta.setDisplayName("Demon Soul");
        demonSoulMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        demonSoulMeta.setLore(lore);
        demonSoulMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonSoulMeta.addEnchant(Enchantment.LUCK, 1, true);
        demonSoul.setItemMeta(demonSoulMeta);
        return demonSoul;
    }

    public static ItemStack getDemonBow() {
        ItemStack demonBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta demonBowMeta = demonBow.getItemMeta();
        demonBowMeta.setDisplayName("Demon Bow");
        demonBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        demonBowMeta.setLore(lore);
        demonBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        demonBow.setItemMeta(demonBowMeta);
        return demonBow;
    }

    public static ItemStack getTheVoidEnd() {
        ItemStack theVoidEnd = new ItemStack(Material.WOOD_SWORD);
        ItemMeta theVoidEndMeta = theVoidEnd.getItemMeta();
        theVoidEndMeta.setDisplayName("The Void End");
        theVoidEndMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        theVoidEndMeta.setLore(lore);
        theVoidEndMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        theVoidEndMeta.addEnchant(Enchantment.LUCK, 1, true);
        theVoidEnd.setItemMeta(theVoidEndMeta);
        return theVoidEnd;
    }

    public static ItemStack getBerserk() {
        ItemStack berserk = new ItemStack(Material.WOOD_SWORD);
        ItemMeta berserkMeta = berserk.getItemMeta();
        berserkMeta.setDisplayName("Berserk");
        berserkMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        berserkMeta.setLore(lore);
        berserkMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        berserkMeta.addEnchant(Enchantment.LUCK, 1, true);
        berserk.setItemMeta(berserkMeta);
        return berserk;
    }

    public static ItemStack getSoulSlayer() {
        ItemStack soulSlayer = new ItemStack(Material.WOOD_SWORD);
        ItemMeta soulSlayerMeta = soulSlayer.getItemMeta();
        soulSlayerMeta.setDisplayName("Soul Slayer");
        soulSlayerMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        soulSlayerMeta.setLore(lore);
        soulSlayerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        soulSlayerMeta.addEnchant(Enchantment.LUCK, 1, true);
        soulSlayer.setItemMeta(soulSlayerMeta);
        return soulSlayer;
    }

    public static ItemStack getMythicBow() {
        ItemStack mythicBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta mythicBowMeta = mythicBow.getItemMeta();
        mythicBowMeta.setDisplayName("Mythic Bow");
        mythicBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        mythicBowMeta.setLore(lore);
        mythicBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        mythicBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        mythicBow.setItemMeta(mythicBowMeta);
        return mythicBow;
    }

    public static ItemStack getLegendaryBow() {
        ItemStack legendaryBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta legendaryBowMeta = legendaryBow.getItemMeta();
        legendaryBowMeta.setDisplayName("Legendary Bow");
        legendaryBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        legendaryBowMeta.setLore(lore);
        legendaryBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        legendaryBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        legendaryBow.setItemMeta(legendaryBowMeta);
        return legendaryBow;
    }

    public static ItemStack getHeroBow() {
        ItemStack heroBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta heroBowMeta = heroBow.getItemMeta();
        heroBowMeta.setDisplayName("Hero Bow");
        heroBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        heroBowMeta.setLore(lore);
        heroBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        heroBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        heroBow.setItemMeta(heroBowMeta);
        return heroBow;
    }

    public static ItemStack getFireBow() {
        ItemStack fireBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta fireBowMeta = fireBow.getItemMeta();
        fireBowMeta.setDisplayName("Fire Bow");
        fireBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        fireBowMeta.setLore(lore);
        fireBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        fireBowMeta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
        fireBow.setItemMeta(fireBowMeta);
        return fireBow;
    }

    public static ItemStack getEpicBow() {
        ItemStack epicBow = new ItemStack(Material.WOOD_SWORD);
        ItemMeta epicBowMeta = epicBow.getItemMeta();
        epicBowMeta.setDisplayName("Epic Bow");
        epicBowMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("///");
        lore.add("");
        lore.add("§d§lRarity:");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("");
        lore.add("");
        epicBowMeta.setLore(lore);
        epicBowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        epicBowMeta.addEnchant(Enchantment.LUCK, 1, true);
        epicBow.setItemMeta(epicBowMeta);
        return epicBow;
    }

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
