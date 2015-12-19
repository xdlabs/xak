package com.xenondigilabs.xak.examples.persistence;

import com.xenondigilabs.xak.persistence.PersistenceService;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.Collection;
import java.util.Properties;

/**
 * Example use Datanucleus data access platform and XAK Persistence.
 * Demostrates use case; how to keep objects persistent on PostgreSQL database.
 */
public class PostgreSQLPersistenceExample{

    /**
     * @param args
     */
    public static void main(String[] args){


        // Create new properties configuration.
        Properties conf = new Properties();

        String DB_HOST = args[0];
        String DB_NAME = args[1];
        String DB_USER = args[2];
        String DB_PASS = args[3];

        // Set configuration parameters
        conf.setProperty("datanucleus.schema.autoCreateTables", "true");
        conf.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
        conf.setProperty("javax.jdo.option.ConnectionURL","jdbc:postgresql://" + DB_HOST + "/" + DB_NAME);
        conf.setProperty("javax.jdo.option.ConnectionDriverName","org.postgresql.Driver");
        conf.setProperty("javax.jdo.option.ConnectionUserName",DB_USER);
        conf.setProperty("javax.jdo.option.ConnectionPassword", DB_PASS);

        // Use JDO API to get PersistenceManager instance
        PersistenceManagerFactory persistence_manager_factory = JDOHelper.getPersistenceManagerFactory(conf);
        PersistenceManager persistence_manager = persistence_manager_factory.getPersistenceManager();

        // -----------------------------------------
        // application logic
        // -----------------------------------------

        // Create Person instances.
        Person abhay = new Person("Abhay", "Arora", "abhay@xenondigilabs.com");
        Person navita = new Person("Navita", "Gogna", "xnavgog@gmail.com");
        Person gursimran = new Person("Gursimran", "Singh", "gursimran@xenondigilabs.com");
        Person parveen = new Person("Parveen", "Bhandari", "xparvbha@gmail.com");

        // Creating complex friendship relation among Person instances ;)
        abhay.addFriend(navita);
        abhay.addFriend(gursimran);
        abhay.addFriend(parveen);

        navita.addFriend(parveen);

        parveen.addFriend(abhay);
        parveen.addFriend(gursimran);

        // -----------------------------------------
        // now lets make them persistent.
        // -----------------------------------------

        // Create a PersistenceService instance. PersistenceService wraps JDO PersistenceManager to provide simplified API.
        PersistenceService pgsql = new PersistenceService(persistence_manager);

        pgsql.insert(abhay);
        pgsql.insert(navita);
        pgsql.insert(gursimran);
        pgsql.insert(parveen);

        /*

        You can insert multiple instances by making a Collection<Person>

        ArrayList<Person> people = new ArrayList<Person>(4);
        people.add(abhay);
        people.add(navita);
        people.add(gursimran);
        people.add(parveen);

        pgsql.insert(people);  // Makes all persistent.

         */

        // Now accessing persistent entities.

        // Match where first_name = Abhay
        Collection<Object> result = pgsql.find(Person.class, "first_name == \"Abhay\"");
        for( Object obj: result ){
            // Typecast to Person.
            Person person = (Person)obj;
            // Print data.
            System.out.println(person.getFullName());
            System.out.println("Friends: ");
            // Read all friends
            for( Person friend: person.getFriends() ){
                System.out.println("\t" + friend.getFullName());
            }
        }

    }

}