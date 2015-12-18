package com.xenondigilabs.xak.persistence

import javax.jdo.Query

import com.xenondigilabs.xak.persistence.entity.PersistentEntity

trait PersistenceService{

  def PersistenceService(persistentEntityClass: Class)

  def create(persistentEntity: PersistentEntity): PersistentEntity;

  def create(persistentEntities: Array[PersistentEntity]): Array[PersistentEntity];

  def read(): Iterator[PersistentEntity];

  def read(query: Query): Iterator[PersistentEntity];

  def delete(persistentEntity: PersistentEntity): Unit;

  def delete(query: Query): Unit;

}
