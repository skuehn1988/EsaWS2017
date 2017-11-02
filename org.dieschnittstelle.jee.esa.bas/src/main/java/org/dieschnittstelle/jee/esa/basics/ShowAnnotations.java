package org.dieschnittstelle.jee.esa.basics;


import org.dieschnittstelle.jee.esa.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.jee.esa.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import static org.dieschnittstelle.jee.esa.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			;
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		//Consumer consumer = new Consumer();
		// ... and let them consume
		//consumer.doShopping(collection.getStockItems());
	}

	/*
	 * UE BAS2
	 */
	private static void showAttributes(Object consumable) {
		String message = "{ "+consumable.getClass().getSimpleName();
		for(Field field:consumable.getClass().getDeclaredFields()){
			message+= " "+field.getName()+":";
			try {
				message += consumable.getClass().getMethod(
						"get"+
								Character.toUpperCase(field.getName().charAt(0))+
								field.getName().substring(1)
				).invoke(consumable)+",";
			} catch(NoSuchMethodException exc) {
				show(exc.getMessage());
			} catch (IllegalAccessException exc) {
				show(exc.getMessage());
			} catch (InvocationTargetException exc) {
				show(exc.getMessage());
			}
		}
		show(message.substring(0, message.length() - 1)+"}");
	}

}
