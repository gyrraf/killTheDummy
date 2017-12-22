/**
 * A textbased, turnbased game where you try to kill the enemy before the enemy kills you.
 * @author	Raffael Gyr
 * @version	1.0
 * @since	05.11.2017
 */
package killTheDummy;

import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import java.util.Scanner;

/**
 * The fight of the player against the Dummy
 * @author	Raffael Gyr
 * @since	05.11.2017		(dd.mm.yyyy)
 * @version	1.0
 */
public class Fight extends Object {
	
	private static Scanner	in				= new Scanner(System.in);
	private static Dummy	player;
	private static Dummy	enemy;
	private static int		damageDealt;
	private static boolean	suicide;
	private static String	contactDetails	= "Raffael Gyr" + "\n"
												+ "raffael.gyr@hotmail.com";
	
	//there shouldn't be any objects of this class.
	private static Fight fight = new Fight();
	/**
	 * constructor. It's private because there shouldn't be any objects of this class.
	 */
	private Fight() {
	}
	
	/**
	 * The main method
	 * @param	args	the input the user started the program with
	 */
	public static void main(String[] args) {
		//make 'args' useless
		args = null;
		
		//the game
		game();
		
		//close everything and remove saved objects
		close();
	} //End of main method
	
	/**
	 * The actual game
	 */
	private static void game() {
		suicide = false;
		
		instructions();
		player = new Player(
				Stats.getPlayerStartHP(),
				Stats.getPlayerOneCD(),
				Stats.getPlayerTwoCD(),
				Stats.getPlayerThreeCD(),
				Stats.getPlayerFourCD()
				);
		
		createEnemy();
		
		//The game should go on until someone is dead
		while (!((player.isDead()) || (enemy.isDead()))) {
			damageDealt = 0;
			
			//The players turn
			boolean moveMade = false;
			while (!(moveMade)) {
				
				//ask what to do
				System.out.println("What do you want to do?");
				System.out.println("Use the following commands:");
				System.out.println("'info':\t		to get Information about something.");
				System.out.println("'stats':		to display statistics.");
				System.out.println("'ability':\t	to use an ability.");
				System.out.println("'attack':		to use a normal attack.");
				System.out.println("'nothing':\t	to do nothing and skip your turn.");
				System.out.println("'surrender':\t	to give up.");
				
				//input
				String input;
				try {
					input = in.nextLine();
				} catch (Exception e) {
					input = " ";
				}
				
				/*
				 * it's supposed to be allowed to enter 'ability 1' which would execute ability1 immediatly.
				 * the same goes for 'attack'
				 */
				//split Strings
				String[] temp;
				try {
					temp = input.split(" ");
				} catch (PatternSyntaxException ex) {
					temp = new String[2];
				}
				String[] inputArr = new String[2];
				if (temp.length >= 1) {
					if (temp[0] != null) {
						inputArr[0] = temp[0];
					}
				}
				if (temp.length >= 2) {
					if (temp[1] != null) {
						inputArr[1] = temp[1];
					}
				}
				
				//get the number
				int number = 0;
				if (inputArr[1] != null) {
					try {
						number = Integer.parseInt(inputArr[1]);
					} catch (NumberFormatException e) {
						inputArr[0] = "invalid";
						number = 0;
					}
				}
				
				//verify input
				while (!((inputArr[0].equals("info"))
						|| ((inputArr[0].equals("ability") && (number <= 4) && (number > 0)))
						|| ((inputArr[0].equals("attack") && (number > 0)))
						|| (inputArr[0].equals("nothing"))
						|| (inputArr[0].equals("surrender"))
						|| (inputArr[0].equals("stats")))) {
					//output
					System.out.println("Invalid input.");
					System.out.println("Please use one of the following commands:");
					System.out.println("'info':\t		to get Information about something.");
					System.out.println("'stats':		to display statistics.");
					System.out.println("'ability':\t	to use an ability.");
					System.out.println("'attack':		to use a normal attack.");
					System.out.println("'nothing':\t	to do nothing and skip your turn.");
					System.out.println("'surrender':\t	to give up.");
					
					//input
					try {
						input = in.nextLine();
					} catch (Exception e) {
						input = " ";
					}
					
					/*
					 * it's supposed to be allowed to enter 'ability 1' which would execute ability1 immediatly.
					 * the same goes for 'attack'
					 */
					//split Strings
					try {
						temp = input.split(" ");
					} catch (PatternSyntaxException ex) {
						temp = new String[2];
					}
					inputArr = new String[2];
					if (temp.length >= 1) {
						if (temp[0] != null) {
							inputArr[0] = temp[0];
						}
					}
					if (temp.length >= 2) {
						if (temp[1] != null) {
							inputArr[1] = temp[1];
						}
					}
					
					//get the number
					number = 0;
					if (inputArr[1] != null) {
						try {
							number = Integer.parseInt(inputArr[1]);
						} catch (NumberFormatException e) {
							inputArr[0] = "invalid";
							number = 0;
						}
					}
				}
				
				//perform action
				switch (inputArr[0]) {
					case "info":
						info();
						break;
					case "ability":
						if (number != 0) {
							moveMade = playerAbility(number);
						} else {
							moveMade = playerAbility();
						}
						break;
					case "attack":
						if (number != 0) {
							playerAttack(number);
						} else {
							playerAttack();
						}
						moveMade = true;
						break;
					case "nothing":
						nothing();
						moveMade = true;
						break;
					case "surrender":
						surrender();
						moveMade = true;
						break;
					case "stats":
						displayStatistics();
						break;
					default:
						break;
				}
			}
			
			if (!suicide) {
				displayStatistics();
			}
			
			//The players turn is over
			
			if (!((player.isDead()) || (enemy.isDead()))) {
				/*
				 * The players turn is over
				 * if not already done display the health of the enemy
				 * 
				 * The enemy's turn starts
				 * if not already done display the health of the player
				 */
				//enemy's turn
				
				if ((damageDealt == 0)
						&& (enemy.getCurrentHealth() == enemy.getMaxHealth())
						&& (enemy.getShield() == 0)) {
					//Enemy doesn't attack
					System.out.println("Your enemy hasn't been enraged yet. He just stares at you.");
				} else {
					//Enemy attacks
					enemyTurn();
				}
				
				in.nextLine();
				
				decreaseCooldowns();
				displayStatistics();
			}
		}
		
		/*
		 * game is over
		 * 
		 * print winner
		 */
		//gameover
		
		theEnd();
	} //End of the method 'game(): void'
	
