package com.demonwav.signz;

import java.util.regex.Pattern;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Signz extends JavaPlugin implements Listener {

    private static final Pattern pattern = Pattern.compile("(?i)&([0-9a-fklmnor&])");

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSignChange(final SignChangeEvent event) {
        final String[] lines = event.getLines();
        for (int i = 0; i < lines.length; i++) {
            final String replaced = pattern.matcher(lines[i]).replaceAll(ChatColor.COLOR_CHAR + "$1").replace(ChatColor.COLOR_CHAR + "&", "&");
            event.setLine(i, replaced);
        }
    }
}
