package andioopp.service.model;

import andioopp.common.time.Time;
import andioopp.common.math.Vector3f;
import andioopp.model.Model;
import andioopp.model.enemy.Enemy;
import andioopp.model.tower.attack.projectiles.Projectile;
import andioopp.model.world.World;

import java.util.Collection;
import java.util.Iterator;

public class EnemyProjectileCollisionService extends ModelService {

    @Override
    public void update(Model model, Time time) {
        World world = model.getWorld();
        checkProjectileHitboxes(world.getProjectiles(), world.getEnemies());
    }

    private void checkProjectileHitboxes(Collection<Projectile> projectiles, Collection<Enemy> enemies) {
        for (Iterator<Projectile> projectileIterator = projectiles.iterator(); projectileIterator.hasNext(); ) {
            Projectile projectile = projectileIterator.next();

            for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext(); ) {
                Enemy enemy = enemyIterator.next();
                Vector3f pp = projectile.getPosition();
                Vector3f ep = enemy.getPosition();
                float dm = 0.2f; //dm stands for delta max

                if (Math.abs(pp.getX() - ep.getX()) < dm && Math.abs(pp.getY() - ep.getY()) < dm) {
                    evaluateProjectileHit(projectile, enemy, projectileIterator, enemyIterator);
                }
            }
        }
    }

    /**
     * Called when a collision is detected. Checks what to do with the collision.
     *
     * @param projectile         to compare with enemy
     * @param enemy              to compare with projectile
     * @param projectileIterator to edit list of projectiles
     * @param enemyIterator      to edit list of enemies
     */
    private void evaluateProjectileHit(Projectile projectile, Enemy enemy, Iterator<Projectile> projectileIterator, Iterator<Enemy> enemyIterator) {
        if (projectile.alreadyInteractedWith.contains(enemy)) {
            return;
        }

        projectile.alreadyInteractedWith.add(enemy);
        projectileIterator.remove();

        if (enemy.canBeDamagedBy(projectile)) {
            enemy.getHealth().decrease(1);
            if (enemy.isDead()) {
                enemyIterator.remove();
            }
        }
    }
}
