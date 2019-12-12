import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import gov.nasa.jpf.vm.Verify;

import java.util.*; 

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>BeanCounterLogic: The bean counter, also known as a quincunx or the Galton
 * box, is a device for statistics experiments named after English scientist Sir
 * Francis Galton. It consists of an upright board with evenly spaced nails (or
 * pegs) in a triangular form. Each bean takes a random path and falls into a
 * slot.
 *
 * <p>Beans are dropped from the opening of the board. Every time a bean hits a
 * nail, it has a 50% chance of falling to the left or to the right. The piles
 * of beans are accumulated in the slots at the bottom of the board.
 * 
 * <p>This class implements the core logic of the machine. The MainPanel uses the
 * state inside BeanCounterLogic to display on the screen.
 * 
 * <p>Note that BeanCounterLogic uses a logical coordinate system to store the
 * positions of in-flight beans.For example, for a 4-slot machine:
 *                      (0, 0)
 *               (0, 1)        (1, 1)
 *        (0, 2)        (1, 2)        (2, 2)
 *  (0, 3)       (1, 3)        (2, 3)       (3, 3)
 * [Slot0]       [Slot1]       [Slot2]      [Slot3]
 */

public class BeanCounterLogic {
	// TODO: Add member methods and variables as needed

	// No bean in that particular Y coordinate
	public static final int NO_BEAN_IN_YPOS = -1;
	private Bean[] in_flight_beans;
	private Queue<Bean> remaining_beans;
	private Queue<Bean> [] slots;
	

	/**
	 * Constructor - creates the bean counter logic object that implements the core
	 * logic. Our bean counter should start with a single bean at the top.
	 * 
	 * @param slotCount the number of slots in the machine
	 */
	BeanCounterLogic(int slotCount) {
		// TODO: Implement
		in_flight_beans =new Bean[slotCount];
		remaining_beans = new LinkedList<Bean>();
		slots = new LinkedList[slotCount];
		for(int i = 0; i < slots.length; i++){
			slots[i] = new LinkedList<Bean>();
		}
		
	}

	/**
	 * Returns the number of beans remaining that are waiting to get inserted.
	 * 
	 * @return number of beans remaining
	 */
	public int getRemainingBeanCount() {
		// TODO: Implement
		return remaining_beans.size();
	}

	/**
	 * Returns the x-coordinate for the in-flight bean at the provided y-coordinate.
	 * 
	 * @param yPos the y-coordinate in which to look for the in-flight bean
	 * @return the x-coordinate of the in-flight bean
	 */
	public int getInFlightBeanXPos(int yPos) {
		// TODO: Implement
		int result;
		if (in_flight_beans[yPos] != null){
			result = in_flight_beans[yPos].get_value();
		}else{
		result= NO_BEAN_IN_YPOS;
		}
		return result;
	}

	/**
	 * Returns the number of beans in the ith slot.
	 * 
	 * @param i index of slot
	 * @return number of beans in slot
	 */
	public int getSlotBeanCount(int i) {
		// TODO: Implement
		
		return slots[i].size();
	}

	/**
	 * Calculates the average slot bean count.
	 * 
	 * @return average of all slot bean counts
	 */
	public double getAverageSlotBeanCount() {
		// TODO: Implement
		int x = 0;
		double y = 0;
		for(int i = 0; i < slots.length; i++){
			y = i * getSlotBeanCount(i);
			x += getSlotBeanCount(i);	
		}
		if (x > 0) {
            return y / x;
        }
		return 0;
	}

	/**
	 * Removes the lower half of all beans currently in slots, keeping only the
	 * upper half.
	 */
	public void upperHalf() {
		// TODO: Implement
		int index = 0;
		int sum = 0;
		for(int i = 0; i < slots.length; i++){
			sum += slots[i].size();
		}
		for(int j = 0; j < sum/2; j++){
			while(index < slots.length && slots[index].size() == 0){
				index++;
			}
			
				slots[index].remove();
			
		}
	}

