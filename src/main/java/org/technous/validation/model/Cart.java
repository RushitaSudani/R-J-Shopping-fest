package org.technous.validation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.technous.validation.audit.AudiTable;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart extends AudiTable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(name = "cart_items")
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "total_item")
    private int totalItem;
    private int discountedPrice;
    private int discounte;


}
