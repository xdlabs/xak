package com.xenondigilabs.xak.persistence.entity;

/**
 * Persistent Entity Interface. Enforce application identity for object.
 */
public interface PersistentEntity {

  /**
   * Object Identifier field.
   */
  PersistentEntityIdentifier objectId = null;

  /**
   * Returns object identifier
   * @return
   */
  PersistentEntityIdentifier getObjectId();

}
