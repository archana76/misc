package com.archana.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * Test class for {@link BasketPricer}
 * 
 * @author Administrator
 *
 */
public class BasketPricerTests {

	private List<IFruit> fruitsBasket = new ArrayList<>();;
	private BigDecimal expectedValue = BigDecimal.ZERO;

	@Before
	public void setUp() {
		fruitsBasket.clear();
		expectedValue = BigDecimal.ZERO;
		fruitsBasket.add(Fruit.BANANA);
		fruitsBasket.add(Fruit.APPLE);
		fruitsBasket.add(Fruit.LEMON);
		fruitsBasket.add(Fruit.PEACHES);
		fruitsBasket.add(Fruit.ORANGE);
		for (Fruit f : Fruit.values()) {
			expectedValue = expectedValue.add(f.getPrice());
		}
	}

	/**
	 * Send a null list to test {@link BasketPricer#validateInput(List)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void whenIllegalArgumentThenExceptionIsThrown() {
		BasketPricer.calculateBasketPrice(null);

	}

	/**
	 * Send a null Fruit item in input list
	 * {@link BasketPricer#calculateBasketPrice(List)}
	 */
	@Test(expected = RuntimeException.class)
	public final void whenRuntimeExceptionIsThrown() {
		fruitsBasket.add(null);
		System.out.println(BasketPricer.calculateBasketPrice(fruitsBasket));
	}

	/**
	 * make sure we are summing up fruit prices correctly in basket Test case to
	 * cover {@link BasketPricer#calculateBasketPrice(List)}
	 * 
	 */
	@Test
	public final void assertBasketIsPricedCorrectly() {
		BigDecimal result = BasketPricer.calculateBasketPrice(fruitsBasket);
		Assert.assertEquals(expectedValue, result);
	}

	/**
	 * stub implementation of IFruit to test out
	 *
	 */
	enum Fruit implements IFruit {

		APPLE(new BigDecimal(2.27)), ORANGE(new BigDecimal(2.07)), PEACHES(new BigDecimal(3.93)), BANANA(
				new BigDecimal(0.25)), LEMON(new BigDecimal(0.79));

		private BigDecimal price;

		Fruit(BigDecimal price) {
			System.out.println("Name: " + this.name() + " initialized.");
			this.price = price;
		}

		@Override
		public BigDecimal getPrice() {
			return this.price;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return this.name();
		}
	}
}
