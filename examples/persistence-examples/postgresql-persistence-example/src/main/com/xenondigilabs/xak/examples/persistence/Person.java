package com.xenondigilabs.xak.examples.persistence;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import java.util.ArrayList;
import java.util.List;

@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class Person{

    String first_name = null;

    String last_name = null;

    String email_address = null;

    @Join
    List<Person>  friends = null;

    /**
     * Constructor initializes data menmbers.
     * @param f_name
     * @param l_name
     * @param email
     */
    public Person(String f_name, String l_name, String email){
        this.first_name = f_name;
        this.last_name = l_name;
        this.email_address = email;
        friends = new ArrayList<Person>();
    }

    /**
     * Get full name of Person.
     * @return
     */
    public String getFullName(){
        // Full Name : First Name + <space> + Last Name
        return this.first_name + " " + this.last_name;
    }

    /**
     * Add new friend to friends list.
     * @param friend
     */
    public void addFriend(Person friend){
        // Append friend object to friends list
        this.friends.add(friend);
    }

    /**
     * Get collection of all friends
     * @return
     */
    public List<Person> getFriends(){
        // Return friends list,
        return this.friends;
    }

}