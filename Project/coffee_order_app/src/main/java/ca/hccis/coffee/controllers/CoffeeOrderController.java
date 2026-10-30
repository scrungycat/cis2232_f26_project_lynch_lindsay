package ca.hccis.coffee.controllers;

import ca.hccis.coffee.bo.CoffeeOrderBO;
import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") int id, Model model) {
        CoffeeOrder order = _cor.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/coffeeorder";
        }
        model.addAttribute("coffeeOrder", order);
        return "coffeeorder/add";
    }

    @PostMapping("/submit")
    public String submitOrder(@ModelAttribute("coffeeOrder") CoffeeOrder coffeeOrder, RedirectAttributes redirectAttributes) {
        // Run Business Object calculation logic
        CoffeeOrderBO.calculateSubtotal(coffeeOrder);
        CoffeeOrderBO.calculateTax(coffeeOrder);
        CoffeeOrderBO.calculateGrandTotal(coffeeOrder);

        // Save to database
        _cor.save(coffeeOrder);

        // Pass a success message to display on the redirect destination
        redirectAttributes.addFlashAttribute("messageSuccess",
                "Successfully saved order for " + coffeeOrder.getCustomerName() + "!");

        return "redirect:/coffeeorder";
    }

}