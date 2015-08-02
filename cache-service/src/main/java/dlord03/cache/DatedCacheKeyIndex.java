package dlord03.cache;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import dlord03.plugin.api.data.security.SecurityIdentifier;

public class DatedCacheKeyIndex implements KeyIndex {

  private final CacheType cacheType;
  private final SecurityIdentifier securityIdentifier;
  private SortedSet<DatedCacheKey> keys;

  public DatedCacheKeyIndex(CacheType cacheType, SecurityIdentifier securityIdentifier) {
    this.cacheType = cacheType;
    this.securityIdentifier = securityIdentifier;
    this.keys = new ConcurrentSkipListSet<>(getKeyComparator());
  }

  @Override
  public CacheType getCacheType() {
    return cacheType;
  }

  @Override
  public SecurityIdentifier getSecurityIdentifier() {
    return securityIdentifier;
  }

  @Override
  public void addLatestKey(Key key, Instant before) {
    if (!key.getCacheType().equals(cacheType)) throw new IllegalArgumentException();
    if (!key.getSecurityIdentifier().equals(securityIdentifier))
      throw new IllegalArgumentException();
    keys.add((DatedCacheKey) key);
  }

  @Override
  public Key getLatestKey() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Key getLatestKey(Instant before) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Key getEndOfDayKey(LocalDate date) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addLatestKey(Key key) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addEndOfDayKey(Key key, LocalDate date) {
    // TODO Auto-generated method stub

  }

  protected Comparator<DatedCacheKey> getKeyComparator() {
    return new Comparator<DatedCacheKey>() {
      @Override
      public int compare(DatedCacheKey key1, DatedCacheKey key2) {
        return key1.getFixingDate().compareTo(key2.getFixingDate());
      }
    };
  }

}
