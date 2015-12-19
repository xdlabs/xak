package com.xenondigilabs.xak.persistence


import java.util.Map
import javax.jdo.{Query, Transaction, PersistenceManager}

/**
  * JSONStore like wrapper for JDO PersistenceManager
  * @param persistence_manager
  */
class PersistenceService(persistence_manager: PersistenceManager){

  /**
    * Alias for PersistenceManager.makePersistent(Object)
    * @param entity
    * @return
    */
  def insert(entity: Object): Object = {

    // Get current transaction from PersistenceManager
    val transaction: Transaction = persistence_manager.currentTransaction()

    try{
      // Begin the transaction
      transaction.begin()
      // Make entity persistent
      val result = persistence_manager.makePersistent(entity)
      // Gracefully commit the transaction
      transaction.commit()
      // Return the results
      return result
    } catch {

      // On exception
      case e: Exception => {
        // Check if transaction is still active
        if( transaction.isActive ){
          // Rollback the transaction
          transaction.rollback()
        }

        // Gracefully close the PersistenceManager
        persistence_manager.close()
      }

    }

    // Return entity passed to method
    return entity

  }

  /**
    * Alias for PersistenceManager.makePersistentAll(List[Object])
    * @param entities
    * @return
    */
  def insert(entities: List[Object]): List[Object] = {

    // Get current transaction from PersistenceManager
    val transaction: Transaction = persistence_manager.currentTransaction()

    try{
      // Begin the transaction
      transaction.begin()
      // Make entities persistent
      val result = persistence_manager.makePersistentAll(entities).asInstanceOf[List[Object]]
      // Gracefully commit the transaction
      transaction.commit()
      // Return the results
      return result
    } catch {

      // On exception
      case e: Exception => {
        // Check if transaction is still active
        if( transaction.isActive ){
          // Rollback the transaction
          transaction.rollback()
        }

        // Gracefully close the PersistenceManager
        persistence_manager.close()
      }

    }

    // Return entity list passed to method
    return entities
  }

  /**
    * Alias for PersistenceManager.newQuery()
    * Support for executing queries in PDO way.
    * @return
    */
  def newQuery(): Query[_] = {
    // Get and return new query instance from PersistenceManager
    persistence_manager.newQuery()
  }

  def find(mapping: Map[String, _]): List[Object] = {

    // Create new query from PersistenceManager
    val query: Query[_] = persistence_manager.newQuery()
    // Add mapping named parameters to query
    query.setNamedParameters(mapping)

    // Get current transaction
    val transaction: Transaction = persistence_manager.currentTransaction()
    // Try to execute query
    try{
      // Begin transaction
      transaction.begin()
      // Execute query
      val results: List[Object] = query.execute().asInstanceOf[List[Object]]
      // Commit transaction
      transaction.commit()
      // Return results
      return results

    } catch {

      case e: Exception => {
        //Check if transaction active
        if( transaction.isActive() ){
          // Rollback transaction
          transaction.rollback()
        }
        // Close PersistenceManager
        persistence_manager.close()
      }

    }

    List[Object]()

  }

  /**
    * Alias for PersistenceManager.deletePersistent(Object)
    * @param entity
    */
  def remove(entity: Object): Unit = {

    // Get current transaction from PersistenceManager
    val transaction: Transaction = persistence_manager.currentTransaction()

    try{
      // Begin the transaction
      transaction.begin()
      // Make entities persistent
      persistence_manager.deletePersistent(entity)
      // Gracefully commit the transaction
      transaction.commit()

    } catch {

      // On exception
      case e: Exception => {
        // Check if transaction is still active
        if (transaction.isActive) {
          // Rollback the transaction
          transaction.rollback()
        }

        // Gracefully close the PersistenceManager
        persistence_manager.close()
      }
    }

  }

  /**
    * Alias for PersistenceManager.deletePersistentAll(List[Object])
    * @param entities
    */
  def remove(entities: List[Object]): Unit = {

    // Get current transaction from PersistenceManager
    val transaction: Transaction = persistence_manager.currentTransaction()

    try{
      // Begin the transaction
      transaction.begin()
      // Make entities persistent
      persistence_manager.deletePersistentAll(entities)
      // Gracefully commit the transaction
      transaction.commit()

    } catch {

      // On exception
      case e: Exception => {
        // Check if transaction is still active
        if( transaction.isActive ){
          // Rollback the transaction
          transaction.rollback()
        }

        // Gracefully close the PersistenceManager
        persistence_manager.close()
      }

    }

  }

}