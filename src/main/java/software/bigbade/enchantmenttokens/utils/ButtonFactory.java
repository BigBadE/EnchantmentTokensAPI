package software.bigbade.enchantmenttokens.utils;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import software.bigbade.enchantmenttokens.api.EnchantmentPlayer;
import software.bigbade.enchantmenttokens.gui.EnchantButton;
import software.bigbade.enchantmenttokens.gui.EnchantmentGUI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Function;

public abstract class ButtonFactory {
    @Getter
    private static ButtonFactory instance = null;

    public static void setInstance(ButtonFactory instance) {
        if (ButtonFactory.instance != null) {
            throw new IllegalStateException("Tried setting already-set instance in ButtonFactory!");
        }
        ButtonFactory.instance = instance;
    }

    public abstract EnchantButton createButton(@Nonnull ItemStack item, @Nullable Function<EnchantmentPlayer, EnchantmentGUI> callable);

    public abstract EnchantButton createButton(@Nonnull ItemStack item, @Nullable Function<EnchantmentPlayer, EnchantmentGUI> callable, @Nullable String translationString);
}
