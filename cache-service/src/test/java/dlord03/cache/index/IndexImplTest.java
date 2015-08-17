package dlord03.cache.index;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dlord03.cache.data.DataType;
import dlord03.cache.support.SerialisationUtils;
import dlord03.cache.data.DataKey;
import dlord03.cache.data.DataKeyImpl;
import dlord03.plugin.api.data.security.IdentifierScheme;
import dlord03.plugin.api.data.security.SecurityIdentifier;

public class IndexImplTest {

  private Index index;
  private DataType dataType;
  private SecurityIdentifier identifier;

  @Before
  public void setUp() {
    identifier = new SecurityIdentifier(IdentifierScheme.RIC, "BT.L");
    dataType = DataType.OPTION;
    index = new IndexImpl(dataType, identifier);
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkCacheType() {
    ZonedDateTime now = ZonedDateTime.now();
    DataKeyImpl key =
      new DataKeyImpl(DataType.DIVIDEND, identifier, now.minusHours(1).toInstant());
    index.addLatestKey(key, now.toInstant());
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkIdentifier() {
    SecurityIdentifier wrongIdentifier;
    wrongIdentifier = new SecurityIdentifier(IdentifierScheme.RIC, "VOD.L");
    ZonedDateTime now = ZonedDateTime.now();
    DataKeyImpl key =
      new DataKeyImpl(dataType, wrongIdentifier, now.minusHours(1).toInstant());
    index.addLatestKey(key, now.toInstant());
  }
  
  @Test
  public void refindEndOfDayKey() {
    ZonedDateTime now = ZonedDateTime.now();
    LocalDate date = LocalDate.from(now);
    Instant recordDate = now.minusDays(7).toInstant();
    DataKey keyIn;
    keyIn = new DataKeyImpl(dataType, identifier, recordDate);
    index.addEndOfDayKey(keyIn, date);
    DataKey keyOut = index.getEndOfDayKey(date.minusDays(1));
    Assert.assertEquals(keyIn, keyOut);
  }


  @Test
  public void refindLatestKey() {
    ZonedDateTime now = ZonedDateTime.now();
    DataKeyImpl keyIn = new DataKeyImpl(dataType, identifier, now.minusHours(1).toInstant());
    index.addLatestKey(keyIn, now.toInstant());
    index = SerialisationUtils.roundTrip(index);
    DataKey keyOut = index.getLatestKey(now.minusMinutes(10).toInstant());
    Assert.assertEquals(keyIn, keyOut);
  }

  @Test
  public void refindIntradayKey() {
    ZonedDateTime now = ZonedDateTime.now();
    DataKeyImpl keyIn = new DataKeyImpl(dataType, identifier, now.minusHours(1).toInstant());
    index.addLatestKey(keyIn, now.minusMinutes(10).toInstant());
    index = SerialisationUtils.roundTrip(index);
    DataKey keyOut = index.getLatestKey(now.minusMinutes(20).toInstant());
    Assert.assertEquals(keyIn, keyOut);
  }

}
