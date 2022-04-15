package at.dietze.re.biome;

import at.dietze.re.Main;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class BiomeExternal implements Listener, IBiome {

    @Override
    public Biome biome(Player p) {
        return p.getLocation().getBlock().getBiome();
    }

    @Override
    public boolean validBiome(Player p) {
        return biome(p).equals(Biome.COLD_BEACH) || biome(p).equals(Biome.MUTATED_TAIGA_COLD) ||
                biome(p).equals(Biome.ICE_MOUNTAINS) || biome(p).equals(Biome.TAIGA_COLD) || biome(p).equals(Biome.TAIGA_COLD_HILLS);
    }

    @Override
    public boolean day(Player p) {
        Server server = Main.getPlugin().getServer();
        long time = server.getWorld(p.getWorld().getName()).getTime();

        return time > 0 && time < 12300;
    }

    @Override
    public int armorNull(Player p) {
        int size = 0;
        if (p.getInventory().getHelmet() != null)
            size++;
        if (p.getInventory().getLeggings() != null)
            size++;
        if (p.getInventory().getChestplate() != null)
            size++;
        if (p.getInventory().getBoots() != null)
            size++;

        return size;
    }

    void cold(Player p){
        if(validBiome(p) && !(p.getLocation().getBlock().getType() == Material.TORCH) && !(p.getItemInHand().getType() == Material.TORCH) && (p.getLocation().getY() >= 60 && armorNull(p) == 0){
               p.damage(0.5);
        }
    }

    void night(Player p){
        if(!day(p) && armorNull(p) < 3 && !(p.getLocation().getBlock().getType() == Material.TORCH) && !(p.getItemInHand().getType() == Material.TORCH) && p.getLocation().getY() >= 60){
               p.damage(0.25);
        }
    }

    void desert(Player p){
        if(biome(p).equals(org.bukkit.block.Biome.DESERT) && !(p.getLocation().getBlock().getType() == Material.STATIONARY_WATER || p.getLocation().getBlock().getType() == Material.WATER) && p.getLocation().getY() >= 60 && !p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && day(p)) {
               if (armorNull(p) == 1) {
                      p.damage(0.25);
               } else if (armorNull(p) == 2) {
                      p.damage(0.5);
               } else if (armorNull(p) == 3) {
                      p.damage(0.75);
               } else if (armorNull(p) == 4) {
                      p.damage(1);
             }
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        night(p);
        cold(p);
        desert(p);


    }
}
