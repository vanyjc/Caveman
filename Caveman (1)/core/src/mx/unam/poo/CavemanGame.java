package mx.unam.poo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.security.SecureRandom;
import java.util.Iterator;

public class CavemanGame extends ApplicationAdapter {

    SpriteBatch batch;
    OrthographicCamera camera;
    Texture menu;
    Texture end;
    Texture background;
    Texture gameOver;
    Texture pausaimg;
    Music musicMenu;
    Music endGame;
    Music musicBack;
    Caveman cavernicolaAnimacion;
    Rectangle cavernicola;
    Sound foodSound;
    Sound rockSound;
    Sound fireSound;
    BitmapFont font;
    EstadoJuego estadoJuego;
    Stage stage;
    Skin skin;
    

    public enum EstadoJuego {
        PLAY, MENU, FIN, PAUSA
    }

    int puntaje = 0;
    int vidas = 3;
    int puntajetem=0;
    

    SecureRandom aleatorio = new SecureRandom();
    int tiempo;

    Array<Rectangle> rocks;
    long lastRockTime;
    Rock rocaImg;

    Array<Rectangle> chickens;
    long lastChickenTime;
    Chicken polloImg;

    Array<Rectangle> fires;
    long lastFireTime;
    Fire fuegoImg;

    Array<Rectangle> tbones;
    long lastTboneTime;
    Tbone carneImg;

    @Override
    public void create() {

        musicBack = Gdx.audio.newMusic(Gdx.files.internal("1c.mp3"));

        musicBack.setLooping(true);
        

        foodSound = Gdx.audio.newSound(Gdx.files.internal("comer.mp3"));
        rockSound = Gdx.audio.newSound(Gdx.files.internal("homer.mp3"));
        fireSound = Gdx.audio.newSound(Gdx.files.internal("homer.mp3"));
        musicMenu = Gdx.audio.newMusic(Gdx.files.internal("me.mp3"));
        
        font = new BitmapFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();

        background = new Texture(Gdx.files.internal("cue.png"));
        gameOver = new Texture(Gdx.files.internal("game.png"));
        endGame = Gdx.audio.newMusic(Gdx.files.internal("Fracasado.mp3"));
        menu = new Texture(Gdx.files.internal("menu.png"));
        end = new Texture(Gdx.files.internal("fin.png"));
        pausaimg = new Texture(Gdx.files.internal("pausa.jpeg"));
        
        cavernicola = new Rectangle();
        cavernicola.x = 800 / 2 - 64 / 2;
        cavernicola.y = 20;
        cavernicola.width = 64;
        cavernicola.height = 64;

        cavernicolaAnimacion = new Caveman(cavernicola.x, cavernicola.y);

        rocks = new Array<Rectangle>();

        chickens = new Array<Rectangle>();

        fires = new Array<Rectangle>();

        tbones = new Array<Rectangle>();

        spawnRock();

        spawnChicken();

        spawnFire();

        spawnTbone();

        estadoJuego = EstadoJuego.MENU;
    }

    private void spawnRock() {
        Rectangle rock = new Rectangle();

        //usamos el metodo random para que coloque  en
        //posiciones aleatorias en el eje x
        rock.x = MathUtils.random(0, 800 - 64);

        //Siempre va a empezar en 480 (hasta arriba)
        rock.y = 480;

        rocaImg = new Rock(rock.x, rock.y);
        //tamaño del rectangulo de la rock
        rock.width = 64;
        rock.height = 64;

        //Agrega la rock generada aleatoriamente al array
        rocks.add(rock);

        //se guarda el tiempo actual en nanosegundos cuando cae la rock
        lastRockTime = TimeUtils.nanoTime();
    }

    private void spawnChicken() {
        Rectangle chicken = new Rectangle();

        //usamos el metodo random para que coloque las pierna en
        //posiciones aleatorias en el eje x
        chicken.x = MathUtils.random(0, 800 - 64);

        //Siempre va a empezar la pierna en 480 (hasta arriba)
        chicken.y = 500;

        polloImg = new Chicken(chicken.x, chicken.y);
        //tamaño del rectangulo de la pierna
        chicken.width = 64;
        chicken.height = 64;

        //Agrega la pierna generada aleatoriamente al array
        chickens.add(chicken);

        //se guarda el tiempo actual en nanosegundos cuando cae el pierna
        lastChickenTime = TimeUtils.nanoTime();
    }

    private void spawnFire() {
        Rectangle fire = new Rectangle();

        //usamos el metodo random para que coloque las fuego en
        //posiciones aleatorias en el eje x
        fire.x = MathUtils.random(0, 800 - 64);

        //Siempre va a empezar la gota en 480 (hasta arriba)
        fire.y = 520;

        fuegoImg = new Fire(fire.x, fire.y);
        //tamaño del rectangulo de la fuego
        fire.width = 64;
        fire.height = 64;

        //Agrega la fuego generada aleatoriamente al array
        fires.add(fire);

        //se guarda el tiempo actual en nanosegundos cuando cae la fuego
        lastFireTime = TimeUtils.nanoTime();
    }

