package com.xenondigilabs.xak.persistence

import java.util.Collection
import javax.jdo.Transaction
import javax.jdo.Query
import javax.jdo.PersistenceManager

import com.xenondigilabs.xak.persistence.entity.PersistentEntity

abstract class AbstractPersistenceService extends PersistenceService{

  var persistence_manager: PersistenceManager = null

  def setPersistenceManager(persistence_manager: PersistenceManager): Unit = {
    this.persistence_manager = persistence_manager
  }

  def getPersistenceManager(): PersistenceManager = {
    this.persistence_manager
  }

  def create(persistent_entity: PersistentEntity): Boolean = {
    var transaction: Transaction = this.persistence_manager.currentTransaction()
    try{
      transaction.begin()
      this.persistence_manager.makePersistent(persistent_entity)
      transaction.commit()
      true
    }catch{
      case e: Exception => {
        if (transaction.isActive){
          transaction.rollback()
        }
        this.persistence_manager.close()
        false
      }
    }
  }

  def create(persistent_entities: Collection[PersistentEntity]): Boolean = {
    var transaction: Transaction = this.persistence_manager.currentTransaction()
    try{
      transaction.begin()
      this.persistence_manager.makePersistentAll(persistent_entities)
      transaction.commit()
      true
    }catch{
      case e: Exception => {
        if (transaction.isActive){
          transaction.rollback()
        }
        this.persistence_manager.close()
        false
      }
    }
  }

  def read(query: Query[Object]): List[PersistentEntity] = {
    var transaction: Transaction = this.persistence_manager.currentTransaction()
    try{
      transaction.begin()
      val items: List[PersistentEntity] = (query.execute()).asInstanceOf[List[PersistentEntity]]
      transaction.commit()
      items
    }catch{
      case e: Exception => {
        if (transaction.isActive){
          transaction.rollback()
        }
        this.persistence_manager.close()
        List[PersistentEntity]()
      }
    }
  }


  def delete(persistent_entity: PersistentEntity): Boolean = {
    var transaction: Transaction = this.persistence_manager.currentTransaction()
    try{
      transaction.begin()
      this.persistence_manager.deletePersistent(persistent_entity)
      transaction.commit()
      true
    }catch{
      case e: Exception => {
        if (transaction.isActive){
          transaction.rollback()
        }
        this.persistence_manager.close()
        false
      }
    }
  }

  def delete(persistent_entities: Collection[PersistentEntity]): Boolean = {
    var transaction: Transaction = this.persistence_manager.currentTransaction()
    try{
      transaction.begin()
      this.persistence_manager.deletePersistentAll(persistent_entities)
      transaction.commit()
      true
    }catch{
      case e: Exception => {
        if (transaction.isActive){
          transaction.rollback()
        }
        this.persistence_manager.close()
        false
      }
    }
  }

  def delete(query: Query[Object]): Boolean;

}
