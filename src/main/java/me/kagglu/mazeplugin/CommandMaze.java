package me.kagglu.mazeplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class CommandMaze implements CommandExecutor {
    private final Main instance;
    public SimpleDateFormat format = new SimpleDateFormat("dd");

    public CommandMaze(Main instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = Bukkit.getPlayer(sender.getName());
        if (p != null) {
            p.sendMessage("Success!");
        }
        return true;
    }
}