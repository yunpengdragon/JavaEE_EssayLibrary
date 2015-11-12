package com.library.essay.configurations;


import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class DBProducer {

  @Produces
  @PersistenceContext(unitName = "essayLibraryPU")
  private EntityManager em;
}
