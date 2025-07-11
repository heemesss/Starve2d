package com.heem.Starve2d.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerComponent implements Component {
    public int velocity = 10;
    public Animation<TextureRegion> walk;
    public Animation<TextureRegion> walkUp;
    public Animation<TextureRegion> walkDown;
    public Animation<TextureRegion> idle;
    public Animation<TextureRegion> idleSide;
    public Animation<TextureRegion> idleUp;

    public int rightHand = 0;
}
