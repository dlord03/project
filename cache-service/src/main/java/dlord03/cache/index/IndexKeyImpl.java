package dlord03.cache.index;

import dlord03.cache.data.DataType;
import dlord03.cache.data.SimpleDataKeyImpl;
import dlord03.plugin.api.data.security.SecurityIdentifier;

public class IndexKeyImpl extends SimpleDataKeyImpl implements IndexKey {

  private static final long serialVersionUID = -6938651146376002358L;

  private final IndexType indexType;

  public IndexKeyImpl(IndexType indexType, DataType dataType,
    SecurityIdentifier securityIdentifier) {
    super(dataType, securityIdentifier);
    this.indexType = indexType;
  }

  @Override
  public IndexType getIndexType() {
    return indexType;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof IndexKeyImpl))
      return false;
    final IndexKeyImpl other = (IndexKeyImpl) obj;
    return (super.equals(obj) && this.indexType.equals(other.indexType));
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + indexType.hashCode();
    return result;
  }

}
