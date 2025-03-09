package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesCreator {

    public static ItemStack getIceCoreRing() {
        ItemStack iceCoreRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta iceCoreMeta = iceCoreRing.getItemMeta();
        iceCoreMeta.setDisplayName("Ice Core Ring");
        List<String> lore = new ArrayList<>();
        lore.add("§3A shard of ancient ice, imbued with the power of eternal winter.");
        lore.add("§3Freezes enemies and grants fire resistance.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        iceCoreMeta.setLore(lore);
        iceCoreMeta.addEnchant(Enchantment.LUCK, 1, true);
        iceCoreMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        iceCoreRing.setItemMeta(iceCoreMeta);
        return iceCoreRing;
    }

    public static ItemStack getFrozenEyeRing() {
        ItemStack frozenEyeRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta frozenEyeMeta = frozenEyeRing.getItemMeta();
        frozenEyeMeta.setDisplayName("Frozen Eye Ring");
        List<String> lore = new ArrayList<>();
        lore.add("§bAn all-seeing eye trapped in an icy crystal.");
        lore.add("§bIts magic distorts space, making the wearer immune to arrows.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        frozenEyeMeta.setLore(lore);
        frozenEyeMeta.addEnchant(Enchantment.LUCK, 1, true);
        frozenEyeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        frozenEyeRing.setItemMeta(frozenEyeMeta);
        return frozenEyeRing;
    }

    public static ItemStack getRingOfTheFaceless() {
        ItemStack ringOfTheFaceless = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta ringOfTheFacelessMeta = ringOfTheFaceless.getItemMeta();
        ringOfTheFacelessMeta.setDisplayName("Ring of The Faceless");
        List<String> lore = new ArrayList<>();
        lore.add("§8A ring that erases the line between life and shadow.");
        lore.add("§8The lower your health, the stronger your defense.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        ringOfTheFacelessMeta.setLore(lore);
        ringOfTheFacelessMeta.addEnchant(Enchantment.LUCK, 1, true);
        ringOfTheFacelessMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        ringOfTheFaceless.setItemMeta(ringOfTheFacelessMeta);
        return ringOfTheFaceless;
    }

    public static ItemStack getGloomOfTheSoul() {
        ItemStack gloomOfTheSoul = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta gloomOfTheSoulMeta = gloomOfTheSoul.getItemMeta();
        gloomOfTheSoulMeta.setDisplayName("Gloom of The Soul");
        List<String> lore = new ArrayList<>();
        lore.add("§7Dark energy within this ring fuels unrelenting fury.");
        lore.add("§7The lower your health, the stronger your attacks.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        gloomOfTheSoulMeta.setLore(lore);
        gloomOfTheSoulMeta.addEnchant(Enchantment.LUCK, 1, true);
        gloomOfTheSoulMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        gloomOfTheSoul.setItemMeta(gloomOfTheSoulMeta);
        return gloomOfTheSoul;
    }
    public static ItemStack getFlameJudgeRing() {
        ItemStack flameJudgeRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta flameJudgeRingMeta = flameJudgeRing.getItemMeta();
        flameJudgeRingMeta.setDisplayName("Flame Judge Ring");
        List<String> lore = new ArrayList<>();
        lore.add("§4The fiery judge punishes all who approach.");
        lore.add("§4Its flames ignite everything around.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        flameJudgeRingMeta.setLore(lore);
        flameJudgeRingMeta.addEnchant(Enchantment.LUCK, 1, true);
        flameJudgeRingMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        flameJudgeRing.setItemMeta(flameJudgeRingMeta);
        return flameJudgeRing;
    }

    public static ItemStack getDragonsSparkRing() {
        ItemStack dragonsSparkRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta dragonsSparkRingMeta = dragonsSparkRing.getItemMeta();
        dragonsSparkRingMeta.setDisplayName("Dragon's Spark Ring");
        List<String> lore = new ArrayList<>();
        lore.add("§cA fragment of an ancient dragon's fury.");
        lore.add("§cEvery attack is imbued with fire.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        dragonsSparkRingMeta.setLore(lore);
        dragonsSparkRingMeta.addEnchant(Enchantment.LUCK, 1, true);
        dragonsSparkRingMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        dragonsSparkRing.setItemMeta(dragonsSparkRingMeta);
        return dragonsSparkRing;
    }

    public static ItemStack getEyeOfTheVoid() {
        ItemStack eyeOfTheVoid = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta eyeOfTheVoidMeta = eyeOfTheVoid.getItemMeta();
        eyeOfTheVoidMeta.setDisplayName("Eye of The Void");
        List<String> lore = new ArrayList<>();
        lore.add("§5Gazing into the abyss, one sees infinite possibilities...");
        lore.add("§510% chance to escape death.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        eyeOfTheVoidMeta.setLore(lore);
        eyeOfTheVoidMeta.addEnchant(Enchantment.LUCK, 1, true);
        eyeOfTheVoidMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        eyeOfTheVoid.setItemMeta(eyeOfTheVoidMeta);
        return eyeOfTheVoid;
    }

    public static ItemStack getRingOfSpatialRift() {
        ItemStack eyeOfTheVoid = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta eyeOfTheVoidMeta = eyeOfTheVoid.getItemMeta();
        eyeOfTheVoidMeta.setDisplayName("Ring of Spatial Rift");
        List<String> lore = new ArrayList<>();
        lore.add("§dBends space itself, allowing the wearer to leap to incredible heights.");
        lore.add("§dEach jump leaves behind faint traces of distorted reality.");
        lore.add("");
        lore.add("§d§lRarity: ");
        lore.add("§a§lLvl: ");
        lore.add("");
        lore.add("");
        eyeOfTheVoidMeta.setLore(lore);
        eyeOfTheVoidMeta.addEnchant(Enchantment.LUCK, 1, true);
        eyeOfTheVoidMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        eyeOfTheVoid.setItemMeta(eyeOfTheVoidMeta);
        return eyeOfTheVoid;
    }
}