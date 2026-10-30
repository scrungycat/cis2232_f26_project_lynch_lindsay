package ca.hccis.coffee.repository;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This is a spring data repository
// allows Spring to inject (Dependency Injection)

//Goal of functionality for S3:
//Whenever a CoffeeOrder controller is created, tells it to inject an instance of a repo into this controller as a param and assign to the attribute

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {
    // Spring Data JPA automatically provides findAll(), findById(), save(), deleteById(), etc.
}