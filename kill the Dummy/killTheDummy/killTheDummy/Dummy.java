/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

/**
 * A dummy with certain amounts of health, maxHealth, shield and 4 abilities, that can change it's stats.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017	(dd.mm.yyyy)
 */
abstract class Dummy extends Object {
	private int	maxHealth,	currentHealth;
	private int	shield;
	private int	cooldown1,	cooldown2,	cooldown3,	cooldown4;
	private int	currentCD1,	currentCD2,	currentCD3,	currentCD4;
	
	/**
	 * creates a character with certain properties
	 * @param	startingHealth	the maxHP and current HP at the start of the game
	 * @param	cooldown1		the cooldown of ability 1
	 * @param	cooldown2		the cooldown of ability 2
	 * @param	cooldown3		the cooldown of ability 3
	 * @param	cooldown4		the cooldown of ability 4
	 */
	protected Dummy(int startingHealth, int cooldown1, int cooldown2, int cooldown3, int cooldown4) {
		this.setMaxHealth(startingHealth);
		this.setCurrentHealth(startingHealth);
		this.setCooldown1(cooldown1);
		this.setCooldown2(cooldown2);
		this.setCooldown3(cooldown3);
		this.setCooldown4(cooldown4);
		this.setCurrentCD1(0);
		this.setCurrentCD2(0);
		this.setCurrentCD3(0);
		this.setCurrentCD4(cooldown4);
		this.setShield(0);
	}
	
