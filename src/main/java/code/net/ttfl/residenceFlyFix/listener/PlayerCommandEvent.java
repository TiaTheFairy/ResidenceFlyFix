package code.net.ttfl.residenceFlyFix.listener;

import code.net.ttfl.residenceFlyFix.ResidenceFlyFix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;


public class PlayerCommandEvent implements Listener {

    private ResidenceFlyFix plugin;

    public PlayerCommandEvent(ResidenceFlyFix plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event){
        System.out.println("command: " + event.getMessage());
        Player player = event.getPlayer();
        if(player.isFlying()){
            List<String> commands = plugin.getConfig().getStringList("blacklist-command-list");

            if(commands.stream().anyMatch(str -> event.getMessage().startsWith(str))){
                event.setCancelled(true);
                plugin.sendMessage("no-command", event.getPlayer());
            }
        }
    }
}
