/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

/**
 * The player and it's abilities.
 * A few player specific methods that are different for the enemy.
 * @author	Raffael Gyr
 * @version	1.0
 */
public class Player extends Dummy {
	/**
	 * constructor. Same as the conostructor of the Dummy.
	 * @param	startingHealth	the maxHP and current HP at the start of the game
	 * @param	cooldown1		the cooldown of ability 1
	 * @param	cooldown2		the cooldown of ability 2
	 * @param	cooldown3		the cooldown of ability 3
	 * @param	cooldown4		the cooldown of ability 4
	 */
	protected Player(int startingHealth, int cooldown1, int cooldown2, int cooldown3, int cooldown4) {
		super(startingHealth, cooldown1, cooldown2, cooldown3, cooldown4);
	}
	
	/**
	 * the first ability of the player. The heal ability.
	 */
	protected void abilityOne() {
		int heal = Useful.percentageRounded(this.getMaxHealth(), Stats.getPlayerOneHealprcnt());
		this.heal(heal);
		this.setCurrentCD1(this.getCooldown1());
	}
	
	/**
	 * the second ability of the player. The damage ability.
	 * @param	enemy	the Dummy that is supposed to take the damage
	 * @return	the Dummy that took the damage
	 */
	protected Dummy abilityTwo(Dummy enemy) {
		int damage = Useful.percentageRounded(this.getMaxHealth(), Stats.getPlayerTwoDmgpercent());
		enemy.takeDamage(damage);
		this.setCurrentCD2(this.getCooldown2());
		
		return enemy;
	}
	
	/**
	 * the third ability of the player. The shield ability.
	 */
	protected void abilityThree() {
		this.addShield(Stats.getPlayerThreeShield());
		this.setCurrentCD3(this.getCooldown3());
	}
	
	/**
	 * the fourth ability of the player. The ultimate ability.
	 */
	protected void abilityFour() {
		this.addMaxHealth(Stats.getPlayerFourIncreaseHP());
		this.setCurrentCD4(this.getCooldown4());
	}
	
	/**
	 * returns general information about the player.
	 * @return	the information as a String.
	 */
	protected String generalInformation() {
		String output = (
				"You currently have "
				+ this.getCurrentHealth()
				+ " Health righth now."
				+ "\n"
				+ "Your maxHealth is currently "
				+ this.getMaxHealth()
				+ " HP."
				);
		if (this.getShield() > 0) {
				output += ("\n"
				+ "You currently have a "
				+ this.getShield()
				+ " HP shield."
				);
		}
		
		return output;
	}
	
	/**
	 * returns info about the attack of the player.
	 * @return	the information as a String.
	 */
	protected String infoAttack() {
		/* 0 <= damage gets dealt < 1/3 enemy currentHealth but minimum 10
		 * 1/3 enemy currentHealth but minimum 10 <= no damage dealt < 2/3 enemy currentHealth but minimum 20
		 * 2/3 enemy currentHealth but minimum 20 <= damage yourself
		 */
		String output = (
				"Your damage will be dealt to your enemy if you intended to deal less than 1/3 of the enemy's current health or less than 10 damage." + "\n"
				+ "Your damage will be dealt to yourself if you intended to deal more than 2/3 of the enemy's current health or more than 20 damage." + "\n"
				+ "Your damage won't go anywhere if you intended to deal damage that was in higher than the first but lower than the second amount."
				);
		
		return output;
	}
	
	/**
	 * returns information about the first ability of the Player. The Heal ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityOne() {
		String output = (
				"You heal by "
				+ Stats.getPlayerOneHealprcnt()
				+ "% of your own maxHP."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getPlayerOneCD()
				+ " Rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the second ability of the Player. The Damage ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityTwo() {
		String output = (
				"You deal "
				+ Stats.getPlayerTwoDmgpercent()
				+ "% of your own HP as damage to your enemy."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getPlayerTwoCD()
				+ " Rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the third ability of the Player. The Shield ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityThree() {
		String output = (
				"You get a "
				+ Stats.getPlayerThreeShield()
				+ " HP Shield."
				+ " Shields have to be destroyed before your HP can be lowered."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getPlayerThreeCD()
				+ " Rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the four ability of the Player. The Ultimate ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityFour() {
		String output = (
				"Your MaxHealth increases by "
				+ Stats.getPlayerFourIncreaseHP()
				+ " HP."
				+ " Remember that your current HP doesn't get affected."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getPlayerFourCD()
				+ " Rounds."
				+ " The cooldown is active when the game begins."
				);
		
		return output;
	}
}