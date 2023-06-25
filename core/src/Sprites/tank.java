package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class tank extends Sprite {
    public World world;
    public Body b2body;
    public int thrown_flag;
    public TextureRegion tank_stand;

    public tank(World world, float x, float y, int flag, int i){
        this.world = world;
        this.thrown_flag = flag;
//        tank_stand = new TextureRegion(getTexture(),);

        defineTank(x,y, i);
    }
    public void defineTank(float x, float y, int i){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.fixedRotation=false;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
//        shape.f
        fdef.friction =0.6f;
        shape.setRadius(20);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        float speed,angle;

        Vector2 startingVelocity =new Vector2(50,5);

        startingVelocity.rotateDeg((float) 120 - 45);
        b2body.setLinearVelocity(startingVelocity);

        if(i==1) {
            Sprite tank_sprite = new Sprite(new Texture(("helios.png")));
            tank_sprite.setSize(120, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight()/2, tank_sprite.getWidth()/2);
            b2body.setUserData(tank_sprite);
        }
        else if(i==2){
            Sprite tank_sprite = new Sprite(new Texture(("Abrams.png")));
            tank_sprite.setSize(150, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight()/2, tank_sprite.getWidth()/2);
            b2body.setUserData(tank_sprite);

        }
        else if(i==3){
            Sprite tank_sprite = new Sprite(new Texture(("buratino.png")));
            tank_sprite.setSize(150, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight()/2, tank_sprite.getWidth()/2);
            b2body.setUserData(tank_sprite);
        }
        else if(i==4){
            Sprite tank_sprite = new Sprite(new Texture(("helios1.png")));
            tank_sprite.setSize(150, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight()/2, tank_sprite.getWidth()/2);
            b2body.setUserData(tank_sprite);
        }
        else if(i==5) {
            Sprite tank_sprite = new Sprite(new Texture(("Abrams-Flip.png")));
            tank_sprite.setSize(120, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight() / 2, tank_sprite.getWidth() / 2);
            b2body.setUserData(tank_sprite);
        }
        else if(i==6){
            Sprite tank_sprite = new Sprite(new Texture(("buratino1.png")));
            tank_sprite.setSize(150, 150);

            tank_sprite.setOrigin(tank_sprite.getHeight()/2, tank_sprite.getWidth()/2);
            b2body.setUserData(tank_sprite);
        }

        shape.dispose();



    }
    public void missile_thrown(){
        thrown_flag = 1;
    }
}
