
package mx.unam.poo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fire {
     public float x,y;
     
     private int height = 64;
     private int width = 64;
     
//    
     public final Texture imagenFire;
     
     
     

     //Constructor 
    public Fire(float x, float y) {
        this.x = x;
        this.y = y;
            imagenFire = new Texture(Gdx.files.internal("fire.png"));
        
    }
    
    public void render(final SpriteBatch batch,float x, float y){
        batch.draw(imagenFire,x,y,width,height);
    }
}
