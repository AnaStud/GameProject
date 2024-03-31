package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.MainProgram;
import heroes.*;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Texture crossbowman, magician, monk, outlaw, pearsant, sniper, spearman;

	MainProgram program;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("fon.jpg");
		program = new MainProgram();

		crossbowman = new Texture("crossbowman.png");
		magician = new Texture("magician.png");
		monk = new Texture("monk.png");
		outlaw = new Texture("outlaw.png");
		pearsant = new Texture("pearsant.png");
		sniper = new Texture("sniper.png");
		spearman = new Texture("spearman.png");
	}

	@Override
	public void render () {
		//ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (BaseHero hero : program.allTeam) {

			if (hero.getHp() == 0) continue;

			float x = hero.getX() * 50.f;
			float y = hero.getY() * 30.f;

			int k = -1;
			if (program.team1.contains(hero)) k = 1;

            switch (hero.getName().substring(0, 4)) {
                case "Cros":
                    batch.draw(crossbowman, x, y, 16 * k, 30);
                    break;
                case "Magi":
                    batch.draw(magician, x, y, 16 * k, 30);
                    break;
                case "Monk":
                    batch.draw(monk, x, y, 16 * k, 30);
                    break;
                case "Outl":
                    batch.draw(outlaw, x, y, 16 * k, 30);
                    break;
                case "Peas":
                    batch.draw(pearsant, x, y, 16 * k, 30);
                    break;
                case "Snip":
                    batch.draw(sniper, x, y, 16 * k, 30);
                    break;
                case "Spea":
                    batch.draw(spearman, x, y, 16 * k, 30);
                    break;
            }
		}

		batch.end();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
			program.step();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		crossbowman.dispose();
		magician.dispose();
		monk.dispose();
		outlaw.dispose();
		pearsant.dispose();
		sniper.dispose();
		spearman.dispose();
	}
}
