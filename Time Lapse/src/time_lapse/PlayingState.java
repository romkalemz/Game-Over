package time_lapse;


import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Image;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


class PlayingState extends BasicGameState {
	private boolean debugMode;
	private int chaser = 1;
	private int runner = 3;
	private int shooter = 2;
	private int arrow_check = 0;
	
	// Animations and spritesheets
	
	// Player idles right
	private SpriteSheet playerRightIdleAni;
	private Animation playerRightIdleAnimation1;
	
	// Player idles left
	private SpriteSheet playerLeftIdleAni;
	private Animation playerLeftIdleAnimation1;
	
	// Player idles up
	private SpriteSheet playerUpIdleAni;
	private Animation playerUpIdleAnimation1;
		
	// Player idles down
	private SpriteSheet playerDownIdleAni;
	private Animation playerDownIdleAnimation1;
	
	// Player moves right
	private SpriteSheet playerRightAni;
	private Animation playerRightAnimation1;
	// Player moves left
	private SpriteSheet playerleftAni;
	private Animation playerLeftAnimation1;
	// Player moves down
	private SpriteSheet playerDownAni;
	private Animation playerDownAnimation1;
	// Player moves up
	private SpriteSheet playerUpAni;
	private Animation playerUpAnimation1;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		Game tl = (Game)game;
		debugMode = false;
		
		// Initialize sprite sheets and animations
		playerRightAni = new SpriteSheet("resources/player_default_right_ani.png",40,40);
		playerRightAnimation1 = new Animation(playerRightAni, 100);
		
		playerleftAni = new SpriteSheet("resources/player_default_left_ani.png",40,40);
		playerLeftAnimation1 = new Animation(playerleftAni, 100);
		
		playerRightIdleAni = new SpriteSheet("resources/player_default_right_idle_ani.png",40,40);
		playerRightIdleAnimation1 = new Animation(playerRightIdleAni, 100);
		
		playerLeftIdleAni = new SpriteSheet("resources/player_default_left_idle_ani.png",40,40);
		playerLeftIdleAnimation1 = new Animation(playerLeftIdleAni, 100);
		
		playerDownAni = new SpriteSheet("resources/player_default_down_ani.png",40,40);
		playerDownAnimation1 = new Animation(playerDownAni, 100);
		 
		playerUpAni = new SpriteSheet("resources/player_default_up_ani.png",40,40);
		playerUpAnimation1 = new Animation(playerUpAni, 100);
		
		playerUpIdleAni = new SpriteSheet("resources/player_default_up_idle_ani.png",40,40);
		playerUpIdleAnimation1 = new Animation(playerUpIdleAni, 100);
		
		playerDownIdleAni = new SpriteSheet("resources/player_default_down_idle_ani.png",40,40);
		playerDownIdleAnimation1 = new Animation(playerDownIdleAni, 100);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		Game tl = (Game)game;
		Animation curr_ani = null;
		// render entities
		tl.map.renderMap(g);
		if(debugMode)
			tl.debug.renderDebug(g, tl);
		if(tl.player.getDirection() == 0) {
			//tl.player.render(g);
		}
		
		// render enemies
		if(!tl.enemy.isEmpty()) {
			for(int i = 0; i < tl.enemy.size(); i++) {
				tl.enemy.get(i).render(g);
			}
		}
		// render items
		if(!tl.items.isEmpty()) {
			for(int i = 0; i < tl.items.size(); i++) {
				tl.items.get(i).render(g);
			}
		}
		// render projectiles
		if(!tl.projectiles.isEmpty()) {
			for (int i = 0; i <tl.projectiles.size(); i++) {
				tl.projectiles.get(i).render(g);
			}
		}
		//render doors and door switches
		if(!tl.doors.isEmpty()) {
			for (int i = 0; i <tl.doors.size(); i++) {
				tl.doors.get(i).render(g);
			}
		}
		//render switches
		if(!tl.doorSwitch.isEmpty()) {
			for (int i = 0; i <tl.doorSwitch.size(); i++) {
				tl.doorSwitch.get(i).render(g);
			}
		}
		if(tl.player.getVelocity().getX() != 0 || tl.player.getVelocity().getY() != 0) {
		// 1 = right, 2 = down, 3 = left, 4 = up
		if(tl.player.getDirection() == 1) {
			curr_ani = playerRightAnimation1;
			playerRightAnimation1.setSpeed(0.8f);
			playerRightAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
		}
		if(tl.player.getDirection() == 2) {
			curr_ani = playerDownAnimation1;
			playerDownAnimation1.setSpeed(0.8f);
			playerDownAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
		}
		if(tl.player.getDirection() == 3) {
			curr_ani = playerLeftAnimation1;
			playerLeftAnimation1.setSpeed(0.8f);
			playerLeftAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
		}
		if(tl.player.getDirection() == 4) {
			curr_ani = playerUpAnimation1;
			playerUpAnimation1.setSpeed(0.8f);
			playerUpAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
		}
		}
		
