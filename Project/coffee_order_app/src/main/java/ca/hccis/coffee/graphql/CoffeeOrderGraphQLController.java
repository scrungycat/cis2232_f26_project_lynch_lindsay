package ca.hccis.coffee.graphql;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CoffeeOrderGraphQLController {

    private final CoffeeOrderRepository repository;

    public CoffeeOrderGraphQLController(CoffeeOrderRepository repository) {
        this.repository = repository;
    }

    // read queries below (read)

    // Handles query: allOrders: [CoffeeOrder]!
    @QueryMapping
    public List<CoffeeOrder> allOrders() {
        return repository.findAll();
    }

    // Handles query: orderById(id: ID!): CoffeeOrder
    @QueryMapping
    public CoffeeOrder orderById(@Argument Integer id) {
        return repository.findById(id).orElse(null);
    }

    // handles schema: ordersByStatus(status: String!): [CoffeeOrder]!
    @QueryMapping
    public List<CoffeeOrder> ordersByStatus(@Argument String status) {
        return repository.findByOrderStatus(status);   // empty list if none match
    }

    // mutations below (write)

    // Handles schema: createOrder(input: CoffeeOrderInput!): CoffeeOrder!
    @MutationMapping
    public CoffeeOrder createOrder(@Argument("input") CoffeeOrderInput input) {
        CoffeeOrder order = new CoffeeOrder();

        order.setCustomerName(input.getCustomerName());
        order.setDrinkType(input.getDrinkType());
        order.setDrinkSize(input.getDrinkSize());
        order.setMilkType(input.getMilkType() != null ? input.getMilkType() : "None");
        order.setQuantity(input.getQuantity());
        order.setExtraShots(input.getExtraShots() != null ? input.getExtraShots() : 0);
        order.setOrderStatus(input.getOrderStatus() != null ? input.getOrderStatus() : "Pending");
        order.setOrderDate(input.getOrderDate());

        CoffeeOrderBO.calculateSubtotal(order);
        CoffeeOrderBO.calculateTax(order);
        CoffeeOrderBO.calculateGrandTotal(order);

        return repository.save(order);
    }

    //  handles schema: updateOrderStatus(id: ID!, status: String!): CoffeeOrder
    @MutationMapping
    public CoffeeOrder updateOrderStatus(@Argument Integer id, @Argument String status) {
        CoffeeOrder order = repository.findById(id).orElse(null);
        if (order == null) {
            return null;                 // client receives null = "no order with that id"
        }
        order.setOrderStatus(status);
        return repository.save(order);   // has an id, so JPA runs an UPDATE, not an INSERT
    }

    // handles schema: deleteOrder(id: ID!): Boolean!
    @MutationMapping
    public boolean deleteOrder(@Argument Integer id) {
        if (!repository.existsById(id)) {
            return false;                // nothing to delete
        }
        repository.deleteById(id);
        return true;
    }
}