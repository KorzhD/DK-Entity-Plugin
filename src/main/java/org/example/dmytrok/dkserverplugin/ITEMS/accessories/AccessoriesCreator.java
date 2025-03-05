package org.example.dmytrok.dkserverplugin.ITEMS.accessories;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AccessoriesCreator {

    public ItemStack getIceCoreRing() {
        ItemStack iceCoreRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta iceCoreMeta = iceCoreRing.getItemMeta();
        iceCoreMeta.setDisplayName("Ice Core Ring");
        List<String> lore = new ArrayList<>();
        lore.add("");
        iceCoreMeta.setLore(lore);
        iceCoreMeta.addEnchant(Enchantment.LUCK, 1, true);
        iceCoreMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        iceCoreRing.setItemMeta(iceCoreMeta);

        return iceCoreRing;
    }
    public ItemStack getFrozenEyeRing() {
        ItemStack frozenEyeRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta frozenEyeMeta = frozenEyeRing.getItemMeta();
        frozenEyeMeta.setDisplayName("Frozen Eye Ring");
        List<String> lore = new ArrayList<>();
        lore.add("");
        frozenEyeMeta.setLore(lore);
        frozenEyeMeta.addEnchant(Enchantment.LUCK, 1, true);
        frozenEyeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        frozenEyeRing.setItemMeta(frozenEyeMeta);

        return frozenEyeRing;
    }
    public ItemStack getRingOfTheFaceless() {
        ItemStack ringOfTheFaceless = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta ringOfTheFacelessMeta = ringOfTheFaceless.getItemMeta();
        ringOfTheFacelessMeta.setDisplayName("Ring of The Faceless");
        List<String> lore = new ArrayList<>();
        lore.add("");
        ringOfTheFacelessMeta.setLore(lore);
        ringOfTheFacelessMeta.addEnchant(Enchantment.LUCK, 1, true);
        ringOfTheFacelessMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        ringOfTheFaceless.setItemMeta(ringOfTheFacelessMeta);

        return ringOfTheFaceless;
    }
    public ItemStack getGloomOfTheSoul() {
        ItemStack gloomOfTheSoul = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta gloomOfTheSoulMeta = gloomOfTheSoul.getItemMeta();
        gloomOfTheSoulMeta.setDisplayName("Gloom of The Soul");
        List<String> lore = new ArrayList<>();
        lore.add("");
        gloomOfTheSoulMeta.setLore(lore);
        gloomOfTheSoulMeta.addEnchant(Enchantment.LUCK, 1, true);
        gloomOfTheSoulMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        gloomOfTheSoul.setItemMeta(gloomOfTheSoulMeta);

        return gloomOfTheSoul;
    }
    public ItemStack getFlameJudgeRing() {
        ItemStack flameJudgeRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta flameJudgeRingMeta = flameJudgeRing.getItemMeta();
        flameJudgeRingMeta.setDisplayName("Flame Judge Ring");
        List<String> lore = new ArrayList<>();
        lore.add("");
        flameJudgeRingMeta.setLore(lore);
        flameJudgeRingMeta.addEnchant(Enchantment.LUCK, 1, true);
        flameJudgeRingMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        flameJudgeRing.setItemMeta(flameJudgeRingMeta);

        return flameJudgeRing;
    }

    public ItemStack getDragonsSparkRing() {
        ItemStack dragonsSparkRing = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta dragonsSparkRingMeta = dragonsSparkRing.getItemMeta();
        dragonsSparkRingMeta.setDisplayName("Dragon's Spark Ring");
        List<String> lore = new ArrayList<>();
        lore.add("");
        dragonsSparkRingMeta.setLore(lore);
        dragonsSparkRingMeta.addEnchant(Enchantment.LUCK, 1, true);
        dragonsSparkRingMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        dragonsSparkRing.setItemMeta(dragonsSparkRingMeta);

        return dragonsSparkRing;
    }

    public ItemStack getEyeOfTheVoid() {
        ItemStack eyeOfTheVoid = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta eyeOfTheVoidMeta = eyeOfTheVoid.getItemMeta();
        eyeOfTheVoidMeta.setDisplayName("Eye Of The Void");
        List<String> lore = new ArrayList<>();
        lore.add("");
        eyeOfTheVoidMeta.setLore(lore);
        eyeOfTheVoidMeta.addEnchant(Enchantment.LUCK, 1, true);
        eyeOfTheVoidMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        eyeOfTheVoid.setItemMeta(eyeOfTheVoidMeta);

        return eyeOfTheVoid;
    }

    public ItemStack getRingOfSpatialRift() {
        ItemStack ringOfSpatialRift = new ItemStack(Material.CHORUS_FRUIT_POPPED);
        ItemMeta ringOfSpatialRiftMeta = ringOfSpatialRift.getItemMeta();
        ringOfSpatialRiftMeta.setDisplayName("Ring of Spatial Rift");
        List<String> lore = new ArrayList<>();
        lore.add("");
        ringOfSpatialRiftMeta.setLore(lore);
        ringOfSpatialRiftMeta.addEnchant(Enchantment.LUCK, 1, true);
        ringOfSpatialRiftMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);
        ringOfSpatialRift.setItemMeta(ringOfSpatialRiftMeta);

        return ringOfSpatialRift;
    }

}