		if(tl.player.getVelocity().getX() == 0 && tl.player.getVelocity().getY() == 0) {
			//curr_ani.stop();
			if(tl.player.getDirection() == 3) {
				curr_ani = playerLeftIdleAnimation1;
				playerLeftIdleAnimation1.setSpeed(0.8f);
				playerLeftIdleAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
			}
			if(tl.player.getDirection() == 2 ) {
				curr_ani = playerDownIdleAnimation1;
				playerDownIdleAnimation1.setSpeed(0.8f);
				playerDownIdleAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
			}
			if(tl.player.getDirection() == 1 ) {
				curr_ani = playerRightIdleAnimation1;
				playerRightIdleAnimation1.setSpeed(0.8f);
				playerRightIdleAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
			}
			if(tl.player.getDirection() == 4 ) {
				curr_ani = playerUpIdleAnimation1;
				playerUpIdleAnimation1.setSpeed(0.8f);
				playerUpIdleAnimation1.draw(tl.player.getCoarseGrainedMinX(),tl.player.getCoarseGrainedMinY());
			}
			
		}
		
		// render UI
		tl.UIHandler.render(tl, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	
		Input input = container.getInput();
		Game tl = (Game)game;
		if(input.isKeyPressed(Input.KEY_F1)) {
			if(debugMode)
				debugMode = false;
			else
				debugMode = true;
		}
		if (input.isKeyDown(Input.KEY_1)) {
			tl.currLevel = 1;
			tl.enterState(Game.SPLASHSCREEN);
		}
		if(input.isKeyDown(Input.KEY_2)) {
			tl.currLevel = 2;
			tl.enterState(Game.TRANSITIONSTATE);
		}
		if(input.isKeyDown(Input.KEY_3)) {
			tl.currLevel = 3;
			tl.enterState(Game.TRANSITIONSTATE);
		}
		
		playerControls(tl, input);
		itemCollision(tl, delta);
		doorFunctions(tl,delta);
		updateDoorStatus(tl,delta);
		tl.map.updateMap(game);
		tl.player.update(tl, delta);
		updateEnemy(game,delta);
		
		// update each bullet on the map
		for(int i = 0; i < tl.projectiles.size(); i++) {
			tl.projectiles.get(i).update(delta);
			if(tl.projectiles.get(i).isFromEnemy == false) {
				if(tl.projectiles.get(i).hitOrMiss(game)) {
					for(int j = 0; j < tl.enemy.size(); j++) {
						if(tl.enemy.get(j).collides(tl.projectiles.get(i)) != null)
							tl.enemy.get(j).takeDamage(tl.projectiles.get(i), tl.player.getAttackDamage());
					}
					tl.projectiles.remove(i);
				}
			}
			else{
				if(tl.projectiles.get(i).hitOrMissForEnemies(game)) {
					if(tl.projectiles.get(i).collides(tl.player)!= null) {
						// reduce health of the player
						// 5 damage from enemy bullets
						tl.player.takeDamage(5);
					}
					tl.projectiles.remove(i);
				}
			}
		}
		
	}

