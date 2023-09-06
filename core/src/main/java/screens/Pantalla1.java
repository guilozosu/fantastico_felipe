package screens;

import characters.Felipe;
import characters.Monster;
import characters.PinkGuy;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.scenes.scene2d.*;
import objects.Coin;
import objects.Flag;
import objects.Hearth;
import juego.Juego;
import objects.Rune;

public class Pantalla1 implements Screen {

    private final Juego juego;
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Felipe koala;
    Monster monster1, monster2, monster3, monster4;
    PinkGuy pinkGuy1;
    Hearth hearth1, hearth2;
    Flag flag;
    Rune rune1;
    Coin coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8, coin9, coin10, coin11, coin12, coin13, coin14, coin15, coin16, coin17, coin18;
    BitmapFont score, level, life;
    Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coinSound.wav"));
    Sound runeSound = Gdx.audio.newSound(Gdx.files.internal("sounds/runeSound.wav"));
    Sound hearthSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hearthSound.wav"));
    Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/painSound.wav"));
    Sound levelSound = Gdx.audio.newSound(Gdx.files.internal("sounds/levelSound.wav"));
    Sound gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameOverSound.wav"));
    
    // https://mixkit.co/free-sound-effects/music/?page=2
    // https://www.photopea.com/
    // https://buttonoptimizer.com/
    
    SpriteBatch batch;
    int puntos = 0, vida;
    boolean gotCoin1 = false, gotCoin2 = false, gotCoin3 = false, gotCoin4 = false, gotCoin5 = false, 
            gotCoin6 = false, gotCoin7 = false, gotCoin8 = false, gotCoin9 = false, gotCoin10 = false,
            gotCoin11 = false, gotCoin12 = false, gotCoin13 = false, gotCoin14 = false, gotCoin15 = false,    
            gotCoin16 = false, gotCoin17 = false, gotCoin18 = false;
    boolean gotMonster1 = false, gotMonster2 = false, gotMonster3 = false, gotMonster4 = false;
    boolean gotHearth1 = false, gotHearth2 = false;
    boolean gotFlag = false;
    boolean gotRune1 = false;
    boolean gotPinkGuy1 = false;

