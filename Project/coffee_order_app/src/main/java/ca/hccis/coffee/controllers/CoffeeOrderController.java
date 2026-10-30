package ca.hccis.coffee.controllers;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffeeorder")
public class CoffeeOrderController {

    private final CoffeeOrderRepository _cor;

    public CoffeeOrderController(CoffeeOrderRepository cor) {
        _cor = cor;
    }

    @RequestMapping("")
    public String home(Model model) {
        Iterable<CoffeeOrder> orders = _cor.findAll();
        model.addAttribute("orders", orders);
        return "coffeeorder/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("coffeeOrder", new CoffeeOrder());
        return "coffeeorder/add"; // Resolves to templates/coffeeorder/add.html
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute("coffeeOrder") CoffeeOrder coffeeOrder) {
        // Run your Business Object calculation logic before saving
        CoffeeOrderBO.calculateSubtotal(coffeeOrder);
        CoffeeOrderBO.calculateTax(coffeeOrder);
        CoffeeOrderBO.calculateGrandTotal(coffeeOrder);

        // Save to the database via JPA repository
        _cor.save(coffeeOrder);

        return "redirect:/coffeeorder"; // Redirect back to the main list
    }

}