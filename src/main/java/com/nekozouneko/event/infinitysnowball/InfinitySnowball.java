package com.nekozouneko.event.infinitysnowball;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class InfinitySnowball extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onThrowSnowball(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Snowball && e.getEntity().getShooter() instanceof Player) {
            Player p = ((Player) e.getEntity().getShooter());
            if (p.getGameMode() == GameMode.ADVENTURE || p.getGameMode() == GameMode.SURVIVAL) {
                Bukkit.getScheduler().runTaskLater(this, () -> p.getInventory().addItem(new ItemStack(Material.SNOWBALL)), 1L);
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball) {
            if (e.getHitEntity() != null && e.getHitEntity() instanceof Player && e.getEntity().getShooter() != null && e.getEntity().getShooter() instanceof Player) {
                Player p = ((Player) e.getHitEntity());
                Player s = (Player) e.getEntity().getShooter();
                Location l = p.getLocation();
                p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 60, 1, false, false, false));
                s.playSound(s.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);
            }
        }
    }

}
