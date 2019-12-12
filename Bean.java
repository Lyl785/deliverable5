import java.util.Random;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>Bean: Each bean is assigned a skill level from 0-9 on creation according to
 * a normal distribution with average SKILL_AVERAGE and standard deviation
 * SKILL_STDEV. A skill level of 9 means it always makes the "right" choices
 * (pun intended) when the machine is operating in skill mode ("skill" passed on
 * command kine). That means the bean will always go right when a peg is
 * encountered, resulting it falling into slot 9. A skill evel of 0 means that
 * the bean will always go left, resulting it falling into slot 0. For the
 * in-between skill levels, the bean will first go right then left. For example,
 * for a skill level of 7, the bean will go right 7 times then go left twice.
 * 
 * <p>Skill levels are irrelevant when the machine operates in luck mode. In that
 * case, the bean will have a 50/50 chance of going right or left, regardless of
 * skill level.
 */

public class Bean {
	// TODO: Add member methods and variables as needed 
	
	private static final double SKILL_AVERAGE = 4.5;	// MainPanel.SLOT_COUNT * 0.5;
	private static final double SKILL_STDEV = 1.5;	
	private Random rand_num;
	private boolean luck_mode;
	private int position_x;
	private int skills;
	private int remaining_skills;	// Math.sqrt(SLOT_COUNT * 0.5 * (1 - 0.5));
	private int random_next;
	
	/**
	 * Constructor - creates a bean in either luck mode or skill mode.
	 * 
	 * @param isLuck	whether the bean is in luck mode
	 * @param rand      the random number generator
	 */
	Bean(boolean isLuck, Random rand) {
		
		luck_mode = isLuck;
		rand_num = rand;
		skills = (int)Math.round(rand_num.nextGaussian() * 1.5 + 4.5);
		if(skills >= 9){
			skills = 9;
		}
		else if(skills <= 0){
			skills = 0;
		}
		position_x = 0;
		// TODO: Implement
	}
	
	
	public void reset(){
		remaining_skills = skills;
		position_x = 0;

	}

	public int get_value(){
		return position_x;

	}


	public void branch(){
		if(luck_mode == true){
			random_next = rand_num.nextInt(100);
			if(random_next > 50){
				position_x++;
			}
		}
		else if( remaining_skills > 0){
			remaining_skills--;
			position_x++;

		}

	}


}
