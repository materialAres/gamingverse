package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.shine.gamingverse.security.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private Set<Address> addresses;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "seller")
    private List<Game> gamesForSale;

    /*
     ** N.B. in mappedBy ci va il nome del campo nell'entità che
     ** ha il ManyToOne, in questo caso private Customer seller
     */
    @OneToMany(mappedBy = "seller")
    private List<Console> consolesForSale;

}
