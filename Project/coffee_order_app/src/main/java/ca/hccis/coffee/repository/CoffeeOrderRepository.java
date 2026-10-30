package ca.hccis.coffee.repository;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is for Sprint 3!!
 *
 * Spring Data repo, allows dependency injection
 * When a coffeeorder controller created, instance of a repo injected into this as a param and assigned an attribute??
 *
 * Work on this later for next sprint due date
 */

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {
    // Spring Data JPA automatically provides findAll(), findById(), save(), deleteById(), etc.
}