package org.lessons.java.crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaRepository repository;

  @GetMapping
  public String index(Model model) {

    List<Pizza> pizzas = repository.findAll();

    model.addAttribute("pizzas", pizzas);

    return "pizzas/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    Pizza pizza = repository.findById(id).get();

    model.addAttribute("pizza", pizza);

    return "pizzas/show";
  }

  @GetMapping("/search")
  public String searchByTitle(@RequestParam(name = "name") String name, Model model) {

    List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(name);

    model.addAttribute("pizzas", pizzas);
    model.addAttribute("name", name);

    return "pizzas/index";
  }
}
