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
     * @param staffToolType String, the type of staff tool being given
     * @param player        Player, the player to give the item to
     */
    public static void create(String staffToolType, Player player) {
        String[] materialParts = FileManager.get("config").getString("tools." + staffToolType + ".material").split(":");
        ItemStack item = new ItemStack(Material.getMaterial(materialParts[0].toUpperCase()), 1, Byte.parseByte(materialParts[1]));
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ColorUtil.colorize(FileManager.get("config").getString("tools." + staffToolType + ".name")));
        List<String> itemLore = new ArrayList<>();
        for (String line : FileManager.get("config").getStringList("tools." + staffToolType + ".lore")) {
            itemLore.add(ColorUtil.colorize(line));
        }
        itemMeta.setLore(itemLore);
        for (String enchantment : FileManager.get("config").getStringList("tools." + staffToolType + ".enchantments")) {
            String[] enchantmentParts = enchantment.split(":");
            itemMeta.addEnchant(Enchantment.getByName(enchantmentParts[0].toUpperCase()),
                    Integer.parseInt(enchantmentParts[1]), true);
        }
        for (String flag : FileManager.get("config").getStringList("tools." + staffToolType + ".item-flags")) {
            itemMeta.addItemFlags(ItemFlag.valueOf(flag.toUpperCase()));
        }
        item.setItemMeta(itemMeta);
        //If the player is not null give them a gen bucket
        if (player != null) {
            //Set all of the NBT data for the item
            NBTItem nbtItem = new NBTItem(item);
            nbtItem.setBoolean("staff+.tool." + staffToolType, true);
            //Add the item to their inventory
            player.getInventory().setItem(FileManager.get("config").getInt("tools." + staffToolType + ".slot"), nbtItem.getItem());
        }
    }
}
