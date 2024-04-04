package org.technous.validation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotNull
//    @Size(max = 50)
    public String name;

    @ManyToOne()
    @JoinColumn(name = "parent_category_id")
    private Category perentCategory;
    private int level;
}
