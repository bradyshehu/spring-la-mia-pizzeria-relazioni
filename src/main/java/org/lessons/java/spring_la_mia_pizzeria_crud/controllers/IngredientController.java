package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);

        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store (@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }

        ingredientRepository.save(formIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult , Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-or-edit";
        }      
        
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        for(Pizza pizzaContainingIngredient : ingredientToDelete.getPizzas()){
            pizzaContainingIngredient.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
        
        return "redirect:/ingredients";
    }
    
    
    
    
    
    
}
