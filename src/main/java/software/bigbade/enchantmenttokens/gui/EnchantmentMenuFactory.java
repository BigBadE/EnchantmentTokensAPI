/*
 * Copyright (c) 2020 BigBadE, All rights reserved
 */

package software.bigbade.enchantmenttokens.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import software.bigbade.enchantmenttokens.api.EnchantmentBase;
import software.bigbade.enchantmenttokens.api.EnchantmentPlayer;

import java.util.List;

public interface EnchantmentMenuFactory {
    void addButtons(List<EnchantButton> button);

    /**
     * Generate the GUI with every enchantment in it
     *
     * @param itemStack Item target
     * @param player    Target player
     * @return Generated enchantment inventory
     */
    EnchantmentGUI generateGUI(ItemStack itemStack, EnchantmentPlayer player);

    EnchantmentGUI genInventory(Player player);

    EnchantmentGUI genItemInventory(EnchantmentPlayer enchantPlayer, ItemStack item, List<EnchantmentBase> added);
}
