package com.xenondigilabs.xak.persistence.entity

trait PersistentEntity {

  protected var objectId: PersistentEntityIdentifier

  def getObjectId(): PersistentEntityIdentifier

}
