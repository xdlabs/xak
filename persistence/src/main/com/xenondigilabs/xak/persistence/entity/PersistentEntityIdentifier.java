package com.xenondigilabs.xak.persistence.entity;

import java.io.Serializable;

/**
 * Entity Identifier Interface. Enforce serialization and equals comparator.
 */
public interface PersistentEntityIdentifier extends Serializable {

  /**
   * Returns true if object is same as this; else false.
   * @param identifier
   * @return
   */
  boolean equals(Object identifier);

  /**
   * Returns hashcode for identity
   * @return
   */
  int hashCode();

  /**
   * Returns serialized version.
   * @return
   */
  String toString();

}
