package juego;

import screens.PantallaMenu;
import screens.Pantalla1;
import screens.PantallaGameOver2;
import screens.Pantalla2;
import screens.PantallaGameOver1;
import screens.PantallaConfiguracion;
import com.badlogic.gdx.Game;

public class Juego extends Game {

    private PantallaGameOver1 pantallaGameOver1;
    private PantallaGameOver2 pantallaGameOver2;
    private PantallaConfiguracion pantallaConfiguracion;
    private PantallaMenu pantallaMenu;
    private Pantalla1 pantalla1;
    private Pantalla2 pantalla2;
    public boolean sonidoOn = true;
    public int vida = 100;
    
    public boolean isSonidoOn() {
        return sonidoOn;
    }

    public void setSonidoOn(boolean sonidoOn) {
        this.sonidoOn = sonidoOn;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    @Override
    public void create() {
        
        pantallaGameOver1 = new PantallaGameOver1(this);
        pantallaGameOver2 = new PantallaGameOver2(this);
        pantallaConfiguracion = new PantallaConfiguracion(this);
        pantallaMenu = new PantallaMenu(this);
        pantalla1 = new Pantalla1(this);
        pantalla2 = new Pantalla2(this);
        
        this.setScreen(pantallaMenu);
    }
    
    
    public void irPantallaMenu(){
        setScreen(pantallaMenu);
    }
    public void irPantallaGameOver1(){
        setScreen(pantallaGameOver1);
    }
    
    public void irPantallaGameOver2(){
        setScreen(pantallaGameOver2);
    }
    
    public void irPantallaConfiguracion(){
        setScreen(pantallaConfiguracion);
    }
    
    public void irPantalla1(){
        setScreen(pantalla1);
    }
    
    public void irPantalla2(){
        setScreen(pantalla2);
    }
    
    
}
