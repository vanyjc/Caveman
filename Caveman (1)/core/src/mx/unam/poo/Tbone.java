
package mx.unam.poo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tbone {
    private float x,y;
     
     private int height = 64;
     private int width = 64;
     
     private Texture imagenTbone;
     
     public Tbone(float x, float y){
        this.x = x;
        this.y = y;
        
        imagenTbone = new Texture(Gdx.files.internal("tbone.png"));
     }
     
     public void render(final SpriteBatch batch,float x,float y){
        batch.draw(imagenTbone,x,y,width,height);
    }
}
