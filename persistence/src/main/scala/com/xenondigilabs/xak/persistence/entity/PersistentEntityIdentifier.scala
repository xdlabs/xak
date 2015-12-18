package com.xenondigilabs.xak.persistence.entity

trait PersistentEntityIdentifier extends Serializable{

  def PersistentEntityIdentifier(identifier: Object);

  def equals(identifier: PersistentEntityIdentifier): Boolean;

  def hashCode(): Int

  def toString(): String;

}
