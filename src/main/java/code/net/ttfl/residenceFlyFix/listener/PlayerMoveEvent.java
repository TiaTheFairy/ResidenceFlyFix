package code.net.ttfl.residenceFlyFix.listener;

import code.net.ttfl.residenceFlyFix.ResidenceFlyFix;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerMoveEvent implements Listener {
    private ResidenceFlyFix plugin;

    public PlayerMoveEvent(ResidenceFlyFix plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.isFlying() && !player.hasPermission("residenceflyfix.bypass.off")){
            if(!plugin.canFly(event.getPlayer())){
                player.setAllowFlight(false);
                player.setFlying(false);
                plugin.sendMessage("turn-off", event.getPlayer());
            }
        }
    }
}
