package andioopp.model.domain.tower.attack.projectiles;

import andioopp.common.math.dimension.Dimension;
import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.time.Time;
import andioopp.model.domain.damage.DamageSource;
import andioopp.model.domain.stats.Health;
import andioopp.model.util.ModelCoordinate;

public class FlashlightProjectile extends Projectile {

    private static Dimension SIZE = new Dimension(3 ,1);
    float birthtime = 0;

    public FlashlightProjectile(Vector3f position, DamageSource damageSource) {
        super("flashlight.png", new ImmutableRectangle(position, SIZE), damageSource, new Health(100));
    }

    @Override
    public void update(Time time) {
        if(birthtime == 0){
            birthtime = time.getTime();
        }
        if(time.getTime() - birthtime > 0.7f ) {
            getRectangle().setPosition(new ModelCoordinate(100));
        }
    }
}
