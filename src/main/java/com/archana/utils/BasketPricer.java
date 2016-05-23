package com.archana.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * A utility class which computes total price for a given basket of fruits. List
 * of individual fruits and prices are fixed and not dynamic. We have a finite
 * number of fruit entries in basket.
 * 
 * @Threadsafe
 * @author Administrator
 *
 */
public final class BasketPricer {

	/**
	 * This method iterates through the fruit list and computes sum of all the
	 * individual prices in fruit entry-set.
	 * 
	 * @Threadsafe
	 * @param fruitsBasket
	 * @return totalPrice for the fruit basket
	 */

	public final static BigDecimal calculateBasketPrice(List<? extends IFruit> fruitsBasket) {
		validateInput(fruitsBasket);
		BigDecimal totalPrice = fruitsBasket.stream().map(IFruit::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		return totalPrice;
	}

	/**
	 * Validate input list is well-formed
	 * 
	 * @param fruitsBasket
	 */
	private final static void validateInput(List<? extends IFruit> fruitsBasket) {
		if (fruitsBasket == null) {
			throw new IllegalArgumentException("Please provide a non-empty Fruit Basket");
		}
		for (IFruit f : fruitsBasket) {
			if (f == null || f.getPrice() == null) {
				throw new IllegalArgumentException("Please provide only non-null fruit items in basket");
			}
		}
	}
}

/**
 * Interface to elements in basket Any object to be placed in Basket should
 * implement this.
 *
 */
abstract interface IFruit {

	// return price of fruit
	public BigDecimal getPrice();

	// return name of fruit
	public String getName();
}
