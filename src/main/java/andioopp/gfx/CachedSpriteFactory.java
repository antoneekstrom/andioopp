package andioopp.gfx;

import java.util.HashMap;
import java.util.Map;

public class CachedSpriteFactory<S extends Sprite<?>> extends SpriteFactory<S> {
    
    private final Map<String, S> cacheMap;

    public CachedSpriteFactory(SpriteSupplier<S> spriteSupplier) {
        super(spriteSupplier);
        cacheMap = new HashMap<>();
    }

    @Override
    public S get(String path) {
        if (isCached(path)) {
            return cacheMap.get(path);
        }
        else {
            return cache(path, super.get(path));
        }
    }

    private S cache(String path, S sprite) {
        cacheMap.put(path, sprite);
        return sprite;
    }

    private boolean isCached(String path) {
        return cacheMap.containsKey(path);
    }


}
