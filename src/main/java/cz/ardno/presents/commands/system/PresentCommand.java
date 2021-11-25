package cz.ardno.presents.commands.system;

import org.bukkit.entity.Player;

public interface PresentCommand {

    void execute(Player player, String[] arguments);
}
