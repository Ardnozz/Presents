package cz.ardno.presents.commands.system;

import cz.ardno.presents.utilities.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PresentPlayerCommand implements PresentCommand, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            this.execute((Player) sender, args);
            return true;
        }
        sender.sendMessage(MessageUtility.CMD_EXECUTOR_NOT_PLAYER.getMessage());
        return false;
    }
}
