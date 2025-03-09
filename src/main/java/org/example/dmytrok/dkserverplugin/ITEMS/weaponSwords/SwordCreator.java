package org.example.dmytrok.dkserverplugin.ITEMS.weaponSwords;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SwordCreator {

    public static ItemStack getHeavenWarriorsBlade() {
        ItemStack heavenWarriorsBlade = new ItemStack(Material.WOOD_SWORD);
        ItemMeta heavenWarriorsBladeMeta = heavenWarriorsBlade.getItemMeta();
        heavenWarriorsBladeMeta.setDisplayName("Heaven Warrior's Blade");
        heavenWarriorsBladeMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("//");
        lore.add("");
        heavenWarriorsBladeMeta.setLore(lore);
        heavenWarriorsBladeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        heavenWarriorsBladeMeta.addEnchant(Enchantment.DAMAGE_ALL,41, true);
        heavenWarriorsBlade.setItemMeta(heavenWarriorsBladeMeta);
        return heavenWarriorsBlade;
    }

    public static ItemStack getThunderer() {
        ItemStack thunderer = new ItemStack(Material.WOOD_SWORD);
        ItemMeta thundererMeta = thunderer.getItemMeta();
        thundererMeta.setDisplayName("Thunderer");
        thundererMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("//");
        lore.add("");
        thundererMeta.setLore(lore);
        thundererMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        thundererMeta.addEnchant(Enchantment.DAMAGE_ALL,41, true);
        thunderer.setItemMeta(thundererMeta);
        return thunderer;
    }

    public static ItemStack getBloodySeeker() {
        ItemStack bloodySeeker = new ItemStack(Material.WOOD_SWORD);
        ItemMeta bloodySeekerMeta = bloodySeeker.getItemMeta();
        bloodySeekerMeta.setDisplayName("Bloody Seeker");
        bloodySeekerMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: §e");
        lore.add("§4§lDamage: §7");
        lore.add("");
        lore.add("//");
        lore.add("");
        bloodySeekerMeta.setLore(lore);
        bloodySeekerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        bloodySeekerMeta.addEnchant(Enchantment.DAMAGE_ALL,41, true);
        bloodySeeker.setItemMeta(bloodySeekerMeta);
        return bloodySeeker;
    }

    public static ItemStack getShadowScythe() {
        ItemStack shadowScythe = new ItemStack(Material.WOOD_SWORD);
        ItemMeta shadowScytheMeta = shadowScythe.getItemMeta();
        shadowScytheMeta.setDisplayName("Shadow Scythe");
        shadowScytheMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§8A weapon that can subvert gravity itself.");
        lore.add("");
        lore.add("§d§lRarity: §eLegendary");
        lore.add("§a§lLvl: §e26");
        lore.add("§4§lDamage: §727");
        lore.add("");
        lore.add("§8§lBlack Hole: §7RMB");
        lore.add("");
        shadowScytheMeta.setLore(lore);
        shadowScytheMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        shadowScytheMeta.addEnchant(Enchantment.DAMAGE_ALL,41, true);
        shadowScythe.setItemMeta(shadowScytheMeta);
        return shadowScythe;
    }

    public static ItemStack getSpaceDivider() {
        ItemStack spaceDivider = new ItemStack(Material.WOOD_SWORD);
        ItemMeta spaceDividerMeta = spaceDivider.getItemMeta();
        spaceDividerMeta.setDisplayName("Space Divider");
        spaceDividerMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§dSlices through space, opening up your ability to use items from another reality");
        lore.add("");
        lore.add("§d§lRarity: §eLegendary");
        lore.add("§a§lLvl: §e26");
        lore.add("§4§lDamage: §727");
        lore.add("");
        lore.add("§d§lGate to the Armory: §7RMB");
        lore.add("");
        spaceDividerMeta.setLore(lore);
        spaceDividerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        spaceDividerMeta.addEnchant(Enchantment.DAMAGE_ALL,41, true);
        spaceDivider.setItemMeta(spaceDividerMeta);
        return spaceDivider;
    }


    public static ItemStack getFrostScythe() {
        ItemStack frostScythe = new ItemStack(Material.WOOD_SWORD);
        ItemMeta frostScytheMeta = frostScythe.getItemMeta();
        frostScytheMeta.setDisplayName("Frost Scythe");
        frostScytheMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§l§bThe blade is forged from the Guardian Core.");
        lore.add("§l§bThe handle is made from his flesh.");
        lore.add("§l§bCapable of cutting the highest mountain peaks.");
        lore.add("");
        lore.add("§d§lRarity: §eLegendary");
        lore.add("§a§lLvl: §e45");
        lore.add("§4§lDamage: §732");
        lore.add("");
        lore.add("§b§lIce Snowflake: §7Shift + RMB");
        lore.add("");
        frostScytheMeta.setLore(lore);
        frostScytheMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        frostScytheMeta.addEnchant(Enchantment.DAMAGE_ALL, 54, true);
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
        lore.add("");
        lore.add("§d§lRarity: §5Epic");
        lore.add("§a§lLvl: §e40");
        lore.add("§4§lDamage: §727");
        lore.add("");
        lore.add("§3§lShield of the Night: §7RMB");
        lore.add("");
        moonLightMeta.setLore(lore);
        moonLightMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        moonLightMeta.addEnchant(Enchantment.DAMAGE_ALL,44, true);
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
        lore.add("");
        lore.add("§d§lRarity: §eLegendary");
        lore.add("§a§lLvl: §e29");
        lore.add("§4§lDamage: §729");
        lore.add("");
        lore.add("§4§lPentagram of Fire: §7LMB + RMB + LMB");
        lore.add("");
        demonSlayerMeta.setLore(lore);
        demonSlayerMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonSlayerMeta.addEnchant(Enchantment.DAMAGE_ALL, 48, true);
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
        lore.add("");
        lore.add("§d§lRarity: §5Epic");
        lore.add("§a§lLvl: §e27");
        lore.add("§4§lDamage: §726");
        lore.add("");
        lore.add("§8§lSuffocation: §7RMB + LMB + RMB");
        lore.add("");
        demonBladeMeta.setLore(lore);
        demonBladeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        demonBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 42, true);
        demonBlade.setItemMeta(demonBladeMeta);
        return demonBlade;
    }

    public static ItemStack getEnderKatana() {
        ItemStack enderKatana = new ItemStack(Material.WOOD_SWORD);
        ItemMeta enderKatanaMeta = enderKatana.getItemMeta();
        enderKatanaMeta.setDisplayName("Ender Katana");
        enderKatanaMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§5Elusive speed, invisible to the eye.");
        lore.add("§5Comparable to teleportation...");
        lore.add("");
        lore.add("§d§lRarity: §eLegendary");
        lore.add("§a§lLvl: §e25");
        lore.add("§4§lDamage: §725");
        lore.add("");
        lore.add("§5§lEnder Speed: §7RMB + LMB + RMB");
        lore.add("");
        enderKatanaMeta.setLore(lore);
        enderKatanaMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        enderKatanaMeta.addEnchant(Enchantment.DAMAGE_ALL, 40, true);
        enderKatana.setItemMeta(enderKatanaMeta);
        return enderKatana;
    }


    public static ItemStack getBloodyDeath() {
        ItemStack bloodyDeath = new ItemStack(Material.WOOD_SWORD);
        ItemMeta bloodyDeathMeta = bloodyDeath.getItemMeta();
        bloodyDeathMeta.setDisplayName("Bloody Death");
        bloodyDeathMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§4Fill it with the blood of your enemies.");
        lore.add("");
        lore.add("§d§lRarity: §5Epic");
        lore.add("§a§lLvl: §e20");
        lore.add("§4§lDamage: §722");
        lore.add("");
        lore.add("§4§lBerserk Mode: §7Shift + RMB");
        lore.add("");
        bloodyDeathMeta.setLore(lore);
        bloodyDeathMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        bloodyDeathMeta.addEnchant(Enchantment.DAMAGE_ALL, 34, true);
        bloodyDeath.setItemMeta(bloodyDeathMeta);
        return bloodyDeath;
    }

    public static ItemStack getLegendaryKatana() {
        ItemStack legendaryKatana = new ItemStack(Material.WOOD_SWORD);
        ItemMeta legendaryKatanaMeta = legendaryKatana.getItemMeta();
        legendaryKatanaMeta.setDisplayName("Legendary Katana");
        legendaryKatanaMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§fThis weapon is capable of cutting even a soul.");
        lore.add("");
        lore.add("§d§lRarity: §5Epic");
        lore.add("§a§lLvl: §e17");
        lore.add("§4§lDamage: §720");
        lore.add("");
        lore.add("§f§lSoul Butcher: §7RMB");
        lore.add("");
        legendaryKatanaMeta.setLore(lore);
        legendaryKatanaMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        legendaryKatanaMeta.addEnchant(Enchantment.DAMAGE_ALL, 30, true);
        legendaryKatana.setItemMeta(legendaryKatanaMeta);
        return legendaryKatana;
    }

    public static ItemStack getMythicBlade() {
        ItemStack mythicBlade = new ItemStack(Material.WOOD_SWORD);
        ItemMeta mythicBladeMeta = mythicBlade.getItemMeta();
        mythicBladeMeta.setDisplayName("Mythic Blade");
        mythicBladeMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§5Legend has it that this blade was forged by the gods themselves,");
        lore.add("§5imbued with the very essence of the cosmos.");
        lore.add("");
        lore.add("§d§lRarity: §5Epic");
        lore.add("§a§lLvl: §e15");
        lore.add("§4§lDamage: §717");
        lore.add("");
        lore.add("§f§lTornado: §7RMB");
        lore.add("");
        mythicBladeMeta.setLore(lore);
        mythicBladeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        mythicBladeMeta.addEnchant(Enchantment.DAMAGE_ALL, 24, true);
        mythicBlade.setItemMeta(mythicBladeMeta);
        return mythicBlade;
    }


    public static ItemStack getLegendarySword() {
        ItemStack legendarySword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta legendarySwordMeta = legendarySword.getItemMeta();
        legendarySwordMeta.setDisplayName("Legendary Sword");
        legendarySwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§eLegends say that this sword has gone through many wars.");
        lore.add("§eAnd he won each one.");
        lore.add("");
        lore.add("§d§lRarity: §2Uncommon");
        lore.add("§a§lLvl: §e13");
        lore.add("§4§lDamage: §715");
        lore.add("");
        lore.add("§c§lTeam Regeneration: §7RMB");
        lore.add("");
        legendarySwordMeta.setLore(lore);
        legendarySwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        legendarySwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 20, true);
        legendarySword.setItemMeta(legendarySwordMeta);
        return legendarySword;
    }

    public static ItemStack getHeroSword() {
        ItemStack heroSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta heroSwordMeta = heroSword.getItemMeta();
        heroSwordMeta.setDisplayName("Hero Sword");
        heroSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("The Sword of Heroes was created in the midst of a great war,");
        lore.add("where its owner was the last bastion in the defense of his people.");
        lore.add("");
        lore.add("§d§lRarity: §2Uncommon");
        lore.add("§a§lLvl: §e10");
        lore.add("§4§lDamage: §712");
        lore.add("");
        lore.add("");
        lore.add("");
        heroSwordMeta.setLore(lore);
        heroSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        heroSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 14, true);
        heroSword.setItemMeta(heroSwordMeta);
        return heroSword;
    }

    public static ItemStack getMoltenSword() {
        ItemStack moltenSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta moltenSwordMeta = moltenSword.getItemMeta();
        moltenSwordMeta.setDisplayName("Molten Sword");
        moltenSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§cFresh from §6the §eoven fire.");
        lore.add("");
        lore.add("§d§lRarity: §8Common");
        lore.add("§a§lLvl: §e6");
        lore.add("§4§lDamage: §79");
        lore.add("");
        lore.add("");
        lore.add("");
        moltenSwordMeta.setLore(lore);
        moltenSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        moltenSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 8, true);
        moltenSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        moltenSword.setItemMeta(moltenSwordMeta);
        return moltenSword;
    }

    public static ItemStack getEpicSword() {
        ItemStack epicSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta epicSwordMeta = epicSword.getItemMeta();
        epicSwordMeta.setDisplayName("Epic Sword");
        epicSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§7Skilled blacksmiths put their hand to this sword.");
        lore.add("");
        lore.add("§d§lRarity: §8Common");
        lore.add("§a§lLvl: §e5");
        lore.add("§4§lDamage: §77");
        lore.add("");
        lore.add("");
        lore.add("");
        epicSwordMeta.setLore(lore);
        epicSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        epicSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        epicSword.setItemMeta(epicSwordMeta);
        return epicSword;
    }

    public static ItemStack getSteelSword() {
        ItemStack steelSword = new ItemStack(Material.WOOD_SWORD);
        ItemMeta steelSwordMeta = steelSword.getItemMeta();
        steelSwordMeta.setDisplayName("Steel Sword");
        steelSwordMeta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add("§7Newbie sword");
        lore.add("");
        lore.add("§d§lRarity: §8Common");
        lore.add("§a§lLvl: §e2");
        lore.add("§4§lDamage: §74");
        lore.add("");
        lore.add("");
        lore.add("");
        steelSwordMeta.setLore(lore);
        steelSwordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        steelSword.setItemMeta(steelSwordMeta);
        return steelSword;
    }



}
