
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
import javax.swing.JOptionPane;
import juego.Juego;

/**
 *
 * @author dieef
 */
public class PantallaMenu implements Screen {

    private final Juego juego;
    private BitmapFont felipe;
    private SpriteBatch batch;
    private Texture buttonStartTexture, buttonSettingTexture, buttonScoresTexture;
    private Rectangle buttonStart, buttonSetting, buttonScores;
    Texture backgroundTexture = new Texture("background.png");
    TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
    
    public PantallaMenu(Juego juego){
        this.juego = juego;

    }
    
    @Override
    public void show() {
        
        // Inicializamos el SpriteBatch y cargamos las texturas
        batch = new SpriteBatch();

        buttonStartTexture = new Texture("buttons/buttonStart.png");
        buttonSettingTexture = new Texture("buttons/buttonSetting.png");
        buttonScoresTexture = new Texture("buttons/buttonScores.png");

        felipe = new BitmapFont();
        
        // Creamos el rectángulo que representa el botón INICIAR
        int buttonStartWidth = Gdx.graphics.getWidth() / 4;
        int buttonStartHeight = buttonStartWidth / 4;
        int buttonStartX = Gdx.graphics.getWidth() / 2 - buttonStartWidth / 2;
        int buttonStartY = (Gdx.graphics.getHeight() / 8) * 4 - buttonStartHeight / 2;
        buttonStart = new Rectangle(buttonStartX, buttonStartY, buttonStartWidth, buttonStartHeight);
        
        // Creamos el rectángulo que representa el botón CONFIGURACION
        int buttonSettingWidth = Gdx.graphics.getWidth() / 4;
        int buttonSettingHeight = buttonSettingWidth / 4;
        int buttonSettingX = Gdx.graphics.getWidth() / 2 - buttonSettingWidth / 2;
        int buttonSettingY = (Gdx.graphics.getHeight() / 8) * 3 - buttonSettingHeight / 2;
        buttonSetting = new Rectangle(buttonSettingX, buttonSettingY, buttonSettingWidth, buttonSettingHeight);
        
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
        
        felipe.draw(batch, "FANTÁSTICO FELIPE",  100, 360);
        felipe.setColor(new Color(1, 1, 1, 1));
        felipe.getData().setScale(3f, 3f); 
        
        // Dibujamos el botón JUGAR
        TextureRegion buttonStartRegion = new TextureRegion(buttonStartTexture);
        batch.draw(buttonStartRegion, buttonStart.x, buttonStart.y, buttonStart.width, buttonStart.height);

        // Dibujamos el botón CONFIGURACION
        TextureRegion buttonSettingRegion = new TextureRegion(buttonSettingTexture);
        batch.draw(buttonSettingRegion, buttonSetting.x, buttonSetting.y, buttonSetting.width, buttonSetting.height);

        // Dibujamos el botón PUNTUACIONES
        TextureRegion buttonScoresRegion = new TextureRegion(buttonScoresTexture);
        batch.draw(buttonScoresRegion, buttonScores.x, buttonScores.y, buttonScores.width, buttonScores.height);

        // Terminamos de dibujar
        batch.end();

        // Comprobamos si se ha hecho clic en el botón
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonStart.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                juego.irPantalla1();
            }
        }
        
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonSetting.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                juego.irPantallaConfiguracion();
            }
        }
        
        // Detectamos si ha pulsado la tecla ESCAPE
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
        buttonStartTexture.dispose();
    }
    
}
