package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.model.Departemen;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartemenRepository implements PanacheRepository<Departemen> {

}