	/**
	 * print instructions
	 */
	private static void instructions() {
		System.out.println("You are playing a game in which you try to kill a dummy.");
		System.out.println("You have "
				+ Stats.getPlayerStartHP()
				+ " hp yourself but you can choose how much hp the dummy should have.");
		System.out.println("The more hp you give to the dummy, the harder it is to win.");
		
		in.nextLine();
		
		System.out.println("Each turn you can choose between four abilities,"
				+ " each with it's own cooldown, a normal attack or doing nothing at all.");
		System.out.println("You can also surrender at any time.");
		System.out.println("Good luck and have fun.");
		in.nextLine();
	} //End of method 'instructions(): void'

	/**
	 * Set up the Enemy
	 */
	private static void createEnemy() {
		System.out.println("How much health should your enemy have?");
		int health;
		
		try {
			health = Integer.parseInt(in.nextLine());
		} catch (Exception e) {
			System.out.println("something went wrong with your input. Your enemy will get " 
					+ Stats.getPlayerStartHP()
					+ " hp too.");
			
			health = Stats.getPlayerStartHP();
		}
		
		enemy = new Enemy(
				health,
				Stats.getEnemyOneCD(),
				Stats.getEnemyTwoCD(),
				Stats.getEnemyThreeCD(),
				Stats.getEnemyFourCD()
				);
	} //End of method 'createEnemy(): void'

