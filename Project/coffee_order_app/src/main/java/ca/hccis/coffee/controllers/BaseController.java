package ca.hccis.coffee.controllers;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import ca.hccis.coffee.util.CisUtility;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
/**
 * Base entry controller with hookups for coffee order repo
 *
 * @author LRML
 * @since 09262026
 * @updated 10/16-29/2026 to add column hookups and naming conventions for db connection
 */

@Controller
public class BaseController {

    private final CoffeeOrderRepository _cor;

    @Autowired
    public BaseController(CoffeeOrderRepository cor) {
        _cor = cor;
    }

    @RequestMapping("/")
    public String home(HttpSession session, Model model) {
        String currentDate = CisUtility.getTodayString("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        ArrayList<CoffeeOrder> orders = new ArrayList<>();
        _cor.findAll().forEach(orders::add);
        model.addAttribute("orders", orders);

        return "index";
    }
}
