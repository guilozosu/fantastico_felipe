package characters;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class PinkGuy extends Image {
    TextureRegion stand, jump;
    Animation walk;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    public TiledMapTileLayer layer;
    boolean irDerecha = true;

    final float GRAVITY = -2.5f;
    final float MAX_VELOCITY = 5f;
    final float DAMPING = 0.87f;

    public PinkGuy() {
        final float width = 32;
        final float height = 32;
        this.setSize(1.5f, 1.5f);

        Texture koalaTexture = new Texture("pinkGuy.png");
        TextureRegion[][] grid = TextureRegion.split(koalaTexture, (int) width, (int) height);

        stand = grid[0][0];
        jump = grid[0][1];
        walk = new Animation(0.05f, grid[0][0], grid[0][1], grid[0][2], grid[0][3], grid[0][4], grid[0][5], grid[0][6], grid[0][7], grid[0][8], grid[0][9], grid[0][10], grid[0][11]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        xVelocity = MAX_VELOCITY;
    }

    @Override
    public void act(float delta) {
        time = time + delta;
        
        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;

        int endX = (int) (this.getX() + this.getWidth());
        
        if (irDerecha == false) {
            endX = (int) (this.getX());
        }
        
        if (layer.getCell(endX, (int) y) != null) {
            irDerecha = !irDerecha;
            isFacingRight = !isFacingRight;
        }
        
        if (irDerecha) {
            this.setPosition(x + xChange, y);
        }
        else {
            this.setPosition(x - xChange, y);
        }

    }

    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;

        if (yVelocity != 0) {
            frame = jump;
        } else if (xVelocity != 0) {
            frame = (TextureRegion) walk.getKeyFrame(time);
        } else {
            frame = stand;
        }

        if (isFacingRight == true) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
    }

    private boolean canMoveTo(float startX, float startY, boolean shouldDestroy) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();

        int x = (int) startX;
        while (x < endX) {

            int y = (int) startY;
            
            if (layer.getCell(x, y) != null) {
                return false;
            }
            
            if (irDerecha) {
                irDerecha = false;
                x = x - 1;
            }
            else {
                irDerecha = true;
                x = x + 1;
            }
        }
        
        return true;
    }
    
    public Rectangle dimension() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