	/**
	 * checks if the Dummy is Dead or not. true = dead, false = alive
	 * @return	whether or not the Dummy is dead.
	 */
	protected boolean isDead() {
		if (currentHealth <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * makes the dummy take damage
	 * @param	amount	the amount of damage the Dummy should take
	 */
	protected void takeDamage(int amount) {
		if (this.getShield() > 0) {
			if (this.getShield() > amount) {
				this.damageShield(amount);
				amount = 0;
			} else {
				amount = amount - this.getShield();
				this.setShield(0);
			}
		}
		
		if (amount > 0) {
			this.lowerCurrentHP(amount);
		}
	}
	
	/**
	 * heals the dummy
	 * @param	amount	the amount of HP that should be healed
	 */
	protected void heal(int amount) {
		this.increaseCurrentHP(amount);
	}
	
	/**
	 * lowers the currentHealth by 'amount'
	 * @param	amount	the amount by which the 'currentHealth' should be lowered
	 */
	private void lowerCurrentHP(int amount) {
		this.setCurrentHealth(this.getCurrentHealth() - amount);
	}
	
	/**
	 * increases the currentHealth by 'amount'. Doesn't go higher than 'maxHealth'
	 * @param	amount	the amount by which the 'currentHealth' should be increased
	 */
	private void increaseCurrentHP(int amount) {
		if (this.getCurrentHealth() + amount < this.getMaxHealth()) {
			this.setCurrentHealth(this.getCurrentHealth() + amount);
		} else {
			this.setCurrentHealth(this.getMaxHealth());
		}
	}
	
	/**
	 * increase your shield strength by 'amount'
	 * @param	amount	the amount of shieldHP that are supposed to be added
	 */
	protected void addShield(int amount) {
		this.setShield(this.getShield() + amount);
	}
	
	/**
	 * lower your shield strength by 'amount'
	 * @param	amount	the amount of shieldHP that are supposed to be removed
	 */
	private void damageShield(int amount) {
		if (this.getShield() - amount <= 0) {
			this.setShield(0);
		} else {
			this.setShield(this.getShield() - amount);
		}
	}
	
	/**
	 * increases your maxHealth by 'amount'
	 * @param	amount	the amount of maxHealth that is supposed to be added
	 */
	protected void addMaxHealth(int amount) {
		this.setMaxHealth(this.getMaxHealth() + amount);
	}
	
	/**
	 * reduces the cooldown of ability1 by 'amount'
	 * @param	amount	the amount by which the cooldown of ability1 is supposed to be reduced
	 */
	private void reduceCooldown1(int amount) {
		if (this.getCurrentCD1() - amount <= 0) {
			this.setCurrentCD1(0);
		} else {
			this.setCurrentCD1(this.getCurrentCD1() - amount);
		}
	}
	
	/**
	 * reduces the cooldown of ability2 by 'amount'
	 * @param	amount	the amount by which the cooldown of ability2 is supposed to be reduced
	 */
	private void reduceCooldown2(int amount) {
		if (this.getCurrentCD2() - amount <= 0) {
			this.setCurrentCD2(0);
		} else {
			this.setCurrentCD2(this.getCurrentCD2() - amount);
		}
	}
	
	/**
	 * reduces the cooldown of ability3 by 'amount'
	 * @param	amount	the amount by which the cooldown of ability3 is supposed to be reduced
	 */
	private void reduceCooldown3(int amount) {
		if (this.getCurrentCD3() - amount <= 0) {
			this.setCurrentCD3(0);
		} else {
			this.setCurrentCD3(this.getCurrentCD3() - amount);
		}
	}
	
	/**
	 * reduces the cooldown of ability4 by 'amount'
	 * @param	amount	the amount by which the cooldown of ability4 is supposed to be reduced
	 */
	private void reduceCooldown4(int amount) {
		if (this.getCurrentCD4() - amount <= 0) {
			this.setCurrentCD4(0);
		} else {
			this.setCurrentCD4(this.getCurrentCD4() - amount);
		}
	}
	
	/**
	 * reduce the cooldowns of all abilities by 'amount'
	 * @param	amount	the amount by which the cooldown of all abilities is supposed to be reduced
	 */
	protected void reduceCooldowns(int amount) {
		this.reduceCooldown1(amount);
		this.reduceCooldown2(amount);
		this.reduceCooldown3(amount);
		this.reduceCooldown4(amount);
	}
	
	/**
	 * gives the current statistics of the Dummy
	 * @return	the information as a String
	 */
	protected String getStatistics() {
		String output =
				"Current Health: " + this.getCurrentHealth() + " HP" + "\n"
				+ "Max Health:" + this.getMaxHealth() + " HP" + "\n";
		
		if (this.getShield() > 0) {
			output = output
					+ "Shield:" + this.getShield() + " HP" + "\n";
		}
		
		output = output
				+ "Cooldown 1:" + this.getCurrentCD1() + " rounds" + "\n"
				+ "Cooldown 2:" + this.getCurrentCD2() + " rounds" + "\n"
				+ "Cooldown 3:" + this.getCurrentCD3() + " rounds" + "\n"
				+ "Cooldown 4:" + this.getCurrentCD4() + " rounds";
		
		return output;
	}

	/**
	 * @return	the maxHealth
	 */
	protected int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @param	maxHealth	the maxHealth to set
	 */
	private void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @return	the currentHealth
	 */
	protected int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * @param	currentHealth	the currentHealth to set
	 */
	protected void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	/**
	 * @return	the cooldown1
	 */
	protected int getCooldown1() {
		return cooldown1;
	}
	
	/**
	 * @param	cooldown1	the cooldown1 to set
	 */
	private void setCooldown1(int cooldown1) {
		this.cooldown1 = cooldown1;
	}
	
	/**
	 * @return	the cooldown2
	 */
	protected int getCooldown2() {
		return cooldown2;
	}

	/**
	 * @param	cooldown2	the cooldown2 to set
	 */
	private void setCooldown2(int cooldown2) {
		this.cooldown2 = cooldown2;
	}

	/**
	 * @return the cooldown3
	 */
	protected int getCooldown3() {
		return cooldown3;
	}

	/**
	 * @param	cooldown3	the cooldown3 to set
	 */
	private void setCooldown3(int cooldown3) {
		this.cooldown3 = cooldown3;
	}

	/**
	 * @return the cooldown4
	 */
	protected int getCooldown4() {
		return cooldown4;
	}

	/**
	 * @param	cooldown4	the cooldown4 to set
	 */
	private void setCooldown4(int cooldown4) {
		this.cooldown4 = cooldown4;
	}

	/**
	 * @return	the currentCD1
	 */
	protected int getCurrentCD1() {
		return currentCD1;
	}

	/**
	 * @param	currentCD1	the currentCD1 to set
	 */
	protected void setCurrentCD1(int currentCD1) {
		this.currentCD1 = currentCD1;
	}

	/**
	 * @return	the currentCD2
	 */
	protected int getCurrentCD2() {
		return currentCD2;
	}

	/**
	 * @param	currentCD2	the currentCD2 to set
	 */
	protected void setCurrentCD2(int currentCD2) {
		this.currentCD2 = currentCD2;
	}

	/**
	 * @return	the currentCD3
	 */
	protected int getCurrentCD3() {
		return currentCD3;
	}

	/**
	 * @param	currentCD3	the currentCD3 to set
	 */
	protected void setCurrentCD3(int currentCD3) {
		this.currentCD3 = currentCD3;
	}

	/**
	 * @return	the currentCD4
	 */
	protected int getCurrentCD4() {
		return currentCD4;
	}

	/**
	 * @param	currentCD4	the currentCD4 to set
	 */
	protected void setCurrentCD4(int currentCD4) {
		this.currentCD4 = currentCD4;
	}

	/**
	 * @return	the shield
	 */
	protected int getShield() {
		return shield;
	}

	/**
	 * @param	shield	the shield to set
	 */
	private void setShield(int shield) {
		this.shield = shield;
	}
	
	/**
	 * the first ability of the Dummy: heal
	 */
	protected abstract void abilityOne();
	
	/**
	 * the second ability of the Dummy: damage
	 * @param	dummy	the Dummy that is supposed to take the damage
	 * @return	the Dummy that took the damage
	 */
	protected abstract Dummy abilityTwo(Dummy dummy);
	
	/**
	 * the third ability of the Dummy: shield
	 */
	protected abstract void abilityThree();
	
	/**
	 * the fourth ability of the Dummy: ultimate
	 */
	protected abstract void abilityFour();
	
	/**
	 * gives general information about the Dummy
	 * @return	the information as a String.
	 */
	protected abstract String generalInformation();
	
	/**
	 * gives Information about the attack
	 * @return	the information as a String
	 */
	protected abstract String infoAttack();
	
	/**
	 * gives information about the first ability: heal
	 * @return	the information as a String
	 */
	protected abstract String infoAbilityOne();
	
	/**
	 * gives information about the second ability: damage
	 * @return	the information as a String
	 */
	protected abstract String infoAbilityTwo();
	
	/**
	 * gives information about the third ability: shield
	 * @return	the information as a String
	 */
	protected abstract String infoAbilityThree();
	
	/**
	 * gives information about the fourth ability: ultimate
	 * @return	the information as a String
	 */
	protected abstract String infoAbilityFour();
}