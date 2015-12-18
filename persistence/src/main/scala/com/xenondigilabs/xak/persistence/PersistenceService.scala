package com.xenondigilabs.xak.persistence

import javax.jdo.{Transaction, PersistenceManager}

class PersistenceService(persistence_manager: PersistenceManager){

  /**
    * Alias for PersistenceManager.makePersistent()
    * @param persistent_object
    * @return
    */
  def insert(persistent_object: Object): Object = {

    // Get current transaction from PersistenceManager
    var transaction: Transaction = persistence_manager.currentTransaction()

    try{
      // Begin the transaction
      transaction.begin()
      // Make entity persistent
      persistence_manager.makePersistent(persistent_object)
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

    // Return entity passed to method
    persistent_object

  }

}