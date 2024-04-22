package org.technous.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
   // @NotEmpty
   // @Size(min = 4,max = 30,message = "firstName must be between 4 to 30")
    private String firstName;

   // @NotEmpty
   // @Size(min = 4,max = 30,message = "lastName must be between 4 to 30")
    @Column(name = "lastname")
    private String lastName;

    //@NotEmpty
    //@Size(min = 4,max = 200,message = "streetaddress must be between 4 to 200")
    @Column(name = "streetaddress")
    private String streetAddress;


    private String country;
    @Column(name = "state")
    // @NotEmpty
    // @Size(min = 4,max = 20,message = "state must be between 4 to 200")
    private String state;
    @Column(name = "city")
    //@NotEmpty
   // @Size(min = 4,max = 20,message = "city must be between 4 to 20")
    private String city;

    @Column(name = "zipcode")
   // @NotEmpty
 //   @Size(min = 0,max = 6,message = "zipCode must between 0 to 6")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    private String mobile;
}