	/**
	 * Removes the upper half of all beans currently in slots, keeping only the
	 * lower half.
	 */
	public void lowerHalf() {
		
		int index = slots.length - 1;
		int sum = 0;
		for(int i = 0; i < slots.length; i++){
			sum += slots[i].size();
		}
		for(int j = 0; j < sum/2; j++){
			while(index >= 0 && slots[index].size() == 0){
				index--;
			}
			
				slots[index].remove();
			
		}
		// TODO: Implement
	}

	/**
	 * A hard reset. Initializes the machine with the passed beans. The machine
	 * starts with one bean at the top.
	 */
	public void reset(Bean[] beans) {
		// TODO: Implement
		
		remaining_beans.clear();
		 for (int i = 0; i < slots.length; i++) {
	            slots[i].clear();
	        }	
		 for (int j = 0; j < slots.length; j++) {
	            in_flight_beans[j] = null;
	        }
		 for (int k = 0; k < beans.length; k++) {
	            remaining_beans.add(beans[k]);
	        }
		if(remaining_beans.size() > 0){
			in_flight_beans[0] = remaining_beans.remove();

			in_flight_beans[0].reset();
			
		}
		else{
			in_flight_beans[0] = null;
		}
	       
	}

	/**
	 * Repeats the experiment by scooping up all beans in the slots and all beans
	 * in-flight and adding them into the pool of remaining beans. As in the
	 * beginning, the machine starts with one bean at the top.
	 */
	public void repeat() {
		// TODO: Implement
		
		for (int i = 0; i < this.slots.length; i++) {
            if (in_flight_beans[i] != null) {
                remaining_beans.add(in_flight_beans[i]);
                in_flight_beans[i] = null;
            }
        }
		 for (int j = 0; j < slots.length; j++) {
	            remaining_beans.addAll(slots[j]);
	            slots[j].clear();
	        }
	        
	        if(remaining_beans.size() > 0){
				in_flight_beans[0] = remaining_beans.remove();

	
				in_flight_beans[0].reset();
				
			}
			else{
				in_flight_beans[0] = null;
			}
		
	}

	/**
	 * Advances the machine one step. All the in-flight beans fall down one step to
	 * the next peg. A new bean is inserted into the top of the machine if there are
	 * beans remaining.
	 * 
	 * @return whether there has been any status change. If there is no change, that
	 *         means the machine is finished.
	 */
	
	
	public boolean advanceStep() {
		boolean adv = false;
		// TODO: Implement
		//System.out.println("remainging beans0"+ remaining_beans.size());
		for(int i = slots.length-1; i >=0; i--){
			Bean beans = in_flight_beans[i];
			if(beans != null){
				if(i == slots.length-1){
					assert beans.get_value() < slots.length;
					int index = beans.get_value();
					slots[index].add(beans);
					//System.out.println("remainging beans1"+ remaining_beans.size());
					
				}
				else{
					beans.branch();
					in_flight_beans[i+1] = beans;
					//System.out.println("remainging beans2"+ remaining_beans.size());
					
				}
				adv = true;
			}else if(i < slots.length-1){
				in_flight_beans[i+1] = null;
				//System.out.println("remainging beans3"+ remaining_beans.size());
			}
			
			//System.out.println("remainging beans"+ remaining_beans.size());
		}
		 if (remaining_beans.size() > 0) {
	            (in_flight_beans[0] = remaining_beans.remove()).reset();
	        }
	        else {
	            in_flight_beans[0] = null;
	        }
		return adv;
	}

	public static void showUsage() {
		System.out.println("Usage: java BeanCounterLogic <number of beans> <luck | skill>");
		System.out.println("Example: java BeanCounterLogic 400 luck");
	}