	/**
	 * lets the player attack the enemy.
	 */
	private static void playerAttack() {
		/*
		 * attack the enemy
		 * let the player decide how much damage to deal
		 */
		
		//Ask how much damage he wants to deal
		System.out.println("How much damage do you want to deal to your enemy?");
		
		//input
		int damage = 0;
		boolean incorrect = true;
		while (incorrect) {
			try {
				damage = Integer.parseInt(in.nextLine());
				incorrect = false;
			} catch (NumberFormatException nfEx) {
				System.out.println("The input wasn't an Integer. Please try again.");
				incorrect = true;
			}
			
			if (damage < 0) {
				System.out.println("You can't deal negative damage. Please input a positive number.");
				incorrect = true;
			}
		}
		
		playerAttack(damage);
	} //End of method 'playerAttack(): void'
	
	/**
	 * lets the player attack the enemy.
	 * @param	damage	the damage that's supposed to be dealt
	 */
	private static void playerAttack(int damage) {
		/*
		 * if he enters a number too large, the attack fails and hits the player himself or just misses
		 * 
		 * 0 <= damage gets dealt < 1/3 enemy currentHealth but minimum 10
		 * 1/3 enemy currentHealth but minimum 10 <= no damage dealt < 2/3 enemy currentHealth but minimum 20
		 * 2/3 enemy currentHealth but minimum 20 <= damage yourself
		 */
		
		//TODO if balance is bad. Change this.
		//check which of the three possibilities apply
		if ((damage < Useful.divisionRounded(enemy.getCurrentHealth(), 3))
				|| (damage < Stats.getPlayerMinDamage1())) {
			//damage gets dealt
			enemy.takeDamage(damage);
			damageDealt = damage;
			
			System.out.println("You dealt " + damage + " damage to your enemy.");
			
			if (enemy.isDead()) {
				System.out.println("This hit killed your enemy.");
			}
		} else if ((damage < Useful.divisionRounded(2 * enemy.getCurrentHealth(), 3))
				|| (damage < Stats.getPlayerMinDamage2())) {
			//nothing happens
			damageDealt = 0;
			
			System.out.println("You tried to hit so hard that you missed your target and wasted your turn.");
		} else {
			//player damages himself
			//comment on him one shotting and/or killing himself
			
			boolean fullHP = false;
			if (player.getCurrentHealth() == player.getMaxHealth()) {
				fullHP = true;
			}
			
			boolean noShield = false;
			if (player.getShield() <= 0) {
				noShield = true;
			}
			
			boolean startingHP = false;
			if (player.getMaxHealth() == Stats.getPlayerStartHP()) {
				startingHP = true;
			}
			
			player.takeDamage(damage);
			damageDealt = 0;
			
			System.out.println("You tried to land the hardest punch ever but only damaged yourself.");
			
			if (player.isDead()) {
				suicide = true;
				if (fullHP) {
					if (startingHP && noShield) {
						System.out.println("You managed to oneshot yourself before increasing your HP or getting a shield.");
					} else if (!noShield) {
						System.out.println("You managed to oneshot yourself through your shield.");
					} else {
						System.out.println("You managed to oneshot yourself.");
					}
				} else {
					System.out.println("You accidentally commited suicide.");
				}
			}
		}
		
		in.nextLine();
		System.out.println("\n");
	} //End of method 'playerAttack(int): void'

