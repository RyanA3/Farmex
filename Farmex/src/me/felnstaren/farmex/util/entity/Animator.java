package me.felnstaren.farmex.util.entity;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;
import net.minecraft.server.v1_16_R1.PacketPlayOutAnimation;

public class Animator {

	/*
	 * TODO: Version support (Reflection)
	 * Should be easy, hopefully, I hope, please
	 */
	
	public static void swingArm(Entity entity) {
		try {
			PacketPlayOutAnimation swing = new PacketPlayOutAnimation(((CraftEntity) entity).getHandle(), 0);
			for(Player player : Bukkit.getOnlinePlayers()) ((CraftPlayer) player).getHandle().playerConnection.sendPacket(swing);
		} catch(Exception e) {
			Logger.log(Level.DEBUG, "Arm swing animation failed to play. Are you running a server version other than 1.16.x?");
		}
	}
	
}
