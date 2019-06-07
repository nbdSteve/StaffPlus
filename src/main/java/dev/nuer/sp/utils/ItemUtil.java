package dev.nuer.sp.utils;

import dev.nuer.sp.managers.FileManager;
import dev.nuer.sp.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles creating and item
 */
public class ItemUtil {

    /**
     * Creates an item from the specified arguments
     *
     * @param material     String, the item material
     * @param name         String, the items name
     * @param lore         List<String>, the lore for the item
     * @param enchantments List<String>, list of enchantments for the item
     * @param flags        List<String>, list of item flags for the item
     * @param player       Player, the player to give the item to
     */
    public static void create(String typeOfBucket, String material, String bucketID, Player player) {
        String[] materialParts = material.split(":");
        ItemStack item = new ItemStack(Material.getMaterial(materialParts[0].toUpperCase()), 1, Byte.parseByte(materialParts[1]));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ColorUtil.colorize(FileManager.get(typeOfBucket).getString(bucketID + ".name")));
        List<String> itemLore = new ArrayList<>();
        for (String line : FileManager.get(typeOfBucket).getStringList(bucketID + ".lore")) {
            itemLore.add(ColorUtil.colorize(line));
        }
        itemMeta.setLore(itemLore);
        for (String enchantment : FileManager.get(typeOfBucket).getStringList(bucketID + ".enchantments")) {
            String[] enchantmentParts = enchantment.split(":");
            itemMeta.addEnchant(Enchantment.getByName(enchantmentParts[0].toUpperCase()),
                    Integer.parseInt(enchantmentParts[1]), true);
        }
        for (String flag : FileManager.get(typeOfBucket).getStringList(bucketID + ".item-flags")) {
            itemMeta.addItemFlags(ItemFlag.valueOf(flag.toUpperCase()));
        }
        item.setItemMeta(itemMeta);
        //If the player is not null give them a gen bucket
        if (player != null) {
            //Set all of the NBT data for the item
            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setBoolean("buckets+.gen-bucket", true);
            nbtItem.setString("buckets+.type", typeOfBucket);
            nbtItem.setString("buckets+.material", FileManager.get(typeOfBucket).getString(bucketID + ".material"));
            nbtItem.setInteger("buckets+.delay", FileManager.get(typeOfBucket).getInt(bucketID + ".delay"));
            nbtItem.setInteger("buckets+.generation-length", FileManager.get(typeOfBucket).getInt(bucketID + ".generation-length"));
            nbtItem.setDouble("buckets+.price", FileManager.get(typeOfBucket).getDouble(bucketID + ".price"));
            nbtItem.setBoolean("buckets+.pseudo", FileManager.get(typeOfBucket).getBoolean(bucketID + ".pseudo"));
            //Add the item to their inventory
            player.getInventory().addItem(nbtItem.getItem());
        }
    }
}
