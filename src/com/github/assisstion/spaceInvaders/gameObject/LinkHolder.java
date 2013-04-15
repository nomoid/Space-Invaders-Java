package com.github.assisstion.spaceInvaders.gameObject;

public final class LinkHolder {
	public static String player;
	public static String grayEnemy;
	public static String blueEnemy;
	public static String redEnemy;
	public static String mothership;
	public static String explosion;
	public static String playerExplosion;
	
	public static String speedBoost;
	public static String firerateBoost;
	public static String powerBoost;
	public static String healthBoost;
	
	public static String bullet;
	public static String grayBullet;
	public static String blueBullet;
	public static String redBullet;
	 
	
	
	
	public static void setSprites(String name){
		player="resources/" + name + "/Spaceship.png";
		grayEnemy="resources/" + name + "/Enemy SpaceShip.png";
		blueEnemy="resources/" + name + "/Enemy SpaceShip Blue.png";
		redEnemy="resources/" + name + "/Enemy SpaceShip Red.png";
		mothership="resources/" + name + "/Mothership.png";
		explosion="resources/" + name + "/Explosion.png";
		playerExplosion="resources/" + name + "/Explosion 48*48.png";
		
		speedBoost="resources/" + name + "/SpeedBoost.png";
		firerateBoost="resources/" + name + "/FirerateBoost.png";
		powerBoost="resources/" + name + "/PowerBoost.png";
		healthBoost="resources/" + name + "/HealthBoost.png";
		
		bullet="resources/" + name + "/Bullet.png";
		grayBullet="resources/" + name + "/GrayShot.png";
		blueBullet="resources/" + name + "/BlueShot.png";
		redBullet="resources/" + name + "/RedShot.png";
		
		refresh();
	}
	
	private static void refresh(){
	
		Enemy.ENEMY_IMAGE = new String[]{
				LinkHolder.grayEnemy,
				LinkHolder.blueEnemy,
				LinkHolder.redEnemy, 
				LinkHolder.mothership};
		
		Bullet.BULLET_SHOT = 
			new String[]{LinkHolder.bullet, LinkHolder.grayBullet, LinkHolder.redBullet, LinkHolder.blueBullet,"resources/EggBullet.png"};
		
		Explosion.EXPLOSION_DEFAULT_IMAGES = new String[]{LinkHolder.explosion,LinkHolder.playerExplosion};
		
		Player.PLAYER_DEFAULT_IMAGE = new String[]{LinkHolder.player,"resources/EasterEgg.png"};

	}
	
	public static void restoreDefaults(){
		player="resources/Default Sprites/Spaceship.png";
		grayEnemy="resources/Default Sprites/Enemy SpaceShip.png";
		blueEnemy="resources/Default Sprites/Enemy SpaceShip Blue.png";
		redEnemy="resources/Default Sprites/Enemy SpaceShip Red.png";
		mothership="resources/Default Sprites/Mothership.png";
		explosion="resources/Default Sprites/Explosion.png";
		playerExplosion="resources/Default Sprites/Explosion 48*48.png";
		
		speedBoost="resources/Default Sprites/SpeedBoost.png";
		firerateBoost="resources/Default Sprites/FirerateBoost.png";
		powerBoost="resources/Default Sprites/PowerBoost.png";
		healthBoost="resources/Default Sprites/HealthBoost.png";
		
		bullet="resources/Default Sprites/Bullet.png";
		grayBullet="resources/Default Sprites/GrayShot.png";
		blueBullet="resources/Default Sprites/BlueShot.png";
		redBullet="resources/Default Sprites/RedShot.png";
		refresh();
		
	}
}