	/**
	 * asks which ability is supposed to be used
	 * @return	true = ability used, false = ability on cooldown.
	 */
	private static boolean playerAbility() {
		/*
		 * ask which ability the user wants to use
		 * check cooldown
		 * if successful return true if unsuccessful return false
		 */
		
		//Which ability does the user want to use?
		
		System.out.println("Which ability do you want to use?");
		System.out.println("Use the numbers 1-4 for your selection.");
		
		int ability = 0;
		
		while (ability == 0) {
			try {
				ability = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				ability = 0;
			}
			
			if (!((ability == 1) || (ability == 2) || (ability == 3) || (ability == 4))) {
				ability = 0;
				System.out.println("That ability doesn't exist.");
				System.out.println("Use the numbers 1-4 for your selection.");
			}
		}
		
		return playerAbility(ability);
	} //End of method 'playerAbility(): boolean'
	
	/**
	 * uses the ability if it isn't on cooldown.
	 * @return	true = ability used, false = ability on cooldown.
	 * @param	ability	the ability that is supposed to be used.
	 */
	private static boolean playerAbility(int ability) {
		/*
		 * check cooldown
		 * if successful return true if unsuccessful return false
		 */
		
		//Check cooldown & use ability if not on cooldown
		switch (ability) {
			case 1:
				//Ability one
				if (player.getCurrentCD1() > 0) {
					System.out.println("Ability 1 is on cooldown for " + player.getCurrentCD1() + " rounds.");
					in.nextLine();
					return false;
				} else {
					int HPBefore = player.getCurrentHealth();
					player.abilityOne();
					int difference = player.getCurrentHealth() - HPBefore;
					System.out.println("You healed " + difference + " HP.");
					in.nextLine();
					return true;
				}
			case 2:
				//Ability Two
				if (player.getCurrentCD2() > 0) {
					System.out.println("Ability 2 is on cooldown for " + player.getCurrentCD2() + " rounds.");
					in.nextLine();
					return false;
				} else {
					int before = enemy.getCurrentHealth() + enemy.getShield();
					enemy = player.abilityTwo(enemy);
					int after = enemy.getCurrentHealth() + enemy.getShield();
					damageDealt = before - after;
					System.out.println("you dealt " + damageDealt + " damage to your enemy.");
					in.nextLine();
					return true;
				}
			case 3:
				//Ability three
				if (player.getCurrentCD3() > 0) {
					System.out.println("Ability 3 is on cooldown for " + player.getCurrentCD3() + " rounds.");
					in.nextLine();
					return false;
				} else {
					int shieldBefore = player.getShield();
					player.abilityThree();
					int difference = player.getShield() - shieldBefore;
					System.out.println("Your Shield strength increased by " + difference + " HP.");
					in.nextLine();
					return true;
				}
			case 4:
				//Ability four
				if (player.getCurrentCD4() > 0) {
					System.out.println("Ability 4 is on cooldown for " + player.getCurrentCD4() + " rounds.");
					in.nextLine();
					return false;
				} else {
					int maxHPBefore = player.getMaxHealth();
					player.abilityFour();
					int difference = player.getMaxHealth() - maxHPBefore;
					System.out.println("Your MaxHealth increased by " + difference + " HP.");
					in.nextLine();
					return true;
				}
			default:
				return false;
		}
	} //End of method 'playerAbility(int): boolean'

	/**
	 * Lets the player do nothing
	 */
	private static void nothing() {
		if (player.getCurrentHealth() == player.getMaxHealth()) {
			System.out.println("You did nothing. Amazing.");
		} else {
			System.out.println("You looked at your wounds. It started to hurt and you did nothing.");
		}
		
		in.nextLine();
	} //End of method 'nothing(): void'

