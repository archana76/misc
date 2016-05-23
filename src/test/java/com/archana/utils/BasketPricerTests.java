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

public class BasketPricerTests {
	
	private List<IFruit> fruitsBasket = new ArrayList<>();	;
	private BigDecimal expectedValue = BigDecimal.ZERO;
	
	
	@Before
	public void setUp() {
		fruitsBasket.clear();
		expectedValue = BigDecimal.ZERO;
		fruitsBasket.add(Fruit.BANANA);
		fruitsBasket.add(Fruit.APPLE);
		fruitsBasket.add(Fruit.PEAR);
		fruitsBasket.add(Fruit.GRAPE);
		fruitsBasket.add(Fruit.MANGO);
		fruitsBasket.add(Fruit.ORANGE);
		for (Fruit f : Fruit.values()) {
			expectedValue = expectedValue.add(f.getPrice());			 
		}			
	}
	
	//send a null list to method
	@Test(expected = IllegalArgumentException.class)
    public final void whenIllegalArgumentThenExceptionIsThrown() {		
		BasketPricer.calculateBasketPrice(null);
        
    }
	
	//send a null fruit inside basket
	@Test(expected = RuntimeException.class)
    public final void whenRuntimeExceptionIsThrown() {	
		fruitsBasket.add(null);
		System.out.println(BasketPricer.calculateBasketPrice(fruitsBasket));
    }
	
	
	//make sure we are summing up fruit prices correctly in basket
	@Test
    public final void assertBasketIsPricedCorrectly() {			
		BigDecimal result = BasketPricer.calculateBasketPrice(fruitsBasket);		
        Assert.assertEquals(expectedValue, result);
    }
	
	
    
    //A Test Implementation of fruit interface
    enum Fruit implements IFruit {
    	
    	APPLE(new BigDecimal(2.27)), ORANGE(new BigDecimal(2.07)), 
    	MANGO(new BigDecimal(3.93)), GRAPE(new BigDecimal(1.79)), 
    	BANANA(new BigDecimal(0.25)), PEAR(new BigDecimal(0.79));

    	private BigDecimal price;

    	Fruit(BigDecimal price) {
    		System.out.println("Name: " + this.name() + " initialized.");
    		this.price = price;
    	}

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