    private void spawnTbone() {
        Rectangle tbone = new Rectangle();

        //usamos el metodo random para que coloque las fuego en
        //posiciones aleatorias en el eje x
        tbone.x = MathUtils.random(0, 800 - 64);

        //Siempre va a empezar la fuego en 480 (hasta arriba)
        tbone.y = 540;

        carneImg = new Tbone(tbone.x, tbone.y);
        //tamaño del rectangulo de la fuego
        tbone.width = 64;
        tbone.height = 64;

        //Agrega la fuego generada aleatoriamente al array
        tbones.add(tbone);

        //se guarda el tiempo actual en nanosegundos cuando cae la fuego
        lastTboneTime = TimeUtils.nanoTime();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        switch (estadoJuego) {
            case MENU:
                stage = new Stage();
                endGame.stop();
                musicMenu.play();
                //Se crea, se le pone tamaño y posicion
                Table tabla = new Table();
                tabla.setPosition(1024 / 3, 450);
                tabla.setFillParent(true);
                tabla.setHeight(500);
                stage.addActor(tabla);

                //boton de play
                TextButton botonplay = new TextButton("PLAY", getSkin());
                botonplay.setPosition(300, 200);
                botonplay.setHeight(40);
                botonplay.setWidth(200);
                botonplay.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        estadoJuego = EstadoJuego.PLAY;

                        return false;
                    }
                });
                stage.addActor(botonplay);
                //boton de salir
                TextButton botonsalir = new TextButton("EXIT", getSkin());
                botonsalir.setPosition(300, 150);
                botonsalir.setHeight(40);
                botonsalir.setWidth(200);
                botonsalir.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        System.exit(0);
                        return false;
                    }
                });
                stage.addActor(botonsalir);
                //Pintando el menu y fondo del menu
                batch.begin();
                batch.draw(menu, 0, 0);
                batch.end();
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                break;

            case PLAY:
                musicMenu.pause();
                musicBack.play();
                camera.update();

                batch.setProjectionMatrix(camera.combined);

                batch.begin();

                batch.draw(background, 0, 0);

                font.setColor(1, 1, 1, 1.0f);
                font.getData().setScale(3f);
                font.draw(batch, "" + Integer.toString(puntaje), 200, 450);
                font.draw(batch, "" + Integer.toString(vidas), 730, 450);

                cavernicolaAnimacion.render(batch);

                for (Rectangle rock : rocks) {
                    rocaImg.render(batch, rock.x, rock.y);
                }

                for (Rectangle chicken : chickens) {
                    polloImg.render(batch, chicken.x, chicken.y);
                }

                for (Rectangle fire : fires) {
                    fuegoImg.render(batch, fire.x, fire.y);
                }

                for (Rectangle tbone : tbones) {
                    carneImg.render(batch, tbone.x, tbone.y);
                }

                if (vidas == 0) {
                    musicBack.stop();
                    endGame.play();
                    puntajetem = puntaje;
                    puntaje = 0;
                    vidas=3;
                    estadoJuego = EstadoJuego.FIN;
                }

                batch.end();
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    estadoJuego = EstadoJuego.PAUSA;
                }
                //Se mueve con teclas
                //Tecla izquierda
                if(cavernicolaAnimacion.x>=0){ 
                    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                         Vector3 left =  new Vector3();
                         left.set(Gdx.input.getX(), 0, 0);
                         camera.unproject(left);
                         cavernicola.x -=10;
                        cavernicolaAnimacion.x-= 10;
                    }
                }
                 
                //Tecla derecha
                if(cavernicolaAnimacion.x<=(800-64)){
                    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                         Vector3 right =  new Vector3();
                         right.set(Gdx.input.getX(), 0, 0);
                         camera.unproject(right);
                         cavernicola.x+= 10;
                         cavernicolaAnimacion.x+= 10;
                    }
                }

                int min = 150000;
                int max = 200000;
                tiempo = aleatorio.nextInt(max - min) + min;

                if (TimeUtils.nanoTime() - lastRockTime > (tiempo * 10000)) {
                    spawnRock();
                }
                Iterator<Rectangle> iter = rocks.iterator();
                while (iter.hasNext()) {
                    Rectangle rock = iter.next();
                    rock.y -= 200 * Gdx.graphics.getDeltaTime();
                    if (rock.y + 64 < 0) {
                        iter.remove();
                    }
                    if (rock.overlaps(cavernicola)) {
                        iter.remove();
                        rockSound.play();
                        vidas--;
                    }
                }

                tiempo = aleatorio.nextInt(max - min) + min;

                if (TimeUtils.nanoTime() - lastChickenTime > (tiempo * 10000)) {
                    spawnChicken();
                }
                Iterator<Rectangle> iterC = chickens.iterator();
                while (iterC.hasNext()) {
                    Rectangle chicken = iterC.next();
                    chicken.y -= 200 * Gdx.graphics.getDeltaTime();
                    if (chicken.y + 64 < 0) {
                        iterC.remove();
                    }
                    if (chicken.overlaps(cavernicola)) {
                        iterC.remove();
                        foodSound.play();
                        puntaje++;
                    }
                }

                tiempo = aleatorio.nextInt(max - min) + min;

                if (TimeUtils.nanoTime() - lastFireTime > (tiempo * 10000)) {
                    spawnFire();
                }
                Iterator<Rectangle> iterF = fires.iterator();
                while (iterF.hasNext()) {
                    Rectangle fire = iterF.next();
                    fire.y -= 200 * Gdx.graphics.getDeltaTime();
                    if (fire.y + 64 < 0) {
                        iterF.remove();
                    }
                    if (fire.overlaps(cavernicola)) {
                        iterF.remove();
                        fireSound.play();
                        vidas--;
                    }
                }

                tiempo = aleatorio.nextInt(max - min) + min;

                if (TimeUtils.nanoTime() - lastTboneTime > (tiempo * 10000)) {
                    spawnTbone();
                }
                Iterator<Rectangle> iterTb = tbones.iterator();
                while (iterTb.hasNext()) {
                    Rectangle tbone = iterTb.next();
                    tbone.y -= 200 * Gdx.graphics.getDeltaTime();
                    if (tbone.y + 64 < 0) {
                        iterTb.remove();
                    }
                    if (tbone.overlaps(cavernicola)) {
                        iterTb.remove();
                        foodSound.play();
                        puntaje++;
                    }
                }

                break;
            case FIN:
                stage = new Stage();
               