	private void updateEnemy(StateBasedGame game, int delta) {
		Game tl = (Game)game;
		for(int i = 0; i < tl.enemy.size(); i++) {
			// check if they are still alive
			if(tl.enemy.get(i).getHP() <= 0)
				tl.enemy.remove(i);
			else {
				if(tl.enemy.get(i).getEnemyType() == chaser && tl.enemy.get(i).getKO() <= 0) {
					tl.enemy.get(i).chasePath();
				}
				if(tl.enemy.get(i).getEnemyType() == runner && tl.enemy.get(i).getKO() <= 0) {
					if(tl.enemy.get(i).getPath().size() <= 8 && tl.enemy.get(i).getPath().size() >= 4) {
						tl.enemy.get(i).setVelocity(tl.player.getVelocity());
					}
					else if(tl.enemy.get(i).getPath().size() <= 3) {
						tl.enemy.get(i).setVelocity(tl.player.getVelocity().negate());
					}
					else {
						tl.enemy.get(i).chasePath();
					}
				}
				if(tl.enemy.get(i).getEnemyType() == shooter && tl.enemy.get(i).getKO() <= 0) {
					if(tl.enemy.get(i).getPath().size() <= 5) {
						tl.enemy.get(i).setVelocity(new Vector(0,0));
						if(tl.enemy.get(i).shootCooldown <= 0) {
							tl.enemy.get(i).shootCooldown = 600;
							addProjectile(game, tl.enemy.get(i), null, true);
						}
					}
					else if(tl.enemy.get(i).getKO() <= 0){
						tl.enemy.get(i).chasePath();
					}
				}
				tl.enemy.get(i).shootCooldown -= delta;
				tl.enemy.get(i).update(game, delta);
			}
		}
	}
	private void itemCollision(Game g, int delta) {
		
		// remove items that have collided with the player
		for(int i = 0; i < g.items.size(); i++) {
			Item item = g.items.get(i);
			
			if(g.player.collides(item) != null && g.player.getActiveDelay() <= 0) {
				if(g.UIHandler.getActivatable() != null && (item.isActivatable()))
					break;
				// add item to the UI
				g.UIHandler.addItem(item);
				// adjust stats of the player
				g.player.adjustStats(item);
				// remove item from the map
				g.items.remove(i);
			}
			
		}
		
	}

	private void updateDoorStatus(Game g, int delta) {
	}
	
	private void doorFunctions(Game g, int delta) {
		//check for door collision
		for(int i = 0; i < g.doorSwitch.size(); i++) {
			DoorSwitch doorswitch = g.doorSwitch.get(i);
			if(g.player.collides(doorswitch) != null) {
				if(!doorswitch.isSwitched()) {
					doorswitch.setIsSwitched(true);
					for(int d = 0; d<doorswitch.getDoor().size(); d++) {
						doorswitch.getDoor().get(d).setIsPass(true);
						doorswitch.getDoor().get(d).rmImage();
						doorswitch.getDoor().get(d).setimage();
					}
					
					doorswitch.removeImage(doorswitch.img);
					doorswitch.addImage(ResourceManager.getImage(Game.DOOR_SWITCH_ON).getScaledCopy(40, 40));
					
					
				}
			}
		}
	}
	
	private void playerControls(Game tl, Input input) {
		// drop activatable
		if(input.isKeyDown(Input.KEY_Q) && tl.player.getActiveDelay() <= 0 && tl.UIHandler.getActivatable() != null) {
			tl.player.canActivate(false);				// remove the option of using SPACE to activate power
			addItem(tl, tl.UIHandler.getActivatable());	// add the item back to the game
			tl.UIHandler.setActivatable(null);			// remove the item from the UI
		}
		// activate activatable
		if(input.isKeyDown(Input.KEY_SPACE) && tl.player.canActivate() && Item.current_cooldown <= 0) {
			// do action / change stats of player
			// accelerator action
			if(tl.UIHandler.getActivatable().getType().equals("accelerator")) {
				tl.player.doAction(tl.UIHandler.getActivatable());
				// start a cooldown timer
				tl.UIHandler.showTimer();
				Item.startTimer(20);
			}
			// fiery action
			else if(tl.UIHandler.getActivatable().getType().equals("fiery")) {
				// add 24 bullets
				for(int i = 0; i < 24; i++) {
					Vector v = new Vector(1, 1);
					v = v.setRotation(15 * i);
					System.out.println(v.getRotation());
					addProjectile(tl, tl.player, v, false);
				}
				tl.UIHandler.showTimer();
				Item.startTimer(8);
			}
			
		}
		
		tl.player.setVelocity(new Vector(0, 0));
		// player movement
		if (input.isKeyDown(Input.KEY_W)) {
			tl.player.setVelocity(tl.player.getVelocity().add(new Vector(0, -1)));
			if(arrow_check == 0) {
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(4);
			}
		}
		if (input.isKeyDown(Input.KEY_S)) {
			tl.player.setVelocity(tl.player.getVelocity().add(new Vector(0, 1)));
			if(arrow_check == 0) {
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(2);
			}
		}
		if (input.isKeyDown(Input.KEY_A)) {
			tl.player.setVelocity(tl.player.getVelocity().add(new Vector(-1, 0)));
			if(arrow_check == 0) {
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(3);
			}
		}
		if (input.isKeyDown(Input.KEY_D)) {
			tl.player.setVelocity(tl.player.getVelocity().add(new Vector(1, 0)));
			if(arrow_check == 0) {
				tl.player.setDirection(1);
				tl.player.setPrevDirection(tl.player.getDirection());
			}
		}	
		
		// player direction / aim
		// wait for a slight cooldown to allow slower response times to angled facing position
		
		if (tl.player.getRotateDelay() <= 0) {
			if (input.isKeyDown(Input.KEY_UP)) {
				tl.player.setImageRotation(180);
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(4);
				if(tl.player.getShootDelay() <= 0) {
					addProjectile(tl, tl.player, new Vector(0, -1), false);
					tl.player.setShootDelay();
				}
			}
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				tl.player.setImageRotation(270);
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(1);
				if(tl.player.getShootDelay() <= 0) {
					addProjectile(tl, tl.player, new Vector(1, 0),false);
					tl.player.setShootDelay();
				}	
			}
			if (input.isKeyDown(Input.KEY_DOWN)) {
				tl.player.setImageRotation(0);
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(2);
				if(tl.player.getShootDelay() <= 0) {
					addProjectile(tl, tl.player, new Vector(0, 1),false);
					tl.player.setShootDelay();
				}
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				tl.player.setImageRotation(90);
				tl.player.setPrevDirection(tl.player.getDirection());
				tl.player.setDirection(3);
				if(tl.player.getShootDelay() <= 0) {
					addProjectile(tl, tl.player, new Vector(-1, 0),false);
					tl.player.setShootDelay();
				}
			}
		}
		
