/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

/**
 * The enemy and it's abilities.
 * A few enemy specific methods that the Player shouldn't have.
 * @author	Raffael Gyr
 * @version	1.0
 */
public class Enemy extends Dummy {
	/**
	 * constructor. Same as the conostructor of the Dummy.
	 * @param	startingHealth	the maxHP and current HP at the start of the game
	 * @param	cooldown1		the cooldown of ability 1
	 * @param	cooldown2		the cooldown of ability 2
	 * @param	cooldown3		the cooldown of ability 3
	 * @param	cooldown4		the cooldown of ability 4
	 */
	protected Enemy(int startingHealth, int cooldown1, int cooldown2, int cooldown3, int cooldown4) {
		super(startingHealth, cooldown1, cooldown2, cooldown3, cooldown4);
	}
	
	/**
	 * the first ability of the enemy. The heal ability.
	 */
	protected void abilityOne() {
		int heal = Useful.percentageRounded(this.getMaxHealth(), Stats.getEnemyOneHealprcnt());
		this.heal(heal);
		this.setCurrentCD1(Stats.getEnemyOneCD());
	}
	
	/**
	 * the second ability of the enemy. The damage ability.
	 * @param	player	the Dummy that is supposed to take the damage
	 * @return	the Dummy that took the damage
	 */
	protected Dummy abilityTwo(Dummy player) {
		int damage = Useful.percentageRounded(player.getMaxHealth(), Stats.getEnemyTwoDmgpercent());
		player.takeDamage(damage);
		this.setCurrentCD2(Stats.getEnemyTwoCD());
		
		return player;
	}
	
	/**
	 * the third ability of the enemy. The shield ability.
	 */
	protected void abilityThree() {
		this.addShield(Stats.getEnemyThreeShield());
		this.setCurrentCD3(Stats.getEnemyThreeCD());
	}
	
	/**
	 * the fourth ability of the enemy. The ultimate ability.
	 */
	protected void abilityFour() {
		this.reduceCooldowns(2);
		this.setCurrentCD4(Stats.getEnemyFourCD());
	}
	
	/**
	 * returns general information about the enemy
	 * @return	the information as a String.
	 */
	protected String generalInformation() {
		String output = (
				"Your enemy has done something very bad to you but you don't remember what it was."
				+ " The only thing you remember is that you have to kill him." + "\n"
				+ "Let me give you some stats:" + "\n"
				+ "maxHP: " + this.getMaxHealth() + "\n"
				+ "currentHP: " + this.getCurrentHealth() + "\n"
				+ "Shield strength: " + this.getShield() + " HP" + "\n"
				+ "Damage: not constant." + "\n"
				+ "Eyecolor: Bl... oh wait that doesn't help you. You can't talk anyway."
				);
		
		return output;
	}
	
	/**
	 * returns information about the attack of the enemy.
	 * @return	the information as a String.
	 */
	protected String infoAttack() {
		/*
		 * 	(own maxHealth != own currentHealth) {
		 * 		(damageDealt > 20 && own currentHealth > 30) {
		 * 			90% of damageDealt
		 * 		} (own currentHealth <= 30) {
		 * 			120% of damageDealt
		 * 		} {
		 * 			20 damage
		 * 		}
		 * 	}
		 */
		String output = (
				"If the enemy is full life, he won't attack at all." + "\n"
				+ "if the damage dealt is larger than " + Stats.getEnemyMinDmgTaken() + " and the currentHealth of the enemy is larger than " + Stats.getEnemyRageHealth() + ", the enemy will attack with " + Stats.getEnemyCalmDmgprcnt() + "% of the damage dealt." + "\n"
				+ "if the enemy's currentHealth is lower or equal to " + Stats.getEnemyRageHealth() + ", he will be enraged and deal " + Stats.getEnemyRageDmgprcnt() + "% of the damage dealt." + "\n"
				+ "else the enemy will just deal " + Stats.getEnemyAltDamage() + " damage."
				);
		
		return output;
	}
	
	/**
	 * returns information about the first ability of the Enemy. The Heal ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityOne() {
		String output = (
				"The enemy heals himself by "
				+ Stats.getEnemyOneHealprcnt()
				+ "% of his own maxHealth."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getEnemyOneCD()
				+ " rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the second ability of the Enemy. The Damage ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityTwo() {
		String output = (
				"The enemy deals you "
				+ Stats.getEnemyTwoDmgpercent()
				+ "% of your own health as damage."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getEnemyTwoCD()
				+ " rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the third ability of the Enemy. The Shield ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityThree() {
		String output = (
				"The enemy gets a "
				+ Stats.getEnemyThreeShield()
				+ " HP Shield."
				+ " Shields have to be destroyed before your HP can be lowered."
				+ "\n"
				+ "This ability has a cooldown of "
				+ Stats.getEnemyThreeCD()
				+ " rounds."
				);
		
		return output;
	}
	
	/**
	 * returns information about the fourth ability of the Enemy. The ultimate ability.
	 * @return	the information as a String.
	 */
	protected String infoAbilityFour() {
		String output = (
				"The enemy decreases the cooldowns of all of his abilities except this one by "
				+ Stats.getEnemyFourCDReduction()
				+ " rounds."
				+ "\n"
				+ "This ability has a cooldown of " 
				+ Stats.getEnemyFourCD()
				+ " rounds."
				+ " The cooldown is active when the game begins."
				);
		
		return output;
	}
}