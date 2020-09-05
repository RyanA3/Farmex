package me.felnstaren.farmex.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import me.felnstaren.farmex.logger.Level;
import me.felnstaren.farmex.logger.Logger;

public class FarmexEventMaster {
	
	private ArrayList<IFarmexListener> listeners;
	
	public FarmexEventMaster() {
		this.listeners = new ArrayList<IFarmexListener>();
	}
	
	public void register(IFarmexListener listener) {
		listeners.add(listener);
	}
	
	
	
	public void trigger(IFarmexEvent event) {
		for(IFarmexListener listener : listeners) {
			for(Method method : listener.getClass().getDeclaredMethods()) {
				if(method.getDeclaredAnnotation(EventHandler.class) == null) continue;
				
				try {
					method.invoke(listener, event);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					Logger.log(Level.SEVERE, "A fatal error occured whilst invoking a listener method for an event \n"
							+ "Listener; " + listener.getClass().getSimpleName() + "\n"
							+ "Event; " + event.getClass().getSimpleName());
				}
			}
		}
	}

}