		arrow_check = 0;
		if (input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_RIGHT)) {
			//tl.player.setImageRotation(225);
			//tl.player.setRotateDelay(50);
			if(tl.player.getShootDelay() <= 0) {
				addProjectile(tl, tl.player, new Vector(1, -1),false);
				tl.player.setShootDelay();
				arrow_check = 1;
			}
			
		}
		if (input.isKeyDown(Input.KEY_RIGHT) && input.isKeyDown(Input.KEY_DOWN)) {
			//tl.player.setImageRotation(315);
			//tl.player.setRotateDelay(50);
			if(tl.player.getShootDelay() <= 0) {
				addProjectile(tl, tl.player, new Vector(1, 1),false);
				tl.player.setShootDelay();
				arrow_check = 1;
			}
			
		}
		if (input.isKeyDown(Input.KEY_DOWN) && input.isKeyDown(Input.KEY_LEFT)) {
			//tl.player.setImageRotation(45);
			//tl.player.setRotateDelay(50);
			if(tl.player.getShootDelay() <= 0) {
				addProjectile(tl, tl.player, new Vector(-1, 1),false);
				tl.player.setShootDelay();
				arrow_check = 1;
			}
			
		}
		if (input.isKeyDown(Input.KEY_UP) && input.isKeyDown(Input.KEY_LEFT)) {
			//tl.player.setImageRotation(135);
			//tl.player.setRotateDelay(50);
			if(tl.player.getShootDelay() <= 0) {
				addProjectile(tl, tl.player, new Vector(-1, -1), false);
				tl.player.setShootDelay();
				arrow_check = 1;
			}
		}
	}
	
	// add the item into the map
	private void addItem(Game tl, Item activatable) {
		// drop the item where the player is located
		System.out.println(activatable);
		activatable.setPosition(tl.player.getPosition());
		// set pick up delay so that the player doesn't instantly pick it back up
		tl.player.setActiveDelay(500);
		// add the item back to the item list array in the game
		tl.items.add(activatable);
	}

	private void addProjectile(StateBasedGame game, Entity e, Vector v, boolean fromEnemy) {
		Projectile p = new Projectile(e.getX(), e.getY());
		Game g = (Game)game;
		//g.image_control.setImage(p, Game.PROJECTILE_DEFAULT_RSC, (int)g.player.getImageRotation() + 90, false);
		if (fromEnemy) {
			// find the direction for the bullets to travel to
			// check if the entity is an enemy
			g.image_control.setImage(p, Game.PROJECTILE_DEFAULT_RSC, (int)g.player.getImageRotation() + 90, false);
			p.isFromEnemy = true;
			Vector playerPos = g.player.getPosition();
			
				double dir = playerPos.subtract(e.getPosition()).getRotation();
				v = new Vector(1, 1).setRotation(dir);
				p.setSpeed(0.2f);
			
		} else {
			// bullet is from the player, adjust speed and damage
			g.image_control.setImage(p, Game.PROJECTILE_DEFAULT_RSC, (int)g.player.getImageRotation() + 90, false);
			p.setDamage(g.player.getAttackDamage());
			p.setSpeed(g.player.getBulletSpeed());
			
//			g.player_shoot_cooldown = g.player.rof;
		}
		p.setDirection(e, v);
		g.projectiles.add(p);
	}
	
	
	@Override
	public int getID() {
		return Game.PLAYINGSTATE;
	}
}
