/*
 * Copyright (c) 2020 BigBadE, All rights reserved
 */

package software.bigbade.enchantmenttokens.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class EnchantmentApplyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Getter
    private final ItemStack item;
    @Getter
    private final Player player;

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
