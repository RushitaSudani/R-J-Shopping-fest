package org.technous.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.technous.validation.audit.AudiTable;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")

public class Product extends AudiTable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")

    @NotEmpty
   // @Size(min = 4,max = 200,message = "Title must be between")
    private String title;

    @NotEmpty
   // @Size(min = 4,max = 200,message = "Description must be between")
    @Column(name = "description")
    private String description;

    //@NotEmpty
    @Column(name = "price")
    private int price;

    @Column(name = "discounted_price")
    private int discountedPrice;
    @Column(name = "discounted_persent")
    private int discountedPersent;
  //  @NotEmpty
    private int quantity;
    @NotEmpty
    private String brand;
    @NotEmpty
    private String color;

    //@Embedded
    //@Column(name = "sizes")
    //@OneToMany
    @ElementCollection()
    private Set<Sizee> sizes=new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "num_ratings")
    private int numRatings;

    @ManyToOne()
    @JoinColumn(name = "categoryid")
    @JsonIgnore
    private Category categories;

    private LocalDateTime createdAt;

    private String subImage1;
    private String subImage2;
    private String subImage3;

}