	/**
	 * lets the player surrender and outputs a bit of funny text.
	 */
	private static void surrender() {
		suicide = true;
		
		//output something depending on the hp left.
		if (player.getCurrentHealth() == player.getMaxHealth()) {
			//full hp
			System.out.println("Your enemy is confused as you go home without any wounds.");
		} else if ((player.getCurrentHealth() >= 100) && (player.getCurrentHealth() != player.getMaxHealth()) && (player.getMaxHealth() > 100)) {
			//100hp or more but not full hp anymore
			System.out.println("You go home to lick your wounds even though you are healthier than when you came here.");
		} else if ((player.getCurrentHealth() < 100) && (player.getCurrentHealth() >= 50)) {
			//50 - 99 hp
			System.out.println("Your wounds really hurt. You limp home.");
		} else if ((player.getCurrentHealth() < 50) && (player.getCurrentHealth() >= 25)) {
			//25 - 49 hp
			System.out.println("Badly wounded you leave the battlefield. You stumble very often. Who knows if you'll ever reach your home.");
		} else if  ((player.getCurrentHealth() < 25) && (player.getCurrentHealth() > 1)) {
			//2 - 24 hp
			System.out.println("With the last of your powers, you crawl home.");
		} else {
			//1 hp
			System.out.println("Your enemy was about to kill you, but in the last second you managed to escape. No one has ever heard of you again.");
		}
		
		System.out.println("\n");
		
		player.setCurrentHealth(0);
		
		in.nextLine();
	} //End of method 'surrender(): void'

	/**
	 * gives the player short information about something
	 * @param selection
	 */
	private static void info() {
		//ask what the player wants information about
		System.out.println("What do you want Information about?");
		System.out.println("Use the following commands:");
		System.out.println("'1':\t				to get information about ability one.");
		System.out.println("'2':\t				to get information about ability two.");
		System.out.println("'3':\t				to get information about ability three.");
		System.out.println("'4':\t				to get information about ability four.");
		System.out.println("'attack':\t			to get information about the attack.");
		System.out.println("'nothing':\t\t		to get information about the option 'nothing'.");
		System.out.println("'enemy':\t			to get information about the enemy.");
		System.out.println("'surrender':\t\t\t	to get information about the option 'surrender'.");
		System.out.println("'player':\t			to get information about yourself.");
		String selection = in.nextLine();
		
		//give information
		switch (selection) {
			case "1":
				//TODO Ability one
				System.out.println(player.infoAbilityOne());
				break;
			case "2":
				//TODO Ability two
				System.out.println(player.infoAbilityTwo());
				break;
			case "3":
				//TODO Ability three
				System.out.println(player.infoAbilityThree());
				break;
			case "4":
				//TODO Ability four
				System.out.println(player.infoAbilityFour());
				break;
			case "attack":
				//TODO Auto Attack
				System.out.println(player.infoAttack());
				break;
			case "enemy":
				enemyInfo();
				break;
			case "nothing":
				System.out.println("You do nothing and skip your turn.");
				System.out.println("I thoght this was obvious.");
				break;
			case "surrender":
				System.out.println("You give up and the enemy wins.");
				System.out.println("There is nothing more to say about this. What did you expect?");
				break;
			case "player":
				System.out.println(player.generalInformation());
				break;
			default:
				System.out.println("I can't give you any Information about " + selection + " because it doesn't exist.");
				break;
		}
		
		System.out.println("\n");
		
		in.nextLine();
	} //End of method 'info(): void'

