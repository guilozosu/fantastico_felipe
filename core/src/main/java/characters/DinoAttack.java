package characters;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class DinoAttack extends Image {

// Se definen dos constantes de tipo float que representan el ancho y alto de la moneda.
    private static final float COIN_WIDTH = 96;
    private static final float COIN_HEIGHT = 96;

// Se definen algunas variables para almacenar la textura de la moneda, la animación de su movimiento, la capa en la que está y el tiempo.
    private final Texture coinTexture;
    private final Animation<TextureRegion> movement;
    public TiledMapTileLayer layer;
    private float time;

// Constructor de la clase Coin.
    public DinoAttack() {
        // Se carga la textura de la moneda desde el archivo "coin.png".
        coinTexture = new Texture("dino.png");
        // Se establece el ancho de la moneda en 1 unidad.
        setWidth(4);
        // Se establece la altura de la moneda dividiendo su altura original por su ancho original.
        setHeight(4);

        // Se divide la textura de la moneda en un conjunto de regiones de textura del tamaño definido por las constantes COIN_WIDTH y COIN_HEIGHT.
        TextureRegion[][] grid = TextureRegion.split(coinTexture, (int) COIN_WIDTH, (int) COIN_HEIGHT);

        // Se crea una animación de movimiento a partir de las regiones de textura obtenidas.
        // La animación tiene un tiempo de duración de 0.15 segundos por cuadro y se establece en modo "LOOP_PINGPONG".
        movement = new Animation<>(0.15f, grid[0][0], grid[0][1], grid[0][2], grid[0][3], grid[0][4], grid[0][5]);
        movement.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

// El método act se ejecuta en cada ciclo de actualización del juego.
    @Override
    public void act(float delta) {
        super.act(delta);
        // Se actualiza el tiempo de la animación con el tiempo transcurrido desde la última actualización.
        time += delta;
        // Se establece la posición de la moneda en sus coordenadas x e y actuales.
        setPosition(getX(), getY());
    }

// El método draw se encarga de dibujar la moneda en la pantalla.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        // Se obtiene el cuadro actual de la animación.
        TextureRegion frame = movement.getKeyFrame(time);
        // Se dibuja el cuadro en la posición actual de la moneda con las dimensiones establecidas.
        batch.draw(frame, getX(), getY(), getWidth(), getHeight());
    }

// El método dimension devuelve un objeto Rectangle que representa las dimensiones de la moneda.
    public Rectangle dimension() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
