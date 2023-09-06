
package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
public class PantallaConfiguracion implements Screen {

    private final Juego juego;
    
    private SpriteBatch batch;
    private Texture buttonExitTexture, buttonSoundTextureActive, buttonSoundTextureDesactive;
    private Texture buttonAumentarTexture, buttonReducirTexture;
    private Rectangle buttonExit, buttonSound, buttonVida;
    private BitmapFont sonidoText, vidaText;
    Texture backgroundTexture = new Texture("background_settings.png");
    TextureRegion backgroundRegion = new TextureRegion(backgroundTexture);
    
    public PantallaConfiguracion(Juego juego){
        this.juego = juego;
    }
    
    @Override
    public void show() {
        
        // Inicializamos el SpriteBatch y cargamos las texturas
        batch = new SpriteBatch();

        buttonExitTexture = new Texture("buttons/buttonExit.png");
        buttonSoundTextureActive = new Texture("buttons/buttonActivar.png");
        buttonSoundTextureDesactive = new Texture("buttons/buttonDesactivar.png");
        buttonAumentarTexture = new Texture("buttons/buttonAumentar.png");
        buttonReducirTexture = new Texture("buttons/buttonReducir.png");
        
        // Creamos el rectángulo que representa el botón CONFIGURACION
        int buttonExitWidth = Gdx.graphics.getWidth() / 4;
        int buttonExitHeight = buttonExitWidth / 4;
        int buttonExitX = Gdx.graphics.getWidth() / 2 - buttonExitWidth / 2;
        int buttonExitY = (Gdx.graphics.getHeight() / 8) * 2 - buttonExitHeight / 2;
        buttonExit = new Rectangle(buttonExitX, buttonExitY, buttonExitWidth, buttonExitHeight);

        // Creamos el rectángulo que representa el botón SONIDO
        int buttonSoundWidth = Gdx.graphics.getWidth() / 4;
        int buttonSoundHeight = buttonSoundWidth / 4;
        int buttonSoundX = Gdx.graphics.getWidth() / 2 - buttonSoundWidth / 2;
        int buttonSoundY = (Gdx.graphics.getHeight() / 8) * 5 - buttonSoundHeight / 2;
        buttonSound = new Rectangle(buttonSoundX, buttonSoundY, buttonSoundWidth, buttonSoundHeight);

        // Creamos el rectángulo que representa el botón VIDA
        int buttonVidaWidth = Gdx.graphics.getWidth() / 4;
        int buttonVidaHeight = buttonSoundWidth / 4;
        int buttonVidaX = Gdx.graphics.getWidth() / 2 - buttonVidaWidth / 2;
        int buttonVidaY = (Gdx.graphics.getHeight() / 8) * 3 - buttonVidaHeight / 2;
        buttonVida = new Rectangle(buttonVidaX, buttonVidaY, buttonVidaWidth, buttonVidaHeight);

        sonidoText = new BitmapFont();
        vidaText = new BitmapFont();
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
        
        // Dibujamos el botón SALIR
        TextureRegion buttonScoresRegion = new TextureRegion(buttonExitTexture);
        batch.draw(buttonScoresRegion, buttonExit.x, buttonExit.y, buttonExit.width, buttonExit.height);
        
        // Dibujamos el botón SONIDO
        // Dibujamos el texto del botón
        TextureRegion buttonSoundRegion;
        if(juego.sonidoOn == false){
            sonidoText.draw(batch, "Sonido Desactivado", 130, 370);
            buttonSoundRegion = new TextureRegion(buttonSoundTextureActive);
        }else{
            sonidoText.draw(batch, "Sonido Activado", 155, 370);
            buttonSoundRegion = new TextureRegion(buttonSoundTextureDesactive);
        }
        batch.draw(buttonSoundRegion, buttonSound.x, buttonSound.y, buttonSound.width, buttonSound.height);
        //font.setColor(new Color(0, 1, 0, 1));
        sonidoText.getData().setScale(3f, 3f);
        
        
        // Dibujamos el botón VIDA
        // Dibujamos el texto del botón
        TextureRegion buttonVidaRegion;
        if(juego.getVida() == 100){
            sonidoText.draw(batch, "Vida 100%", 220, 250);
            buttonVidaRegion = new TextureRegion(buttonAumentarTexture);
        }else{
            sonidoText.draw(batch, "Vida 200%", 220, 250);
            buttonVidaRegion = new TextureRegion(buttonReducirTexture);
        }
        batch.draw(buttonVidaRegion, buttonVida.x, buttonVida.y, buttonVida.width, buttonVida.height);
        //font.setColor(new Color(0, 1, 0, 1));
        vidaText.getData().setScale(3f, 3f);
        
         
        
        
        // Terminamos de dibujar
        batch.end();

        // Comprobamos si se ha hecho clic en el botón
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonSound.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                // Llamar al método desactivarSonido
                desactivarSonido();
            }
        }
        
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.input.getY();
            if (buttonVida.contains(clickX, Gdx.graphics.getHeight() - clickY)) {
                // Llamar al método desactivarSonido
                cambiarVida();
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

    // Método para desactivar el sonido
    private void desactivarSonido(){
        if(juego.isSonidoOn() == true){
            juego.setSonidoOn(false);
        }else{
            juego.setSonidoOn(true);
        }
    }


    private void cambiarVida(){
        if(juego.getVida() == 100){
            juego.setVida(200);
        }else{
            juego.setVida(100);
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
    }
    
}
