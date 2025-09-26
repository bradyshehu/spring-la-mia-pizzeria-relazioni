package org.lessons.java.spring_la_mia_pizzeria_crud.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Questo campo non puó essere lasciato incompleto")
    @Size(max=50, message = "Il massimo numero di caratteri per il nome é 50")
    private String name;


    @Lob
    @Size(max=500, message = "Il massimo numero di caratteri per la descrizione é 500")
    @NotBlank(message = "Questo campo non puó essere lasciato incompleto")
    private String description;

    @NotBlank(message = "Questo campo non puó essere lasciato incompleto")
    @Size(max=150, message = "Il massimo numero di caratteri per l''url' dell''immagine é 150")
    private String img;

    @NotNull(message = "Questo campo non puó essere lasciato incompleto")
    @DecimalMin(value = "4.99", message = "Il prezzo minimo per una pizza é di 5€") 
    private BigDecimal price;

    // GETTERS

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "pizza")
    private List<Offer> offers;

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> newOffers){
        this.offers = newOffers;
    }

    @ManyToMany()
    @JoinTable(
        name="ingredient_pizza",
        joinColumns = @JoinColumn( name = "pizza_id"),
        inverseJoinColumns = @JoinColumn( name = "ingredient_id")
    )

    private List<Ingredient> ingredients;



    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    @Override
    public String toString(){
        return String.format("Pizza: %s, Prezzo: %.2f euro.  \n Informazioni: %s", this.name, this.price, this.description);
    }
}
