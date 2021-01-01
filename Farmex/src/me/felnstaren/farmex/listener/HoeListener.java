package me.felnstaren.farmex.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.farmex.config.Options;
import me.felnstaren.farmex.event.FarmexEventMaster;
import me.felnstaren.farmex.event.events.CropHarvestEvent;
import me.felnstaren.farmex.event.events.CropPlantEvent;
import me.felnstaren.farmex.registry.CustomMaterial;
import me.felnstaren.farmex.registry.crop.CropBlockRegistry;
import me.felnstaren.farmex.registry.crop.CropType;
import me.felnstaren.farmex.registry.crop.ICropEntry;
import me.felnstaren.farmex.registry.seed.ISeedEntry;
import me.felnstaren.farmex.registry.seed.SeedEntry;
import me.felnstaren.farmex.registry.seed.SeedItemRegistry;
import me.felnstaren.farmex.registry.seed.SeedType;
import me.felnstaren.farmex.util.block.VanillaCropUtils;
import me.felnstaren.farmex.util.entity.Animator;
import me.felnstaren.farmex.util.item.InventoryEditor;
import me.felnstaren.farmex.util.item.ItemEditor;

public class HoeListener implements Listener {
	
	private SeedItemRegistry seed_registry;
	private CropBlockRegistry crop_registry;
	private FarmexEventMaster event_master;
	
	public HoeListener(FarmexEventMaster event_master, SeedItemRegistry seed_registry, CropBlockRegistry crop_registry) {
		this.seed_registry = seed_registry;
		this.crop_registry = crop_registry;
		this.event_master = event_master;
	}

	@EventHandler
	public void onHarvest(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(event.getHand() != EquipmentSlot.HAND) return;
		if(event.getClickedBlock() == null) return;
		
		Player player = event.getPlayer();
		ItemStack hand = player.getInventory().getItemInMainHand();
		Block block = event.getClickedBlock();
		
		if(Options.USE_PERMISSION && !player.hasPermission("farmex.use")) return;
		if(!hand.getType().name().contains("HOE")) return;
		
		int radius = 0;
		if(CustomMaterial.isCustomMaterial(hand)) radius = 1;
		
		//Handle planting
		if(block.getType() == Material.FARMLAND) {
			ItemStack seed = getFirstSeed(player);
			if(seed == null) return;
			plant(player, block, seed, radius);
			return;
		}
		
		//Handle harvesting
		if(crop_registry.isCrop(block)) {
			harvest(player, block, radius);
			return;
		}
		
		//Damage Hoe/Scythe
		ItemEditor.damage(hand, radius + 1);
	}
	
	
	
	private void plant(Player player, Block block, ItemStack seed, int radius) {
		ISeedEntry entry = seed_registry.getEntry(seed);
		int seed_count = InventoryEditor.getTotalItems(player.getInventory(), seed);
		int seeded = 0;
		
		outer: for(int offX = -radius; offX <= radius; offX++) {
			for(int offZ = -radius; offZ <= radius; offZ++) {
				if(seeded >= seed_count) break outer;
				
				Block check = block.getRelative(offX, 0, offZ);
				Block plant = check.getRelative(BlockFace.UP);
				if(!VanillaCropUtils.isPlantable(check)) continue;

				CropPlantEvent farm_event = new CropPlantEvent(player, plant, seed);
				event_master.trigger(farm_event);
				if(farm_event.isCancelled()) continue;
				
				if(entry.getType() == SeedType.VANILLA) 
					plant.setType(((SeedEntry) entry).getBlockMaterial());
				
				seeded++;
			}
		}
		
		if(seeded > 0) Animator.swingArm(player);
		InventoryEditor.removeItems(player.getInventory(), seed, seeded);
	}
	
	private void harvest(Player player, Block block, int radius) {
		for(int offX = -radius; offX <= radius; offX++) {
			for(int offZ = -radius; offZ <= radius; offZ++) {
				Block plant = block.getRelative(offX, 0, offZ);
				if(!crop_registry.isCrop(plant)) continue;

				CropHarvestEvent farm_event = new CropHarvestEvent(player, plant);
				event_master.trigger(farm_event);
				if(farm_event.isCancelled()) continue;
				
				ICropEntry entry = crop_registry.getEntry(plant);
				
				if(entry.getType() == CropType.VANILLA) 
					VanillaCropUtils.harvest(plant);
				else
					crop_registry.unregister(plant);
			}
		}
		
		Animator.swingArm(player);
	}
	
	
	
	
	private ItemStack getFirstSeed(Player player) {
		ItemStack[] inventory = player.getInventory().getContents();
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] == null) continue;
			if(seed_registry.isSeed(inventory[i])) return inventory[i].clone();
		}
		return null;
	}
	
}