	/**
	 * Allows the player to get information about the enemy
	 */
	private static void enemyInfo() {
		//give general information
		System.out.println(enemy.generalInformation());
		
		System.out.println("\n");
		
		//Ask what the player wants more information about
		System.out.println("Which part of your enemy do you want to know more about?");
		System.out.println("Use these commands:");
		System.out.println("'pattern':\t\t			to get information about his attack pattern.");
		System.out.println("'attack':\t				to get information about his normal attack.");
		System.out.println("'1':\t 					to get information about his ability one.");
		System.out.println("'2':\t					to get information about his ability two.");
		System.out.println("'3':\t					to get information about his ability three.");
		System.out.println("'4':\t					to get information about his ability four.");
		System.out.println("'nothing':\t\t			to not get any additional information.");
		String selection = in.nextLine();
		
		//give information
		switch (selection) {
			case "pattern":
				//TODO information about the attack pattern of the enemy
				System.out.println("The enemy uses his abilities as often as possible. Only if all of them are on cooldown will he use an attack.");
				System.out.println("The abilities get prioritized in the reverse direction than they are numbered (4, 3, 2, 1) but the heal will only be used if he isn't full health already.");
				break;
			case "attack":
				//TODO information about the standard attack of the enemy
				System.out.println(enemy.infoAttack());
				break;
			case "1":
				//TODO information about the ability one of the enemy
				System.out.println(enemy.infoAbilityOne());
				break;
			case "2":
				//TODO information about the ability two of the enemy
				System.out.println(enemy.infoAbilityTwo());
				break;
			case "3":
				//TODO information about the ability three of the enemy
				System.out.println(enemy.infoAbilityThree());
				break;
			case "4":
				//TODO information about the ability four of the enemy
				System.out.println(enemy.infoAbilityFour());
				break;
			case "nothing":
				System.out.println("If you don't want any additional information. So it shall be.");
				break;
			default:
				System.out.println("I'm sorry but I can't give you any information about " + selection + " because I didn't give you this option.");
				System.out.println("You managed to make an incorrect choice and I will punish you by not giving you any information.");
				break;
		}
	} //End of method 'enemyInfo(): void'

	/**
	 * The enemies turn
	 */
	private static void enemyTurn() {
		//enemy attacks
		/*
		 * check cooldowns and use the abilities in reverse order (4, 3, 2, 1)
		 * NOTE: only use the heal (1) when not full health
		 * 
		 * if nothing is off cooldown: attack
		 */
		
		//check cooldowns and select what happens
		char move = '0'; //0=not changed, 1=ability1, 2=ability2, 3=ability3, 4=ability4, a=attack
		//move not decided yet
		if ((enemy.getCurrentCD4() <= 0)
				&& !((enemy.getCurrentCD3() <= 0)
						&& (enemy.getCurrentCD2() <= 0)
						&& (enemy.getCurrentCD1() <= 0))) {
			move = '4';
		} else if (enemy.getCurrentCD3() <= 0) {
			move = '3';
		} else if (enemy.getCurrentCD2() <= 0) {
			move = '2';
		} else if ((enemy.getCurrentCD1() <= 0)
				&& (enemy.getCurrentHealth() != enemy.getMaxHealth())) {
			move = '1';
		} else {
			move = 'a';
		}
		
		//Execute
		switch (move) {
			case 'a':
				//enemy attacks
				enemyAttack();
				break;
			case '1':
				//ability one
				int HPBeforeOne = enemy.getCurrentHealth();
				enemy.abilityOne();
				int differenceOne = enemy.getCurrentHealth() - HPBeforeOne;
				System.out.println("The enemy healed " + differenceOne + " HP.");
				break;
			case '2':
				//ability 2
				int HPBeforeTwo = player.getCurrentHealth() + player.getShield();
				enemy.abilityTwo(player);
				int HPAfterTwo = player.getCurrentHealth() + player.getShield();
				int differenceTwo = HPBeforeTwo - HPAfterTwo;
				System.out.println("The enemy dealt " + differenceTwo + " damage to you.");
				break;
			case '3':
				//ability 3
				int ShieldBefore = enemy.getShield();
				enemy.abilityThree();
				int differenceThree = enemy.getShield() - ShieldBefore;
				System.out.println("The enemy increased their shield strength by " + differenceThree + " HP.");
				break;
			case '4':
				//ability 4
				enemy.abilityFour();
				System.out.println("The enemy decreased all of their cooldowns by " + Stats.getEnemyFourCDReduction() + " rounds.");
				break;
			default:
				break;
		}
	} //End of method 'enemyTurn(): void'
	
