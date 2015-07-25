package dlord03.cache;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import dlord03.plugin.api.data.security.SecurityIdentifier;

public class DateCacheKeyIndex {

  private final CacheType cacheType;
  private final SecurityIdentifier securityIdentifier;
  private SortedSet<Key> keys;

  public DateCacheKeyIndex(CacheType cacheType, SecurityIdentifier securityIdentifier) {

    this.cacheType = cacheType;
    this.securityIdentifier = securityIdentifier;
    this.keys = new ConcurrentSkipListSet<>(new Comparator<Key>() {

      @Override
      public int compare(Key key1, Key key2) {
        return key1.getUpdatedAt().compareTo(key2.getUpdatedAt());
      }
    });

  }

  public void add(Key key) {
    if (!key.getCacheType().equals(cacheType)) throw new IllegalArgumentException();
    if (!key.getSecurityIdentifier().equals(securityIdentifier)) throw new IllegalArgumentException();
    keys.add(key);
  }

  public CacheType getCacheType() {
    return cacheType;
  }

  public SecurityIdentifier getSecurityIdentifier() {
    return securityIdentifier;
  }

}
