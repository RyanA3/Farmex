package me.felnstaren.farmex.event;

public abstract class IFarmexEvent {

	private boolean cancelled = false;
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isCancelled() {
		return cancelled;
	}
	
}
