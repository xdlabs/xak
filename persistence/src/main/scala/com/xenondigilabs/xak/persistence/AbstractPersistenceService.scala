package com.xenondigilabs.xak.persistence

import javax.jdo.Query

import com.xenondigilabs.xak.persistence.entity.PersistentEntity

abstract class AbstractPersistenceService extends PersistenceService{

  def AbstractPersistenceService(persistentEntityClass: Class) = {

  }

  override def create(persistentEntity: PersistentEntity): PersistentEntity = ???

  override def delete(persistentEntity: PersistentEntity): Unit = ???

  override def delete(query: Query): Unit = ???

  override def read(): Iterator[PersistentEntity] = ???

  override def read(query: Query): Iterator[PersistentEntity] = ???

  override def create(persistentEntities: Array[PersistentEntity]): Array[PersistentEntity] = ???

}
