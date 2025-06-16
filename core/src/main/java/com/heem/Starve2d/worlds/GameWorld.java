package com.heem.Starve2d.worlds;

import com.badlogic.ashley.core.Engine;
import com.heem.Starve2d.managers.EntityFactory;
import com.heem.Starve2d.managers.FloorFactory;
import com.heem.Starve2d.systems.PlayerSystem;
import com.heem.Starve2d.systems.RenderSystem;

public class GameWorld {
    private Engine engine;

    public GameWorld(){
        new FloorFactory();
        addSystems();
        addEntities();
    }

    private void addSystems() {
        engine = new Engine();
        RenderSystem renderSystem = new RenderSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(new PlayerSystem(renderSystem.camera));
    }

    private void addEntities() {
        engine.addEntity(EntityFactory.createPlayer());
        engine.addEntity(EntityFactory.createFloor(0, 0));
    }

    public void render(float delta){
        engine.update(delta);
    }
}
