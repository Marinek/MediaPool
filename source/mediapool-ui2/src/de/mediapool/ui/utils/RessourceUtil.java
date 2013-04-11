package de.mediapool.ui.utils;

import java.lang.reflect.Field;

import com.vaadin.server.Resource;

import de.mediapool.ui.ressource.MPIcons;


public class RessourceUtil {

	
	public static Resource getRessouceFromString(String ress) {
		try {
			Field field = MPIcons.class.getField(ress + "_ICO");
			
			return (Resource) field.get(null);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return MPIcons.ERROR_ICO;
	}
}
 