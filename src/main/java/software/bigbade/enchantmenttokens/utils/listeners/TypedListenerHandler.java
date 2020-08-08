package software.bigbade.enchantmenttokens.utils.listeners;

import lombok.Getter;
import org.bukkit.event.Event;
import software.bigbade.enchantmenttokens.api.ListenerType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class TypedListenerHandler {
    private final Map<ListenerType, ListenerManager<?>> listenerManager = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Event> ListenerManager<T> getManager(ListenerType type) {
        return (ListenerManager<T>) listenerManager.get(type);
    }

    public void register(ListenerType type, ListenerManager<?> manager) {
        listenerManager.put(type, manager);
    }
}
