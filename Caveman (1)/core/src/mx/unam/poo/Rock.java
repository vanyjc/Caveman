
package mx.unam.poo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rock {
     private float x,y;
     
     private int height = 64;
     private int width = 64;
     
     private final Texture imagenRock;
     
     public Rock(float x, float y){
        this.x = x;
        this.y = y;
        
        imagenRock = new Texture(Gdx.files.internal("Rock Pile.png"));

     }
     
     public void render(final SpriteBatch batch,float x,float y){
        batch.draw(imagenRock,x,y,width,height);
    }
     
}

