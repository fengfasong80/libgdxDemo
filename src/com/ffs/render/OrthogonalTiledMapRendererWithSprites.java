package com.ffs.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义渲染器
 * @author fengfasong
 * @date 2020/9/10
 */
public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {

    private Sprite sprite;

    private List<Sprite> sprites;

    private int drawSpritesAfterLayer = 1;

    public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
        super(map);
        sprites = new ArrayList<>();
    }

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    @Override
    public void render() {
        super.render();
        beginRender();
        int currentLayer = 0;
        for(MapLayer layer : map.getLayers()){
            if(layer.isVisible()){
                if(layer instanceof TiledMapTileLayer){
                    renderTileLayer((TiledMapTileLayer) layer);
                    currentLayer++;
                    if(currentLayer == drawSpritesAfterLayer){
                        for(Sprite sprite : sprites){
                            sprite.draw(this.getBatch());
                        }
                    }else {
                        for(MapObject mapObject : layer.getObjects()){
                            renderObject(mapObject);
                        }
                    }
                }
            }
        }
        endRender();
    }
}
