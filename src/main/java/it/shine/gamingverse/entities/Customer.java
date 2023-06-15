package it.shine.gamingverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.shine.gamingverse.security.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "customers", schema = "public", catalog = "gamingverse")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private Set<Address> addresses;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "seller")
    private List<Game> gamesForSale;

    /*
     ** N.B. in mappedBy ci va il nome del campo nell'entità che
     ** ha il ManyToOne, in questo caso private Customer seller
     */
    @OneToMany(mappedBy = "seller")
    private List<Console> consolesForSale;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Order> orders;

}
