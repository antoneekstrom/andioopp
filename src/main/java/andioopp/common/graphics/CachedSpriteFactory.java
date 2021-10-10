package andioopp.common.graphics;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link SpriteFactory} which caches a sprite when loading it for the first time.
 * @param <S> the type of sprite
 */
public class CachedSpriteFactory<S extends Sprite<?>> implements SpriteFactory<S> {

    private final SpriteFactory<S> spriteFactory;
    private final Map<String, S> cacheMap;

    public CachedSpriteFactory(SpriteFactory<S> spriteFactory) {
        this.spriteFactory = spriteFactory;
        cacheMap = new HashMap<>();
    }

    @Override
    public S get(String path) {
        if (isCached(path)) {
            return cacheMap.get(path);
        }
        else {
            return cache(path, getSpriteFactory().get(path));
        }
    }

    private S cache(String path, S sprite) {
        cacheMap.put(path, sprite);
        return sprite;
    }

    private boolean isCached(String path) {
        return cacheMap.containsKey(path);
    }

    private SpriteFactory<S> getSpriteFactory() {
        return spriteFactory;
    }
}