	/**
	 * Auxiliary main method. Runs the machine in text mode with no bells and
	 * whistles. It simply shows the slot bean count at the end. Also, when the
	 * string "test" is passed to args[0], the program enters test mode. In test
	 * mode, the Java Pathfinder model checking tool checks the logic of the machine
	 * for a small number of beans and slots.
	 * 
	 * @param args args[0] is an integer bean count, args[1] is a string which is
	 *             either luck or skill.
	 */
	public static void main(String[] args) {
		boolean luck;
		int beanCount = 0;
		int slotCount = 0;

		if (args.length == 1 && args[0].equals("test")) {
			// TODO: Verify the model checking passes for beanCount values 0-3 and slotCount
			// values 1-5 using the JPF Verify API.
			beanCount = 100;//Verify.getInt(0, 3);
	        slotCount = 10;//Verify.getInt(1, 5);
			//System.out.println("beanCount"+beanCount);
			//System.out.println("slotCount"+slotCount);
			// Create the internal logic
			BeanCounterLogic logic = new BeanCounterLogic(slotCount);
			// Create the beans (in luck mode)
			Bean[] beans = new Bean[beanCount];
			for (int i = 0; i < beanCount; i++) {
				beans[i] = new Bean(true, new Random());
			}
			// Initialize the logic with the beans
			logic.reset(beans);

			while (true) {
				if (!logic.advanceStep()) {
					break;
				}

				// Checks invariant property: all positions of in-flight beans have to be
				// legal positions in the logical coordinate system.
				for (int yPos = 0; yPos < slotCount; yPos++) {
					int xPos = logic.getInFlightBeanXPos(yPos);
					assert xPos == BeanCounterLogic.NO_BEAN_IN_YPOS || (xPos >= 0 && xPos <= yPos);
				}
				int remaining_beans_num = logic.getRemainingBeanCount();
				int slot_beans_num = 0;
				int inflight_beans_num = 0;
				for(int i = 0; i < slotCount;i++){
					slot_beans_num += (logic.getSlotBeanCount(i));
				}
				
	            for (int j = 0; j < slotCount;j++) {
	                    if (logic.getInFlightBeanXPos(j) != -1) {
	                    	inflight_beans_num++;
	                    }
	            }
	            //System.out.println("Arraylist " + );
	            System.out.println("remainging beans_num"+ remaining_beans_num);
	            System.out.println("slot_beans_num "+ slot_beans_num);
	            System.out.println("inflight_beans_num"+ inflight_beans_num);
	            //assert remaining_beans_num + slot_beans_num + inflight_beans_num == beanCount;
				// TODO: Check invariant property: the sum of remaining, in-flight, and in-slot
				// beans always have to be equal to beanCount
	           
				
			}
			// TODO: Check invariant property: when the machine finishes,
			// 1. There should be no remaining beans.
			// 2. There should be no beans in-flight.
			// 3. The number of in-slot beans should be equal to beanCount.
			int remaining_bean_num_1 = logic.getRemainingBeanCount();   
            int slot_beans_num_1 = 0;
            int inflight_beans_num_1 = 0;
            for (int i = 0; i < slotCount; i++) {
            	slot_beans_num_1 += logic.getSlotBeanCount(i);
            }
            
     
            for (int j = 0; j < slotCount; j++) {
                if (logic.getInFlightBeanXPos(j) != -1) {
                	inflight_beans_num_1++;
                }
            }
            System.out.println("remaining_bean_num_1"+remaining_bean_num_1+"inflight_beans_num_1"+inflight_beans_num_1+"slot_beans_num_1"+slot_beans_num_1);
            //assert remaining_bean_num_1 == 0 && inflight_beans_num_1 == 0 && slot_beans_num_1 == beanCount;
			
			//return;
		}

		if (args.length != 2) {
			showUsage();
			return;
		}

		try {
			beanCount = Integer.parseInt(args[0]);
		} catch (NumberFormatException ne) {
			showUsage();
			return;
		}
		if (beanCount < 0) {
			showUsage();
			return;
		}

		if (args[1].equals("luck")) {
			luck = true;
		} else if (args[1].equals("skill")) {
			luck = false;
		} else {
			showUsage();
			return;
		}
		
		slotCount = 10;

		// Create the internal logic
		BeanCounterLogic logic = new BeanCounterLogic(slotCount);
		// Create the beans (in luck mode)
		Bean[] beans = new Bean[beanCount];
		for (int i = 0; i < beanCount; i++) {
			beans[i] = new Bean(luck, new Random());
		}
		// Initialize the logic with the beans
		logic.reset(beans);
					
		// Perform the experiment
		while (true) {
			if (!logic.advanceStep()) {
				break;
			}
		}
		// display experimental results
		System.out.println("Slot bean counts:");
		for (int i = 0; i < slotCount; i++) {
			System.out.print(logic.getSlotBeanCount(i) + " ");
		}
		System.out.println("");
	}
}
