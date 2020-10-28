package at.dietze.re.food;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemConsume implements IConsume, Listener {


    @Override
    public void heal(Player p) {
        try {
            p.setHealth(p.getHealth() + 3);
        } catch (Exception e) {
            System.out.println("The player can't be healed over 20HP, this error will be ignored.");
            p.setHealth(20);
        }
    }

    public boolean isItem(){
    
     if (e.getItem().getType().equals(Material.POTION) && e.getItem().getDurability() == 0) {
           return true;
        } 
     return false;
    }
   }

    @EventHandler
    public void itemConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        heal(p);
        if(isItem()){
          p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 100, 1));
        }
    }
}
