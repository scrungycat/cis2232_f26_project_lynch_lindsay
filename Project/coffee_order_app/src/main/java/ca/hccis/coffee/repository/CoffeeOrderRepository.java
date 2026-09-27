package ca.hccis.coffee.repository;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This is a spring data repository

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {
    // Spring Data JPA automatically provides findAll(), findById(), save(), deleteById(), etc.
}