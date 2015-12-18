package com.xenondigilabs.xak.persistence

import java.util.Collection
import javax.jdo.PersistenceManager
import javax.jdo.Query

import com.xenondigilabs.xak.persistence.entity.PersistentEntity

trait PersistenceService{

  def setPersistenceManager(persistence_manager: PersistenceManager): Unit

  def getPersistenceManager(): PersistenceManager

  def create(persistent_entity: PersistentEntity): Boolean;

  def create(persistent_entities: Collection[PersistentEntity]): Boolean;

  def read(query: Query[Object]): List[PersistentEntity];

  def delete(persistent_entity: PersistentEntity): Boolean;

  def delete(query: Collection[PersistentEntity]): Boolean;

  def delete(query: Query[Object]): Boolean;

}
