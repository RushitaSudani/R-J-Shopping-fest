package org.technous.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "streetaddress")
    private String streetAddress;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    private String mobile;
}
