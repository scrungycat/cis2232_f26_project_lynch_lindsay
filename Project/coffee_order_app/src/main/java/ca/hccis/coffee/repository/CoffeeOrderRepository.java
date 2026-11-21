package ca.hccis.coffee.repository;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is for Sprint 3!!
 *
 * Spring Data repo, allows dependency injection
 * When a coffeeorder controller created:
 * instance of a repo injected into this as a param and assigned an attribute
 * All four CRUD operations wired through here
 * Bean validation runs on add and update
 *
 * Updated Nov 20 for Sprint 3 requirements
 */

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Integer> {

    List<CoffeeOrder> findByOrderStatus(String orderStatus);
    List<CoffeeOrder> findByOrderDateBetweenOrderByOrderDateAsc(String startDate, String endDate);
}