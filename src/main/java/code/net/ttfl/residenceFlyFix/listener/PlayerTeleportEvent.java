package code.net.ttfl.residenceFlyFix.listener;

import code.net.ttfl.residenceFlyFix.ResidenceFlyFix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerTeleportEvent implements Listener {
    private ResidenceFlyFix plugin;

    public PlayerTeleportEvent(ResidenceFlyFix plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerTeleport(org.bukkit.event.player.PlayerTeleportEvent event){
        Player player = event.getPlayer();
        if(player.isFlying()){
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage("stop tp!");
            plugin.sendMessage("no-tp", event.getPlayer());
        }
    }
}
