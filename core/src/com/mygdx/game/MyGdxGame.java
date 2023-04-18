package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
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
		for (int i = 0; i < holyTeam.size(); i++) {
			int x = holyTeam.get(i).pos.x;
			int y = (holyTeam.get(i).pos.y - 1) * Gdx.graphics.getHeight()/10;
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

		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}
