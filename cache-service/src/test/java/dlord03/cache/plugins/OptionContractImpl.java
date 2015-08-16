package dlord03.cache.plugins;

import java.time.ZonedDateTime;

import dlord03.plugin.api.data.OptionContract;
import dlord03.plugin.api.data.security.SecurityIdentifier;

public class OptionContractImpl implements OptionContract {

  private final SecurityIdentifier securityIdentifier;
  private final ZonedDateTime updatedAt;
  private final String intrumentType;
  private final String name;
  private final String expiryDate;
  private final Double strikePrice;

  public OptionContractImpl(SecurityIdentifier securityIdentifier,
    ZonedDateTime updatedAt, String intrumentType, String expiryDate, Double strikePrice,
    String name) {
    super();
    this.securityIdentifier = securityIdentifier;
    this.updatedAt = updatedAt;
    this.intrumentType = intrumentType;
    this.expiryDate = expiryDate;
    this.strikePrice = strikePrice;
    this.name = name;
  }

  @Override
  public SecurityIdentifier getSecurityIdentifier() {
    return securityIdentifier;
  }

  @Override
  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getIntrumentType() {
    return intrumentType;
  }

  @Override
  public String getExpiryDate() {
    return expiryDate;
  }

  @Override
  public Double getStrikePrice() {
    return strikePrice;
  }

  @Override
  public SecurityIdentifier getTicker() {
    return securityIdentifier;
  }

}