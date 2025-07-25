package com.heem.Starve2d.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {
    public Sprite sprite; // спрайт, чтобы рисовать

    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }
    public SpriteComponent(){};
}