    public Pantalla1(Juego game){
        this.juego = game;
        
    }

    
    @Override
    public void show() {
        
        // Inicializar todo a cero!!
        puntos = 0; vida = juego.getVida();
        gotCoin1 = false; gotCoin2 = false; gotCoin3 = false; gotCoin4 = false; gotCoin5 = false;
        gotCoin6 = false; gotCoin7 = false; gotCoin8 = false; gotCoin9 = false; gotCoin10 = false;
        gotCoin11 = false; gotCoin12 = false; gotCoin13 = false; gotCoin14 = false; gotCoin15 = false;
        gotCoin16 = false; gotCoin17 = false; gotCoin18 = false;
        
        gotMonster1 = false; gotMonster2 = false; gotMonster3 = false; gotMonster4 = false;
        gotPinkGuy1 = false;
        gotHearth1 = false; gotHearth2 = false;
        
        gotFlag = false;
        gotRune1 = false;
        
        score = new BitmapFont();
        level = new BitmapFont();
        life = new BitmapFont();
        
        batch = new SpriteBatch();

        map = new TmxMapLoader().load("level1.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        
        // BANDERA fin de nivel
        flag = new Flag();
        flag.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        flag.setPosition(196, 5);
        stage.addActor(flag);
        
        // RUNAS
        rune1 = new Rune();
        rune1.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        rune1.setPosition(93, 13);
        stage.addActor(rune1);
        
        // PERSONAJE principal
        koala = new Felipe();
        koala.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        koala.setPosition(20, 10);
        stage.addActor(koala);
        
        // PINKGUY
        pinkGuy1 = new PinkGuy();
        pinkGuy1.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        pinkGuy1.setPosition(15, 2);
        stage.addActor(pinkGuy1);
        
        // MONSTRUOS
        monster1 = new Monster();
        monster1.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        monster1.setPosition(158, 2);
        stage.addActor(monster1);
        
        monster2 = new Monster();
        monster2.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        monster2.setPosition(168, 2);
        stage.addActor(monster2);
        
        monster3 = new Monster();
        monster3.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        monster3.setPosition(178, 2);
        stage.addActor(monster3);
        
        monster4 = new Monster();
        monster4.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        monster4.setPosition(186, 2);
        stage.addActor(monster4);
        
        // CORAZONES 
        hearth1 = new Hearth();
        hearth1.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        hearth1.setPosition(7, 9);
        stage.addActor(hearth1);
        
        hearth2 = new Hearth();
        hearth2.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        hearth2.setPosition(162, 12);
        stage.addActor(hearth2);

        // MONEDAS
            coin1 = new Coin();
            coin1.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin1.setPosition(26, 2);
            stage.addActor(coin1);
            
            coin2 = new Coin();
            coin2.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin2.setPosition(28, 2);
            stage.addActor(coin2);
            
            coin3 = new Coin();
            coin3.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin3.setPosition(30, 2);
            stage.addActor(coin3);
            
            coin4 = new Coin();
            coin4.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin4.setPosition(34, 6);
            stage.addActor(coin4);
            
            coin5 = new Coin();
            coin5.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin5.setPosition(36, 6);
            stage.addActor(coin5);
            
            coin6 = new Coin();
            coin6.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin6.setPosition(38, 6);
            stage.addActor(coin6);
            
            coin7 = new Coin();
            coin7.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin7.setPosition(42, 10);
            stage.addActor(coin7);
            
            coin8 = new Coin();
            coin8.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin8.setPosition(44, 10);
            stage.addActor(coin8);
            
            coin9 = new Coin();
            coin9.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin9.setPosition(46, 10);
            stage.addActor(coin9);
            
            coin10 = new Coin();
            coin10.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin10.setPosition(51, 10);
            stage.addActor(coin10);
            
            coin11 = new Coin();
            coin11.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin11.setPosition(53, 10);
            stage.addActor(coin11);
            
            coin12 = new Coin();
            coin12.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin12.setPosition(55, 10);
            stage.addActor(coin12);
            
            coin13 = new Coin();
            coin13.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin13.setPosition(60, 10);
            stage.addActor(coin13);
            
            coin14 = new Coin();
            coin14.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin14.setPosition(62, 10);
            stage.addActor(coin14);
            
            coin15 = new Coin();
            coin15.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin15.setPosition(68, 6);
            stage.addActor(coin15);
            
            coin16 = new Coin();
            coin16.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin16.setPosition(70, 6);
            stage.addActor(coin16);
            
            coin17 = new Coin();
            coin17.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin17.setPosition(72, 6);
            stage.addActor(coin17);
            
            coin18 = new Coin();
            coin18.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            coin18.setPosition(64, 10);
            stage.addActor(coin18);
        //}

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.x = koala.getX();
        camera.position.y = 10;
        camera.update();
            
        // GAME OVER
        if(koala.getY() < 0 || vida <= 0){
            if(juego.isSonidoOn() == true)
                gameOverSound.play();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            koala.remove();
            juego.irPantallaGameOver1();
        }
        
        // SEGUIMIENTO DE CÁMARA CUANDO SALTE
        /*
        if(koala.getY() > 10){
            camera.position.y = koala.getY() + 4;
            camera.update();
        }
        */
        
        if (koala.dimension().overlaps(flag.dimension()) && gotFlag == false) {
            if(juego.isSonidoOn() == true)
                levelSound.play();
            flag.remove();
            gotFlag = true;
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            juego.irPantalla2();
        }
        
        if (koala.dimension().overlaps(pinkGuy1.dimension()) && gotPinkGuy1 == false) {
            if(juego.isSonidoOn() == true)
                hurtSound.play();
            pinkGuy1.remove();
            gotPinkGuy1 = true;
            vida = vida - 25;
        }
        
        if (koala.dimension().overlaps(monster1.dimension()) && gotMonster1 == false) {
            if(juego.isSonidoOn() == true)
                hurtSound.play();
            monster1.remove();
            gotMonster1 = true;
            vida = vida - 25;
        }
        
        if (koala.dimension().overlaps(monster2.dimension()) && gotMonster2 == false) {
            if(juego.isSonidoOn() == true)
                hurtSound.play();
            monster2.remove();
            gotMonster2 = true;
            vida = vida - 25;
        }
        
        if (koala.dimension().overlaps(monster3.dimension()) && gotMonster3 == false) {
            if(juego.isSonidoOn() == true)
                hurtSound.play();
            monster3.remove();
            gotMonster3 = true;
            vida = vida - 25;
        }
        
        if (koala.dimension().overlaps(monster4.dimension()) && gotMonster4 == false) {
            if(juego.isSonidoOn() == true)
                hurtSound.play();
            monster4.remove();
            gotMonster4 = true;
            vida = vida - 25;
        }
        
        if (koala.dimension().overlaps(hearth1.dimension()) && gotHearth1 == false) {
            if(juego.isSonidoOn() == true)
                hearthSound.play();
            hearth1.remove();
            gotHearth1 = true;
            vida = vida + 25;
        }
        
        if (koala.dimension().overlaps(hearth2.dimension()) && gotHearth2 == false) {
            if(juego.isSonidoOn() == true)
                hearthSound.play();
            hearth2.remove();
            gotHearth2 = true;
            vida = vida + 25;
        }
        
        if (koala.dimension().overlaps(rune1.dimension()) && gotRune1 == false) {
            if(juego.isSonidoOn() == true)
                runeSound.play();
            rune1.remove();
            gotRune1 = true;
            puntos = puntos + 10;
            //coinSound.dispose();
        }
        
        if (koala.dimension().overlaps(coin1.dimension()) && gotCoin1 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin1.remove();
            gotCoin1 = true;
            puntos++;
            //coinSound.dispose();
        }
        
        if (koala.dimension().overlaps(coin2.dimension()) && gotCoin2 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin2.remove();
            gotCoin2 = true;
            puntos++;
            //coinSound.dispose();
        }
        
        if (koala.dimension().overlaps(coin3.dimension()) && gotCoin3 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin3.remove();
            gotCoin3 = true;
            puntos++;
            //coinSound.dispose();
        }
        
        if (koala.dimension().overlaps(coin4.dimension()) && gotCoin4 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin4.remove();
            gotCoin4 = true;
            puntos++;
            //coinSound.dispose();
        }
        
        if (koala.dimension().overlaps(coin5.dimension()) && gotCoin5 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin5.remove();
            gotCoin5 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin7.dimension()) && gotCoin7 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin7.remove();
            gotCoin7 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin8.dimension()) && gotCoin8 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin8.remove();
            gotCoin8 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin9.dimension()) && gotCoin9 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin9.remove();
            gotCoin9 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin10.dimension()) && gotCoin10 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin10.remove();
            gotCoin10 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin11.dimension()) && gotCoin11 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin11.remove();
            gotCoin11 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin12.dimension()) && gotCoin12 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin12.remove();
            gotCoin12 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin13.dimension()) && gotCoin13 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin13.remove();
            gotCoin13 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin14.dimension()) && gotCoin14 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin14.remove();
            gotCoin14 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin15.dimension()) && gotCoin15 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin15.remove();
            gotCoin15 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin16.dimension()) && gotCoin16 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin16.remove();
            gotCoin16 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin17.dimension()) && gotCoin17 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin17.remove();
            gotCoin17 = true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin18.dimension()) && gotCoin18 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin18.remove();
            gotCoin18= true;
            puntos++;
        }
        
        if (koala.dimension().overlaps(coin6.dimension()) && gotCoin6 == false) {
            if(juego.isSonidoOn() == true)
                coinSound.play();
            coin6.remove();
            gotCoin6 = true;
            puntos++;
        }
        
        // Detectamos si ha pulsado la tecla ESCAPE
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            juego.irPantallaMenu();
        }

        renderer.setView(camera);
        renderer.render();

        stage.act(delta);
        stage.draw();
        
        batch.begin();
        score.draw(batch, "Puntuación: " + puntos, 30, 440);
        if(vida > 50){
            life.setColor(new Color(0, 1, 0, 1));
            //life.getData().setScale(1.5f, 2f); 
            life.draw(batch, "Vida: " + vida + "%", 30, 420);
        }else{
            life.setColor(new Color (1, 0, 0, 1));
            life.draw(batch, "Vida: " + vida + "%", 30, 420);
        }
        
                  
        
        level.draw(batch, "Nivel: 1", 30, 400);
        batch.end();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 20 * width / height, 20);
    }

    @Override
    public void resume() {
    }
}
