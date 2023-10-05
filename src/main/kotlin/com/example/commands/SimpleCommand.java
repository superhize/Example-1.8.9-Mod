package com.example.commands;

import com.example.errors.CommandError;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.List;

/**
 * @author Moulberry
 **/
public class SimpleCommand extends CommandBase {

    private final String commandName;
    private final ProcessCommandRunnable runnable;
    private TabCompleteRunnable tabRunnable;

    public SimpleCommand(String commandName, ProcessCommandRunnable runnable) {
        this.commandName = commandName;
        this.runnable = runnable;
    }

    public SimpleCommand(String commandName, ProcessCommandRunnable runnable, TabCompleteRunnable tabRunnable) {
        this.commandName = commandName;
        this.runnable = runnable;
        this.tabRunnable = tabRunnable;
    }

    public abstract static class ProcessCommandRunnable {

        public abstract void processCommand(ICommandSender sender, String[] args);
    }

    public interface TabCompleteRunnable {

        List<String> tabComplete(ICommandSender sender, String[] args, BlockPos pos);
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + commandName;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        try {
            runnable.processCommand(sender, args);
        } catch (Throwable e) {
            throw new CommandError("Error while executing command /" + commandName, e);
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (tabRunnable != null) return tabRunnable.tabComplete(sender, args, pos);
        return null;
    }
}
