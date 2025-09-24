package org.lessons.java.spring_la_mia_pizzeria_crud.models;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Questo campo non puó essere lasciato incompleto")
    private LocalDate start;



    @NotNull(message = "Questo campo non puó essere lasciato incompleto")
    private LocalDate end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @NotNull(message = "Questo campo non puó essere lasciato incompleto")
    private int percentage;

    @NotBlank(message = "Questo campo non puó essere lasciato incompleto")
    private String title; 

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;


    
    // @Override
    // public void toString
}
