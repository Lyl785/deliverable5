import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.*;

public class BeanCounterLogicTest {
	// TODO: implement
	// Be sure to mock your beans!
	Random rand = new Random();
	Bean b;
	ArrayList<Bean> t;
	BeanCounterLogicTest test_value;
	@Before
	public void setUp() {
		test_value = new BeanCounterLogicTest();
		t = new ArrayList<Bean>();
		b = new Bean(true, rand);
	}
	@After 
	public void end(){
		System.out.println("Finish the test.");
		
	}
	
	
	@Test
	public void test_getRemainingBeanCount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		t.add(b);
		Field method = BeanCounterLogic.class.getDeclaredField("remaining_beans");
		method.setAccessible(true);
		method.set(test_value, t);
		//method.getRemainingBeanCount();
		//assertEquals(1, test_value.test_getRemainingBeanCount());
		
	}
	
	@Test 
	public void test_getInFlightBeanXPos() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void test_getSlotBeanCount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void test_getAverageSlotBeanCount() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void upperHalf() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void lowerHalf() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void reset() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void repeat() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	@Test 
	public void advanceStep() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	
		Field method = BeanCounterLogic.class.getDeclaredField("result");
		method.setAccessible(true);
		test_value.test_getInFlightBeanXPos();
	
	}
	
	
	
}
