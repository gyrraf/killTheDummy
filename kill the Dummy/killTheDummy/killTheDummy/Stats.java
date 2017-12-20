/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

/**
 * Statistics and Information about the fight and it's participants. Especially useful because it makes the process of balancing easier.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	26.11.2017	(dd.mm.yyyy)
 */
class Stats extends Object {
	//Player
	private static final int playerStartHP		= 100;
	//abilities
	private static final int playerOneCD		= 4,	playerOneHealprcnt		= 20;
	private static final int playerTwoCD		= 3,	playerTwoDmgpercent		= 20;
	private static final int playerThreeCD		= 6,	playerThreeShield		= 50;
	private static final int playerFourCD		= 10,	playerFourIncreaseHP	= 50;
	//attack
	private static final int playerMinDamage1	= 10,	playerMinDamage2		= 20;
	//check the Fight.playerAttack() method for more balance changes on the attack
	
	//Enemy
	//abilities
	private static final int enemyOneCD			= 6,	enemyOneHealprcnt		= 35;
	private static final int enemyTwoCD			= 5,	enemyTwoDmgpercent		= 20;
	private static final int enemyThreeCD		= 4,	enemyThreeShield		= 30;
	private static final int enemyFourCD		= 12,	enemyFourCDReduction	= 2;
	//attack
	private static final int enemyCalmDmgprcnt	= 90,	enemyRageDmgprcnt		= 120,	enemyAltDamage	= 20;
	private static final int enemyRageHealth	= 30,	enemyMinDmgTaken		= 20;
	//check the Fight.enemyAttack() method for more balance changes on the attack
	
	//there shouldn't be any objects of this class.
	@SuppressWarnings("unused")
	private Stats stats = new Stats();
	/**
	 * constructor. It's private because there shouldn't be any objects of this class.
	 */
	private Stats() {
	}
	
	/**
	 * @return the playerStartHP
	 */
	protected static int getPlayerStartHP() {
		return playerStartHP;
	}
	/**
	 * @return the playerOneCD
	 */
	protected static int getPlayerOneCD() {
		return playerOneCD;
	}
	/**
	 * @return the playerOneHealprcnt
	 */
	protected static int getPlayerOneHealprcnt() {
		return playerOneHealprcnt;
	}
	/**
	 * @return the playerTwoCD
	 */
	protected static int getPlayerTwoCD() {
		return playerTwoCD;
	}
	/**
	 * @return the playerTwoDmgpercent
	 */
	protected static int getPlayerTwoDmgpercent() {
		return playerTwoDmgpercent;
	}
	/**
	 * @return the playerThreeCD
	 */
	protected static int getPlayerThreeCD() {
		return playerThreeCD;
	}
	/**
	 * @return the playerThreeShield
	 */
	protected static int getPlayerThreeShield() {
		return playerThreeShield;
	}
	/**
	 * @return the playerFourCD
	 */
	protected static int getPlayerFourCD() {
		return playerFourCD;
	}
	/**
	 * @return the playerFourIncreaseHP
	 */
	protected static int getPlayerFourIncreaseHP() {
		return playerFourIncreaseHP;
	}
	/**
	 * @return the playermindamage1
	 */
	protected static int getPlayerMinDamage1() {
		return playerMinDamage1;
	}
	/**
	 * @return the playermindamage2
	 */
	protected static int getPlayerMinDamage2() {
		return playerMinDamage2;
	}
	/**
	 * @return the enemyOneCD
	 */
	protected static int getEnemyOneCD() {
		return enemyOneCD;
	}
	/**
	 * @return the enemyOneHealprcnt
	 */
	protected static int getEnemyOneHealprcnt() {
		return enemyOneHealprcnt;
	}
	/**
	 * @return the enemyTwoCD
	 */
	protected static int getEnemyTwoCD() {
		return enemyTwoCD;
	}
	/**
	 * @return the enemyTwoDmgpercent
	 */
	protected static int getEnemyTwoDmgpercent() {
		return enemyTwoDmgpercent;
	}
	/**
	 * @return the enemyThreeCD
	 */
	protected static int getEnemyThreeCD() {
		return enemyThreeCD;
	}
	/**
	 * @return the enemyThreeShield
	 */
	protected static int getEnemyThreeShield() {
		return enemyThreeShield;
	}
	/**
	 * @return the enemyFourCD
	 */
	protected static int getEnemyFourCD() {
		return enemyFourCD;
	}
	/**
	 * @return the enemyFourCDReduction
	 */
	protected static int getEnemyFourCDReduction() {
		return enemyFourCDReduction;
	}
	/**
	 * @return the enemycalmdmgprcnt
	 */
	protected static int getEnemyCalmDmgprcnt() {
		return enemyCalmDmgprcnt;
	}
	/**
	 * @return the enemyragedmgprcnt
	 */
	protected static int getEnemyRageDmgprcnt() {
		return enemyRageDmgprcnt;
	}
	/**
	 * @return the enemyaltdamage
	 */
	protected static int getEnemyAltDamage() {
		return enemyAltDamage;
	}
	/**
	 * @return the enemyragehealth
	 */
	protected static int getEnemyRageHealth() {
		return enemyRageHealth;
	}
	/**
	 * @return the enemymindmgtaken
	 */
	protected static int getEnemyMinDmgTaken() {
		return enemyMinDmgTaken;
	}
}