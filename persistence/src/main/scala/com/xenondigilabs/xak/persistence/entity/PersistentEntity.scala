package com.xenondigilabs.xak.persistence.entity

trait PersistentEntity {

  protected var objectId: PersistentEntityIdentifier

  def PersistentEntity(objectId: PersistentEntityIdentifier)

  def getObjectId(): PersistentEntityIdentifier

}
