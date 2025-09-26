package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;








@Controller
@RequestMapping("/pizzas")

public class PizzasController {
    
    @Autowired
    private PizzasRepository repository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index (Model model) {

        List<Pizza> pizzas = repository.findAll();

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute("pizza", repository.findById(id).get());

       return "pizzas/show";
    }
    
    @GetMapping("/searchByName")
    public String indexSearchedByName(@RequestParam String name, Model model) {

        List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(name);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult , Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create-or-edit";
        }

        repository.save(formPizza);

        return "redirect:/pizzas";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("edit", true);
        return "pizzas/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult , Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("ingredients", ingredientRepository.findAll());
            model.addAttribute("edit", true);
        return "pizzas/create-or-edit";
        }

        repository.save(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String destroy(@PathVariable("id") Integer id) {

        for(Offer offerToDelete : repository.findById(id).get().getOffers()){
            offerRepository.delete(offerToDelete);
        }

        repository.deleteById(id);
        
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/offers")
    public String createOffer(@PathVariable("id") Integer id, Model model) {

        Offer offer = new Offer();
        offer.setPizza(repository.findById(id).get());

        model.addAttribute("offer", offer);
        // model.addAttribute("edit",false);

        return "offers/create-or-edit";
    }
    
    
    
    
}
