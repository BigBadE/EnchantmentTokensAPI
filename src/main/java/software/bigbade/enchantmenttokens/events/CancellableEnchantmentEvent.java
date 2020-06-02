package software.bigbade.enchantmenttokens.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public interface CancellableEnchantmentEvent<T extends Event & Cancellable> extends Cancellable, EnchantmentEvent<T> { }