//                //Se crea, se le pone tamaño y posicion
                Table fin = new Table();
                fin.setPosition(1024 / 3, 450);
                fin.setFillParent(true);
                fin.setHeight(500);
                stage.addActor(fin);
                //boton de regresar salir 
                TextButton botonfin = new TextButton("EXIT", getSkin());
                botonfin.setPosition(475, 50);
                botonfin.setHeight(40);
                botonfin.setWidth(200);
                botonfin.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        System.exit(0);
                        return false;
                    }
                });
                stage.addActor(botonfin);
                TextButton botonend = new TextButton("BACK TO MENU", getSkin());
                botonend.setPosition(475, 125);
                botonend.setHeight(40);
                botonend.setWidth(200);
                botonend.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        estadoJuego = EstadoJuego.MENU;
                        return false;
                    }
                });
                stage.addActor(botonend);
                batch.begin();
                batch.draw(end, 0,0);
                font.setColor(1, 1, 1, 1.0f);
                font.getData().setScale(3f);
                font.draw(batch, "" + Integer.toString(puntajetem), 440, 440);
                batch.end();
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                break;
            case PAUSA: 
                stage = new Stage();
                musicBack.pause();
                musicMenu.play();
                
//                Se crea, se le pone tamaño y posicion
                Table fina = new Table();
                fina.setPosition(1024 / 3, 450);
                fina.setFillParent(true);
                fina.setHeight(500);
                stage.addActor(fina);
                //boton de regresar reanudar 
                TextButton botonreanudar = new TextButton("PLAY", getSkin());
                botonreanudar.setPosition(300, 190);
                botonreanudar.setHeight(40);
                botonreanudar.setWidth(200);
                botonreanudar.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        estadoJuego = EstadoJuego.PLAY;
                        return false;
                    }
                });
                TextButton botonsalirpausa = new TextButton("EXIT", getSkin());
                botonsalirpausa.setPosition(300, 90);
                botonsalirpausa.setHeight(40);
                botonsalirpausa.setWidth(200);
                botonsalirpausa.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        System.exit(0);
                        return false;
                    }
                });
                stage.addActor(botonsalirpausa);
                TextButton botonbotonmenupausa = new TextButton("MENU", getSkin());
                botonbotonmenupausa.setPosition(300, 140);
                botonbotonmenupausa.setHeight(40);
                botonbotonmenupausa.setWidth(200);
                botonbotonmenupausa.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int puntero, int boton) {
                        estadoJuego = EstadoJuego.MENU;
                        return false;
                    }
                });
                stage.addActor(botonbotonmenupausa);
                if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                    estadoJuego= EstadoJuego.PLAY;
                }
                stage.addActor(botonreanudar);
                batch.begin();
                batch.draw(pausaimg, 0,0);
                batch.end();
                stage.act(Gdx.graphics.getDeltaTime());
                stage.draw();
                Gdx.input.setInputProcessor(stage);
                break;
        }

    }

    public Skin getSkin() {
        if (skin == null) {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        return skin;
    }    


    @Override
    public void dispose() {
        background.dispose();
        musicBack.dispose();
        batch.dispose();
    }
}
