package org.technous.validation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryid;
    @Column(name = "categorytitle")
    private String categorytitle;
    @Column(name = "categorydescription")
    private String categorydescription;

    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private List<Product> list=new ArrayList<>();
}
