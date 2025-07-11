package com.heem.Starve2d.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.heem.Starve2d.Core;

public class GameUI extends ClickListener {
    private Core game;
    private Stage stage;

    private InventoryWidget inventoryWidget;

    public GameUI(Core game) {
        this.game = game;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        setWidgets();
        Gdx.input.setInputProcessor(stage);
    }

    private void setWidgets(){
        inventoryWidget = new InventoryWidget();

        stage.addActor(inventoryWidget);
    }

    public void update(){
        stage.act();
    }

    public void render(){
        stage.draw();
    }

}
