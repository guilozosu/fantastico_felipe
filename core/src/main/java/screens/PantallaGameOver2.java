
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import juego.Juego;

/**
 *
 * @author dieef
 */
public class PantallaGameOver2 implements Screen {

    private final Juego juego;
    
    BitmapFont gameOver;
    
    private SpriteBatch batch;
    private Texture buttonStartAgainTexture, buttonExitTexture, buttonScoresTexture;
    private Rectangle buttonStartAgain, buttonExit, buttonScores;
    Texture backgroundTexture = new Texture("background_over01.png");
    TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
    
    public PantallaGameOver2(Juego juego){
        this.juego = juego;
    }
    
    @Override
    public void show() {
        
        gameOver = new BitmapFont();
        
        // Inicializamos el SpriteBatch y cargamos las texturas
        batch = new SpriteBatch();

        buttonStartAgainTexture = new Texture("buttons/buttonStartAgain.png");
        buttonExitTexture = new Texture("buttons/buttonExit.png");
        buttonScoresTexture = new Texture("buttons/buttonScores.png");

        // Creamos el rectángulo que representa el botón INICIAR
        int buttonStartWidth = Gdx.graphics.getWidth() / 4;
        int buttonStartHeight = buttonStartWidth / 4;
        int buttonStartX = Gdx.graphics.getWidth() / 2 - buttonStartWidth / 2;
        int buttonStartY = (Gdx.graphics.getHeight() / 8) * 4 - buttonStartHeight / 2;
        buttonStartAgain = new Rectangle(buttonStartX, buttonStartY, buttonStartWidth, buttonStartHeight);
        
        // Creamos el rectángulo que representa el botón CONFIGURACION
        int buttonSettingWidth = Gdx.graphics.getWidth() / 4;
        int buttonSettingHeight = buttonSettingWidth / 4;
        int buttonSettingX = Gdx.graphics.getWidth() / 2 - buttonSettingWidth / 2;
        int buttonSettingY = (Gdx.graphics.getHeight() / 8) * 3 - buttonSettingHeight / 2;
        buttonExit = new Rectangle(buttonSettingX, buttonSettingY, buttonSettingWidth, buttonSettingHeight);
        
        // Creamos el rectángulo que representa el botón CONFIGURACION
        int buttonScoresWidth = Gdx.graphics.getWidth() / 4;
        int buttonScoresHeight = buttonScoresWidth / 4;
        int buttonScoresX = Gdx.graphics.getWidth() / 2 - buttonScoresWidth / 2;
        int buttonScoresY = (Gdx.graphics.getHeight() / 8) * 2 - buttonScoresHeight / 2;
        buttonScores = new Rectangle(buttonScoresX, buttonScoresY, buttonScoresWidth, buttonScoresHeight);
    }

    @Override
    public void render(float delta) {
        // Limpiamos la pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Comenzamos a dibujar
        batch.begin();
        
        // Dibujamos el fondo
        batch.draw(backgroundRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        // Dibujamos el GAME OVER
        gameOver.draw(batch, "GAME OVER",  185, 370);
        gameOver.setColor(new Color(0, 1, 0, 1));
        gameOver.getData().setScale(3f, 3f); 

        
        // Dibujamos el botón JUGAR
        TextureRegion buttonStartRegion = new TextureRegion(buttonStartAgainTexture);
        batch.draw(buttonStartRegion, buttonStartAgain.x, buttonStartAgain.y, buttonStartAgain.width, buttonStartAgain.height);

        // Dibujamos el botón CONFIGURACION
        TextureRegion buttonSettingRegion = new TextureRegion(buttonExitTexture);
        batch.draw(buttonSettingRegion, buttonExit.x, buttonExit.y, buttonExit.width, buttonExit.height);

        // Dibujamos el botón PUNTUACIONES
        TextureRegion buttonScoresRegion = new TextureRegion(buttonScoresTexture);
        batch.draw(buttonScoresRegion, buttonScores.x, buttonScores.y, buttonScores.width, buttonScores.height);    
        
        
        // Terminamos de dibujar
        batch.end();

        // Comprobamos si se ha hecho clic en el botón
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonStartAgain.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                juego.irPantalla2();
            }
        }
        
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonExit.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                juego.irPantallaMenu();
            }
        }
        
        // Detectamos si ha pulsado la tecla ESCAPE
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            juego.irPantallaMenu();
        }
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        // Liberamos los recursos que hemos utilizado
        batch.dispose();
        buttonStartAgainTexture.dispose();
    }
    
}
