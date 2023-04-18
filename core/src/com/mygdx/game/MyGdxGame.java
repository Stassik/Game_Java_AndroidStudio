package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.units.BaseHero;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon, mage, monk, armsman, arbalester, sniper, robber, spearman;
	Music melody;
	ArrayList<BaseHero> holyTeam;
	ArrayList<BaseHero> darkTeam;
	PriorityQueue<BaseHero> allTeam;
	
	@Override
	public void create () {
		holyTeam = BaseHero.generateTeam(1);
		darkTeam = BaseHero.generateTeam(2);
		allTeam = BaseHero.sortedPriority(darkTeam, holyTeam);
		batch = new SpriteBatch();
		fon = new Texture("Fons/"+ Fons.values()[new Random().nextInt(Fons.values().length)] + ".png");
		melody = Gdx.audio.newMusic(Gdx.files.internal("Музыка/"+ MusicForGame.values()[new Random().nextInt(MusicForGame.values().length)] +".mp3"));
		melody.setLooping(true);
		melody.setVolume(0.125f);
		melody.play();

		arbalester = new Texture("персонажи/arbalester.png");
		sniper = new Texture("персонажи/sniper.png");
		mage = new Texture("персонажи/mage.png");
		monk = new Texture("персонажи/monk.png");
		spearman = new Texture("персонажи/spearman.png");
		robber = new Texture("персонажи/robber.png");
		armsman = new Texture("персонажи/armsman.png");

	}

	@Override
	public void render () {

		//if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
		allTeam = BaseHero.sortedPriority(darkTeam, holyTeam);
			for (BaseHero unit : allTeam) {
				if (holyTeam.contains(unit)) {
					unit.step(darkTeam, holyTeam, allTeam);
				} else {
					unit.step(holyTeam, darkTeam, allTeam);
				}
			}
			for (BaseHero u: allTeam) {
				if (u.status.equals("busy")) {
					u.status = "stand";
				}
			}

		//}


		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i = holyTeam.size()-1; i >= 0 ; i--) {
			batch.setColor(1, 1, 1, 1);
			if (holyTeam.get(i).status.equals("died")) batch.setColor(Color.RED);
			int x = (holyTeam.get(i).pos.y - 1) * Gdx.graphics.getWidth()/12;
			int y = (holyTeam.get(i).pos.x - 1) * Gdx.graphics.getHeight()/12;
			switch (holyTeam.get(i).className) {
				case "Арбалетчик":
					batch.draw(arbalester, x, y);
					break;
				case "Снайпер":
					batch.draw(sniper, x, y);
					break;
				case "Монах":
					batch.draw(monk, x, y);
					break;
				case "Маг":
					batch.draw(mage, x, y);
					break;
				case "Копейщик":
					batch.draw(spearman, x, y);
					break;
				case "Разбойник":
					batch.draw(robber, x, y);
					break;
				case "Оруженосец":
					batch.draw(armsman, x, y);
			}
			batch.setColor(1, 1, 1, 1);
			if (darkTeam.get(i).status.equals("died")) batch.setColor(Color.RED);
			x = (darkTeam.get(i).pos.y +1) * Gdx.graphics.getWidth()/12;
			y = (darkTeam.get(i).pos.x-1) * Gdx.graphics.getHeight()/12;
			switch (darkTeam.get(i).className){
				case "Арбалетчик":
					Sprite sprite = new Sprite(arbalester);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Маг":
					sprite = new Sprite(mage);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Монах":
					sprite = new Sprite(monk);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Оруженосец":
					sprite = new Sprite(armsman);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Разбойник":
					sprite = new Sprite(robber);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Снайпер":
					sprite = new Sprite(sniper);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Копейщик":
					sprite = new Sprite(spearman);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
			}

		}
		batch.setColor(1, 1, 1, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}
