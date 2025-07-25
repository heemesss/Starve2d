package com.heem.Starve2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.heem.Starve2d.screens.FirstScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game { // это основная параша, отвечает за логику смены экранов
    public static final int SCREEN_WIDTH = 1280, SCREEN_HEIGHT = 720; // экран
    private Screen screen; // текущий screen

    @Override
    public void create() {
        setScreen(new FirstScreen(this));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.CLEAR); // очистка
        screen.render(Gdx.graphics.getDeltaTime()); //render
    }

    @Override
    public void setScreen(Screen screen) { // меняем скрин
        if (this.screen != null) {
            this.screen.hide();
            this.screen.dispose();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
