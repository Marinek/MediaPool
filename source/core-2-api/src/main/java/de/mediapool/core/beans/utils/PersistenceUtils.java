package de.mediapool.core.beans.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;


import de.mediapool.core.beans.AbstractBean;
import de.mediapool.core.persistence.core.interfaces.IPSValueObject;

public class PersistenceUtils {
	
	private static Logger log = Logger.getLogger(PersistenceUtils.class);
	
	public static <T extends AbstractBean> T toBean(Class<T> pBeanClass, IPSValueObject pValueObject) {
		if(pBeanClass != null) {
			try {
				T lBean = pBeanClass.newInstance();
				
				return toBean(lBean, pValueObject);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("Parameter dürfen nicht null sein!");
		}
	}
	
	public static <T extends AbstractBean> T toBean(T pBean, IPSValueObject pValueObject) {
		if(pBean != null && pValueObject != null) {
			try {
				Class<?> lBeanObjectClass = pBean.getClass();

				while(lBeanObjectClass != null) {

					Field[] lBeanFields = lBeanObjectClass.getDeclaredFields();
					for(Field lBeanField : lBeanFields) {
						try {
							Method lValueObjectGetMethod = pValueObject.getClass().getMethod("get" + firstUpperCase(lBeanField.getName()), new Class [] {});
							Object lValueObjectFieldData = lValueObjectGetMethod.invoke(pValueObject, new Object[] {});
							
							Method lBeanSetMethod = pBean.getClass().getMethod("set" + firstUpperCase(lBeanField.getName()), lValueObjectFieldData.getClass());
							lBeanSetMethod.invoke(pBean, lValueObjectFieldData);
						} catch (NoSuchMethodException nsme) {
							log.debug("Das Feld '" + lBeanField.getName() + "' konnte nicht zugeordnet werden.");
						}
					}
					lBeanObjectClass = lBeanObjectClass.getSuperclass();
				}
				return pBean;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("Parameter dürfen nicht null sein!");
		}
	}

	public static <T extends IPSValueObject> T toVO(Class<T> pValueObjectClass, AbstractBean pBeanObject) {
		if(pValueObjectClass != null && pBeanObject != null) {
			try {
				T lValueObject = pValueObjectClass.newInstance();
				Class<?> lValueObjectClass = lValueObject.getClass();

				while(lValueObjectClass != null) {
					Field[] lValueObjectFields = lValueObjectClass.getDeclaredFields();
					for(Field lValueObjectField : lValueObjectFields) {
						try {
							Method lBeanObjecttGetMethod = pBeanObject.getClass().getMethod("get" + firstUpperCase(lValueObjectField.getName()), new Class [] {});
							Object lBeanFieldData = lBeanObjecttGetMethod.invoke(pBeanObject, new Object[] {});

							Method lVOSetMethod = lValueObject.getClass().getMethod("set" + firstUpperCase(lValueObjectField.getName()), lBeanFieldData.getClass());
							lVOSetMethod.invoke(lValueObject, lBeanFieldData);
						} catch (NoSuchMethodException nsme) {
							log.debug("Das Feld '" + lValueObjectField.getName() + "' konnte nicht zugeordnet werden.");
						}
					}
					lValueObjectClass = lValueObjectClass.getSuperclass();
				}
				return lValueObject;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("Parameter dürfen nicht null sein!");
		}
	}

	private static String firstUpperCase(String name) {
		return name.substring(0,1).toUpperCase() + name.substring(1);
	}
}
