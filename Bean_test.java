import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;




public class Bean_test {
	Random rand = new Random();
	Bean test_value;
	@Before
	public void setUp() {
		test_value = new Bean(true, rand);
	}
	@After 
	public void end(){
		System.out.println("Finish the test.");
		
	}

	
	
	@Test
	public void test_reset() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field method = Bean.class.getDeclaredField("position_x");
		Field method1 = Bean.class.getDeclaredField("remaining_skills");
		Field method2 = Bean.class.getDeclaredField("skills");
		method.setAccessible(true);
		method1.setAccessible(true);
		method2.setAccessible(true);
		test_value.reset();
		int x = method.getInt(test_value);
		int remaining_skills = method1.getInt(test_value);
		int skills = method2.getInt(test_value);
		assertEquals(0, x);
		assertEquals(remaining_skills, skills);
	}
	
	@Test 
	public void test_get_value() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field method = Bean.class.getDeclaredField("position_x");
		method.setAccessible(true);
		//test_value.get_value();
		method.setInt(test_value, 1);
		assertEquals(1, test_value.get_value());	
		
		}
	
	@Test 
	public void test_branch_test1() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field method = Bean.class.getDeclaredField("random_next");
		method.setAccessible(true);
		test_value.branch();
		int x = method.getInt(test_value);
		assertTrue(x > 0 && x < 100);
	}
		
	
	@Test 
	public void test_branch_test2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

		test_value = new Bean (false, rand);
		Field method = Bean.class.getDeclaredField("remaining_skills");
		method.setAccessible(true);
		method.setInt(test_value, 1);
		test_value.branch();
		int x = method.getInt(test_value);
		assertEquals(0, x);
		
		}
	
}