	/**
	 * The normal attack of the enemy
	 */
	private static void enemyAttack() {
		//TODO balance changes
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
		
		if (enemy.getMaxHealth() != enemy.getCurrentHealth()) {
			if ((damageDealt > Stats.getEnemyMinDmgTaken())
					&& (enemy.getCurrentHealth() > Stats.getEnemyRageHealth())) {
				//damage when life is not critical
				player.takeDamage(Useful.percentageRounded(damageDealt, Stats.getEnemyCalmDmgprcnt()));
				System.out.println("The enemy dealt " + Useful.percentageRounded(damageDealt, Stats.getEnemyCalmDmgprcnt()) + " damage to you.");
			} else if (enemy.getCurrentHealth() <= Stats.getEnemyRageHealth()) {
				//damage when life is critical
				player.takeDamage(Useful.percentageRounded(damageDealt, Stats.getEnemyRageDmgprcnt()));
				System.out.println("The enemy dealt " + Useful.percentageRounded(damageDealt, Stats.getEnemyRageDmgprcnt()) + " damage to you.");
			} else {
				//damage otherwise
				player.takeDamage(Stats.getEnemyAltDamage());
				System.out.println("The enemy dealt " + Stats.getEnemyAltDamage() + " damage to you.");
			}
		} else {
			System.out.println("The enemy isn't wounded. He doesn't attack.");
		}
		
	} //End of method 'enemyAttack(): void'
	
	/**
	 * lower cooldowns
	 */
	private static void decreaseCooldowns() {
		player.reduceCooldowns(1);
		enemy.reduceCooldowns(1);
	} //End of method 'decreaseCooldowns(): void'
	
	/**
	 * prints all stats needed
	 */
	private static void displayStatistics() {
		System.out.println(
				"----------------------------------------------------------------------------------" + "\n"
				+ "Statistics" + "\n"
				+ "----------------------------------------------------------------------------------" + "\n"
				);
		
		System.out.println(
				"Player:" + "\n\n" + player.getStatistics() + "\n"
				);
		
		System.out.println(
				"Enemy:" + "\n\n" + enemy.getStatistics() + "\n"
				);
		
		System.out.println( 
				"----------------------------------------------------------------------------------" + "\n"
				+ "----------------------------------------------------------------------------------" + "\n"
				);
		
		in.nextLine();
	} //End of method 'displayStatistics(): void'

	/**
	 * outputs something at the end of the game
	 */
	private static void theEnd() {
		//TODO make more complex. Priority: very low
		if ((player.isDead()) && (enemy.isDead())) {
			//both are dead
			System.out.println("I don't know how you did it, but you both managed to die at the same time.");
			System.out.println("This message shouldn't even appear. If it does and you still remember how you got here,"
					+ " please report the error to me: "
					+ contactDetails);
		} else if (player.isDead()) {
			//player is dead
			int overkill = player.getCurrentHealth() * -1;
			System.out.println("You died.");
			if (overkill != 0) {
				System.out.println("Your enemy dealt " + overkill + " more than he needed to.");
			}
		} else {
			//enemy is dead
			int overkill = enemy.getCurrentHealth() * -1;
			System.out.println("You killed your enemy.");
			if (overkill != 0) {
				System.out.println("You dealt " + overkill + " more than you needed to.");
			}
		}
		
		in.nextLine();
		
		System.out.println("The game is now over. I hope you had fun.");
	} //End of method 'theEnd(): void'
	
	/**
	 * closes everything and removes all objects
	 */
	private static void close() {
		try {
			if (in != null) {
				in.close();
			}
			
			if (System.in != null) {
				System.in.close();
			}
			
			if (System.out != null) {
				System.out.close();
			}
			
			if (player != null) {
				player = null;
			}
			
			if (enemy != null) {
				enemy = null;
			}
			
			if (fight != null) {
				fight = null;
			}
			
			if (damageDealt != 0) {
				damageDealt = 0;
			}
			
			if (!suicide) {
				suicide = true;
			}
			
			if (contactDetails != null) {
				contactDetails = null;
			}
		} catch (IllegalStateException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}
	} //End of method 'close(): void'
} //End of class 'Fight'