package at.dietze.re.biome;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public interface IBiome {

    Biome biome(Player p);
    boolean validBiome(Player p);
    boolean day(Player p);
    int armorNull(Player p);
}
