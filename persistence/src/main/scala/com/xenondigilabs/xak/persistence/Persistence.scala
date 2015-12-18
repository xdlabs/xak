package com.xenondigilabs.xak.persistence

import java.util.Properties
import javax.jdo.PersistenceManagerFactory
import javax.jdo.JDOHelper

object Persistence{

  def getPersistenceManagerFactory(properties: Properties): PersistenceManagerFactory = {
    properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory")
    JDOHelper.getPersistenceManagerFactory(properties)
  }

}