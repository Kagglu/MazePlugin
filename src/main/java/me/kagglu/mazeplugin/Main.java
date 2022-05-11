package me.kagglu.mazeplugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveConfig();
        this.getCommand("maze").setExecutor(new CommandMaze(this));
    }

    @Override
    public void onDisable() {

    }
}
