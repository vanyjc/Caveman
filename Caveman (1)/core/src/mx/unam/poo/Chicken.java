
package mx.unam.poo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chicken {
    private float x,y;
     
     private int height = 64;
     private int width = 64;
     
     private Texture imagenChicken;
     
     public Chicken(float x, float y){
        this.x = x;
        this.y = y;
        
        imagenChicken = new Texture(Gdx.files.internal("chi.png"));

     }
     
     public void render(final SpriteBatch batch,float x,float y){
        batch.draw(imagenChicken,x,y,width,height);
    }
}
