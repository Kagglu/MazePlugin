package me.kagglu.mazeplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class CommandMaze implements CommandExecutor {
    private final Main instance;

    public CommandMaze(Main instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = Bukkit.getPlayer(sender.getName());
        if (p == null) {
            return true;
        }
        Material material;
        if (args.length > 1) {
            material = Material.matchMaterial(args[1]);
        } else {
            material = Material.matchMaterial("bedrock");
        }
        if (material == null) {
            p.sendMessage("§4§lInvalid block type!");
            return true;
        }
        try {
            Integer.parseInt(args[0]);
        } catch (Exception e) {
            p.sendMessage("§4§lInvalid size! Size must be an odd integer greater than or equal to 5");
            return true;
        }
        int size = Integer.parseInt(args[0]);
        if (size % 2 == 0 || size < 5) {
            p.sendMessage("§4§lInvalid size! Size must be an odd integer greater than or equal to 5");
            return true;
        }
        World world = p.getWorld();
        buildMaze(p, world, material, size);
        return true;
    }

    private void buildMaze(Player player, World world, Material material, int size) {
        Location location;
        int x = player.getLocation().getBlockX() + 1;
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ() + 1;
        for (int i = 0; i < size; i++) { //generate foundation
            for (int j = 0; j < size; j++) {
                location = new Location(world, x + i, y - 1, z + j);
                location.getBlock().setType(material);
            }
        }

        for (int i = 0; i < size; i++) { //generate boundary
            location = new Location(world, x + i, y, z);
            location.getBlock().setType(material);
            location = new Location(world, x + i, y + 1, z);
            location.getBlock().setType(material);
            location = new Location(world, x + i, y, z + size - 1);
            location.getBlock().setType(material);
            location = new Location(world, x + i, y + 1, z + size - 1);
            location.getBlock().setType(material);
            location = new Location(world, x, y, z + i);
            location.getBlock().setType(material);
            location = new Location(world, x, y + 1, z + i);
            location.getBlock().setType(material);
            location = new Location(world, x + size - 1, y, z + i);
            location.getBlock().setType(material);
            location = new Location(world, x + size - 1, y + 1, z + i);
            location.getBlock().setType(material);
        }

        int mazeSize = size / 2;
        Maze maze = new Maze(mazeSize);

        for (int i = 0; i < mazeSize; i++) { //build "posts"
            for (int j = 0; j < mazeSize; j++) {
                location = new Location(world, x + i * 2 + 2, y, z + j * 2 + 2);
                location.getBlock().setType(material);
                location = new Location(world, x + i * 2 + 2, y + 1, z + j * 2 + 2);
                location.getBlock().setType(material);
            }
        }


        for (int i = 0; i < mazeSize; i++) { //fill in walls
            for (int j = 0; j < mazeSize; j++) {
                if (i + 1 < mazeSize && maze.getMaze()[i][j].isInAdjacencyList(maze.getMaze()[i + 1][j])) {
                    location = new Location(world, x + i * 2 + 2, y, z + j * 2 + 1);
                    location.getBlock().setType(material);
                    location = new Location(world, x + i * 2 + 2, y + 1, z + j * 2 + 1);
                    location.getBlock().setType(material);
                }
                if (j + 1 < mazeSize && maze.getMaze()[i][j].isInAdjacencyList(maze.getMaze()[i][j + 1])) {
                    location = new Location(world, x + i * 2 + 1, y, z + j * 2 + 2);
                    location.getBlock().setType(material);
                    location = new Location(world, x + i * 2 + 1, y + 1, z + j * 2 + 2);
                    location.getBlock().setType(material);
                }
            }
        }

        Material air = Material.matchMaterial("air");
        if (air == null) {
            return;
        }
        location = new Location(world, x + 1, y, z);
        location.getBlock().setType(air);
        location = new Location(world, x + 1, y + 1, z);
        location.getBlock().setType(air);
        location = new Location(world, x + size - 2, y, z + size - 1);
        location.getBlock().setType(air);
        location = new Location(world, x + size - 2, y + 1, z + size - 1);
        location.getBlock().setType(air);
    }
}