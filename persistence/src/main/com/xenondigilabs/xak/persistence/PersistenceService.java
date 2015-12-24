package com.xenondigilabs.xak.persistence;

import com.xenondigilabs.xak.persistence.utils.JDOQLUtils;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * JSONStore like wrapper for JDO PersistenceManager
 */
public class PersistenceService{

    private PersistenceManager persistence_manager;

    /**
     * @param persistence_manager
     */
    public PersistenceService(PersistenceManager persistence_manager){
        this.persistence_manager = persistence_manager;
    }

    /**
     * Alias for PersistenceManager.makePersistent(Object)
     * @param entity
     * @return
     */
    public Object insert(Object entity){

        // Get current transaction from PersistenceManager
        Transaction transaction = persistence_manager.currentTransaction();

        try{
            // Begin the transaction
            transaction.begin();
            // Make entity persistent
            Object result = persistence_manager.makePersistent(entity);
            // Gracefully commit the transaction
            transaction.commit();
            // Return the results
            return result;
        } catch(Exception e){
            // Check if transaction is still active
            if( transaction.isActive() ){
                // Rollback transaction.
                transaction.rollback();
            }
        }

        // Return entity passed to method
        return entity;

    }

    /**
     * Alias for PersistenceManager.makePersistentAll(List[Object])
     * @param entities
     * @return
     */
    public Collection<Object> insert(Collection<Object> entities){

        // Get current transaction from PersistenceManager
        Transaction transaction = persistence_manager.currentTransaction();

        try{
            // Begin the transaction
            transaction.begin();
            // Make entities persistent
            Collection<Object> result =   persistence_manager.makePersistentAll(entities);
            // Gracefully commit the transaction
            transaction.commit();
            // Return the results
            return result;
        } catch(Exception e){

            // Check if transaction is still active
            if( transaction.isActive() ){
                // Rollback the transaction
                transaction.rollback();
            }

        }

        // Return entity list passed to method
        return entities;
    }

    /**
     * Alias for PersistenceManager.newQuery()
     * Support for executing queries in PDO way.
     * @return
     */
    public Query newQuery(){
        // Get and return new query instance from PersistenceManager
        return persistence_manager.newQuery();
    }

    /**
     * Alias for PersistenceManager.newQuery(Class)
     * @param entity_class
     * @return
     */
    public Query newQuery(Class entity_class){
        // Get and return new query instance for entity_class from PersistenceManager
        return persistence_manager.newQuery(entity_class);
    }

    /**
     * Alias for PersistenceManager.getObjectId()
     * @param entity
     * @return
     */
    public Object getObjectId(Object entity){
        // Fetch and return JDO ObjectId for object
        return persistence_manager.getObjectId(entity);
    }

    /**
     * Runs named parameters query. Requires entity Class and parameters' key-value mapping.
     * @param entity_class
     * @param mapping
     * @return
     */
    public Collection<Object> find(Class entity_class, Map<String, Object> mapping){
        // Generate JDOQL query from key-value Map
        String jdoql_query = JDOQLUtils.mapToJDOQL(mapping);
        // Find and return matching results.
        return this.find(entity_class, jdoql_query);
    }

    /**
     * Runs JDOQL query. Requires entity Class and JDOQL query.
     * @param entity_class
     * @param jdoql_query
     * @return
     */
    public Collection<Object> find(Class entity_class, String jdoql_query){
        // Create new query from PersistenceManager
        Query query = persistence_manager.newQuery(entity_class, jdoql_query);
        // Get current transaction
        Transaction transaction = persistence_manager.currentTransaction();
        // Try to execute query
        try{
            // Begin transaction
            transaction.begin();
            // Execute query
            Collection<Object> results = (Collection<Object>) query.execute();
            // Commit transaction
            transaction.commit();
            // Return results
            return results;

        } catch(Exception e){

            //Check if transaction active
            if( transaction.isActive() ){
                // Rollback transaction
                transaction.rollback();
            }

        }

        return new ArrayList<Object>();

    }

    /**
     * Alias for PersistenceManager.deletePersistent(Object)
     * @param entity
     */
    public void remove(Object entity){

        // Get current transaction from PersistenceManager
        Transaction transaction = persistence_manager.currentTransaction();

        try{
            // Begin the transaction
            transaction.begin();
            // Make entities persistent
            persistence_manager.deletePersistent(entity);
            // Gracefully commit the transaction
            transaction.commit();

        } catch(Exception e){
            // Check if transaction is still active
            if (transaction.isActive()) {
                // Rollback the transaction
                transaction.rollback();
            }
        }

    }

    /**
     * Alias for PersistenceManager.deletePersistentAll(List[Object])
     * @param entities
     */
    public void remove(Collection<Object> entities){

        // Get current transaction from PersistenceManager
        Transaction transaction = persistence_manager.currentTransaction();

        try{
            // Begin the transaction
            transaction.begin();
            // Make entities persistent
            persistence_manager.deletePersistentAll(entities);
            // Gracefully commit the transaction
            transaction.commit();

        } catch(Exception e){

            // Check if transaction is still active
            if( transaction.isActive()){
                // Rollback the transaction
                transaction.rollback();
            }

        }

    }

    /**
     * Shortcut method for 'find() and remove()'
     * @param mapping
     */
    public void remove(Class entity_class, Map<String, Object> mapping){
        // Find matching objects
        Collection<Object> entities = this.find(entity_class, mapping);
        // Delete matching persistent objects
        this.remove(entities);
    }

    /**
     * Shortcut method for `find() and remove()`
     * @param entity_class
     * @param jdoql_query
     */
    public void remove(Class entity_class, String jdoql_query){
        // Find objects that fits for query
        Collection<Object> entities = this.find(entity_class, jdoql_query);
        // Delete matching objects
        this.remove(entities);
    }

}