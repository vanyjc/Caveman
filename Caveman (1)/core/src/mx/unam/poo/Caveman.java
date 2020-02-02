/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.poo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Vannie
 */
public class Caveman {
        // private Sprite sprite;
     public float x,y;
     
     private int height = 64;
     private int width = 64;
     
     private final Animation animation;
     
     private float tiempo;
     
     private final TextureRegion [] regionsMovimiento; //regiones que representan el movimiento
     
     public final Texture imagenCaveman;
     
     private TextureRegion frameActual; //Cual region se usa en determinado momento
     
     

     //Constructor 
    public Caveman(float x, float y) {
        this.x = x;
        this.y = y;
        //sprite = new Sprite(new Texture(Gdx.files.internal("lost in time.png")),32,32);
        //Cargar la imagen:
            imagenCaveman = new Texture(Gdx.files.internal("lost in time.png"));
        
        //Matriz de regiones: regresa todas las regiones
        TextureRegion[][] tmp = TextureRegion.split(imagenCaveman, imagenCaveman.getWidth()/4,imagenCaveman.getHeight()/4);
        
        regionsMovimiento = new TextureRegion[4];   //tamaño total del arreglo     
        
        for(int i=0; i<4;i++) regionsMovimiento[i] = tmp[0][i];
        //pasa de un arreglo a una matriz: 
        /*La primera region es tal parte de la textura
        tmp asigna las imagenes a regionsMovimiento
        */
        
        animation = new Animation(1/10f,regionsMovimiento);
        
        tiempo = 0f;
    }
    
    public void render(final SpriteBatch batch){
        //tiempo que paso desde el ultimo frame
        tiempo += Gdx.graphics.getDeltaTime();
        //mmmm..
        frameActual = (TextureRegion) animation.getKeyFrame(tiempo,true);
        batch.draw(frameActual,x,y,width,height);
    }
}
