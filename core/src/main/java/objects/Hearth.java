package objects;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Hearth extends Image {

// Se definen dos constantes de tipo float que representan el ancho y alto de la moneda.
    private static final float COIN_WIDTH = 10;
    private static final float COIN_HEIGHT = 10;

// Se definen algunas variables para almacenar la textura de la moneda, la animación de su movimiento, la capa en la que está y el tiempo.
    private final Texture coinTexture;
    public TiledMapTileLayer layer;
    private float time;


// Constructor de la clase Coin.
    public Hearth() {
        // Se carga la textura de la moneda desde el archivo "coin.png".
        coinTexture = new Texture("hearth.png");
        // Se establece el ancho de la moneda en 1 unidad.
        setWidth(1);
        // Se establece la altura de la moneda dividiendo su altura original por su ancho original.
        setHeight(COIN_HEIGHT / COIN_WIDTH);
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

        // Se dibuja el cuadro en la posición actual de la moneda con las dimensiones establecidas.
        batch.draw(coinTexture, getX(), getY(), getWidth(), getHeight());
    }

// El método dimension devuelve un objeto Rectangle que representa las dimensiones de la moneda.
    public Rectangle dimension() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
