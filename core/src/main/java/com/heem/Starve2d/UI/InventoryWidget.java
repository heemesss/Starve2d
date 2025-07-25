package com.heem.Starve2d.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InventoryWidget extends Actor { // наследуем, чтобы можно было на Stage
    private Texture texture = new Texture("window.png");
    public static final int SIZE = 50;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int i = 0; i < 8; i++){
            batch.draw(texture, i * SIZE, getStage().getHeight() - SIZE, SIZE, SIZE);
        }

        batch.draw(texture, SIZE, SIZE, SIZE, SIZE);
        batch.draw(texture, getStage().getWidth() - SIZE * 2, SIZE, SIZE, SIZE);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
