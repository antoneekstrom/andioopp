package andioopp.model.system.systems;

import andioopp.common.math.rectangle.ImmutableRectangle;
import andioopp.common.math.rectangle.Rectangle;
import andioopp.common.math.vector.Vector3f;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;
import andioopp.model.util.ModelCoordinate;

import java.util.Collection;
import java.util.Iterator;

/**
 * A class that handles when Enemies and Projectiles collides.
 */
public class EnemyProjectileCollisionSystem implements System<Model> {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        checkProjectileHitboxes(world.getProjectiles(), world.getEnemies(), world);
    }

    private void checkProjectileHitboxes(Collection<Projectile> projectiles, Collection<Enemy> enemies, World world) {
        for (Iterator<Projectile> projectileIterator = projectiles.iterator(); projectileIterator.hasNext(); ) {
            Projectile projectile = projectileIterator.next();

            for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                Rectangle pr = projectile.getRectangle();
                ModelCoordinate ep = enemy.getPosition();
                boolean withinX = pr.getPosition().getX() <= ep.getX() && pr.getPosition().getX() + pr.getSize().getWidth() > ep.getX();
                boolean withinY = pr.getPosition().getY() <= ep.getY() && pr.getPosition().getY() + pr.getSize().getHeight() > ep.getY();

                if(withinX && withinY) {
                    evaluateProjectileHit(projectile, enemy, world);
                }
            }
        }
    }

    /**
     * Called when a collision is detected. Checks what to do with the collision.
     *
     * @param projectile         to compare with enemy
     * @param enemy              to compare with projectile
     */
    private void evaluateProjectileHit(Projectile projectile, Enemy enemy, World world) {
        if (projectile.alreadyInteractedWith.contains(enemy)) {
            return;
        }

        projectile.alreadyInteractedWith.add(enemy);

        if (enemy.meetsAllRequirements(projectile)) {
            projectile.getHealth().decrease(1);

            if (!enemy.isImmuneAgainst(projectile)) {
                enemy.getHealth().decrease(1);
            }
        }
    }
}
