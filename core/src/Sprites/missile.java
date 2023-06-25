package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;


class ProjectileEquation {

    public float gravity;
    public Vector2 startVelocity = new Vector2();
    public Vector2 startPoint = new Vector2();
    public Vector2 gravityVec = new Vector2(0,-10f);

    public float getX(float n) {
        return startVelocity.x * (n * 1/30f) + startPoint.x;
    }

    public float getY(float n) {
        float t = 1/30f * n;
        return 0.5f * gravity * t * t + startVelocity.y * t + startPoint.y;
    }


}
class Controller  {

    public float power = 50f;
    public float angle = 0f;

}

class TrajectoryActor extends Actor {

    private Controller controller;
    private ProjectileEquation projectileEquation;
    private Sprite trajectorySprite;

    public int trajectoryPointCount = 30;
    public float timeSeparation = 1f;

    public TrajectoryActor(Controller controller, float gravity, Sprite trajectorySprite) {
        this.controller = controller;
        this.trajectorySprite = trajectorySprite;
        this.projectileEquation = new ProjectileEquation();
        this.projectileEquation.gravity = gravity;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        projectileEquation.startVelocity.set(controller.power, 0f);
        projectileEquation.startVelocity.rotate(controller.angle);
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        float t = 0f;

        float width = this.getWidth();
        float height = this.getHeight();

        float timeSeparation = this.timeSeparation;

        int lt = 10;
        for (int i = 0;i<lt ; i++) {
            float x = this.getX() + projectileEquation.getX(t);
            float y = this.getY() + projectileEquation.getY(t);

            batch.setColor(this.getColor());
            batch.draw(trajectorySprite, x, y, width, height);

            t += timeSeparation;
        }
    }

    public Actor hit(float x, float y) {
        return null;
    }
}
//    @Override
//    public void draw(SpriteBatch batch, float parentAlpha) {
//        float t = 0f;
//        float width = this.getWidth();
//        float height = this.getHeight();
//
//        float timeSeparation = this.timeSeparation;
//
//        for (int i = 0; i < trajectoryPointCount; i += timeSeparation) {
//            //projectileEquation.getTrajectoryPoint(this.getX(), this.getY(), i);
//            float x = this.getX() + projectileEquation.getX(i);
//            float y = this.getY() + projectileEquation.getY(i);
//
//            batch.setColor(this.getColor());
//            if (trajectorySprite != null) batch.draw(trajectorySprite, x, y, width, height);
//
//        }
//    }
//
//    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//        if(button==1 || world.showingDialog)return false;
//        touchPos.set(x, y);
//        float angle = touchPos.sub(playerCannon.position).angle();
//        if(angle > 270 ) {
//            angle = 0;
//        }
//        else if(angle >70) {
//            angle = 70;
//        }
//        playerCannon.setAngle(angle);
//        world.trajPath.controller.angle = angle;
//        float radians =  (float) angle * MathUtils.degreesToRadians;
//        float ballSpeed = touchPos.sub(playerCannon.position).len()*12;
//        world.trajPath.projectileEquation.startVelocity.x = (float) (Math.cos(radians) * ballSpeed);
//        world.trajPath.projectileEquation.startVelocity.y = (float) (Math.sin(radians) * ballSpeed);
//        return true;
//    }
//
//    public CannonBall(float x, float y, float width, float height, float damage, World world,  Cannon cannonOwner) {
//        super(x, y, width, height, damage, world);
//        active = false;
//        shape = new CircleShape();
//        shape.setRadius(width/2);
//
//        FixtureDef fd = new FixtureDef();
//        fd.shape = shape;
//        fd.density = 4.5f;
//        if(cannonOwner.isEnemy) { //Enemy cannon balls cannot hit other enemy cannons just the player
//            fd.filter.groupIndex = -16;
//        }
//        bodyDef.type = BodyType.DynamicBody;
//        bodyDef.position.set(this.position);
//
//        body = world.createBody(bodyDef);
//        body.createFixture(fd);
//        body.setUserData(this);
//        body.setBullet(true);
//        this.cannonOwner = cannonOwner;
//        this.hitByBall = null;
//        this.particleEffect = null;
//    }
//
//    private CannonBall createCannonBall(float radians, float ballSpeed, float radius, float damage)
//    {
//        CannonBall cannonBall =  new CannonBall(CannonEnd().x, CannonEnd().y, radius * ballSizeMultiplier, radius * ballSizeMultiplier, damage, this.world, this);
//        cannonBall.velocity.x = (float) (Math.cos(radians) * ballSpeed);
//        //cannonBall.velocity.x = (float) ((Math.sqrt(10) * Math.sqrt(29) *
//        //  Math.sqrt((Math.tan(cannon.angle)Math.tan(cannon.angle))+1)) / Math.sqrt(2 * Math.tan(cannon.angle) - (2 * 10 * 2)/29)) -1f;
//        cannonBall.velocity.y = (float) (Math.sin(radians) * ballSpeed);
//        cannonBall.active = true;
//        //cannonBall.body.applyLinearImpulse(cannonBall.velocity, cannonBall.position);
//        cannonBall.body.applyForce(cannonBall.velocity, cannonBall.position );
//        return cannonBall;
//    }
//
//
//trajPath = new TrajectoryActor(-10f);
//        trajPath.setX(playerCannon.CannonEnd().x);
//        trajPath.setY(playerCannon.CannonEnd().y);
//        trajPath.setWidth(10f);
//        trajPath.setHeight(10f);
//        stage.addActor(trajPath);


public class missile extends Sprite {
    public World world;
    public Body b2body;

    public missile(World world){
        this.world = world;
        definemissile();
    }
    public Body definemissile(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(300+20,500+20);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

//        b2body.

        fdef.shape = shape;
        b2body.createFixture(fdef);
        Sprite boxsprite =new Sprite (new Texture("m2.png"));
        boxsprite.setSize(120,67);
        boxsprite.setOrigin(boxsprite.getHeight()/2,boxsprite.getWidth()/2);
        b2body.setUserData((boxsprite));
        shape.dispose();
        return b2body;


}
}
