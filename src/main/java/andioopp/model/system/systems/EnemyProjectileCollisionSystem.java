package andioopp.model.system.systems;

import andioopp.common.math.vector.Vector3f;
import andioopp.common.time.Time;
import andioopp.model.Model;
import andioopp.model.domain.enemy.Enemy;
import andioopp.model.domain.entity.DroppedCoinEntity;
import andioopp.model.domain.tower.attack.projectiles.Projectile;
import andioopp.model.domain.world.World;
import andioopp.model.system.System;

import java.util.Collection;
import java.util.Iterator;

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
                Vector3f pp = projectile.getPosition();
                Vector3f ep = enemy.getPosition();
                float dm = 0.2f; //dm stands for delta max

                if (Math.abs(pp.getX() - ep.getX()) < dm && Math.abs(pp.getY() - ep.getY()) < dm) {
                    evaluateProjectileHit(projectile, enemy, projectileIterator, enemyIterator, world);
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
    private void evaluateProjectileHit(Projectile projectile, Enemy enemy, Iterator<Projectile> projectileIterator, Iterator<Enemy> enemyIterator, World world) {
        if (projectile.alreadyInteractedWith.contains(enemy)) {
            return;
        }

        projectile.alreadyInteractedWith.add(enemy);
        projectileIterator.remove();

        if (enemy.canBeDamagedBy(projectile)) {
            enemy.getHealth().decrease(1);
            if (enemy.isDead()) {
                world.getDroppedCoins().add(new DroppedCoinEntity(enemy.getPosition(), enemy.getReward()));
                enemyIterator.remove();
            }
        }
    }
}
