package software.bigbade.enchantmenttokens.api.wrappers;

import co.aikar.taskchain.TaskChainTasks;

public class EnchantmentTasks {
    @FunctionalInterface
    public interface EnchantmentTask extends TaskChainTasks.GenericTask {
        @Override
        void runGeneric();
    }

    @FunctionalInterface
    public interface FirstEnchantmentTask<T> extends TaskChainTasks.FirstTask<T> {
        @Override
        T run();
    }

    @FunctionalInterface
    public interface LastEnchantmentTask<T> extends TaskChainTasks.LastTask<T> {
        @Override
        void runLast(T t);
    }
}