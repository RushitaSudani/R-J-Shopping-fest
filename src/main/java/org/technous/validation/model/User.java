package org.technous.validation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.technous.validation.audit.AudiTable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AudiTable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private  String email;
    private  long phone_no;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addessList = new ArrayList<Address>();

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @NotEmpty(message = "not be empty")
//    @Size(min = 3, max = 20, message = "FirstName between 3 to 20")
//    private String firstName;
//    @NotEmpty(message = "not be empty")
//    @Size(min = 3, max = 20, message = "LastName between 3 to 20")
//    private String lastName;
//    private String password;
//    @Email(message = "Email Not Be null")
//    private String email;
//    // private String role;
//    // private String mobile;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Address> addessList = new ArrayList<Address>();

    //@Embedded
//    @ElementCollection
//    @CollectionTable(name = "paymentinformation",joinColumns = @JoinColumn(name = "userId"))
//    private List<PaymentInformation> paymentInformations = new ArrayList<>();

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Rating> ratings = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//    private List<Review> review = new ArrayList<>();

    // private LocalDateTime createdAt;

//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {CascadeType.PERSIST,
//                    CascadeType.MERGE, CascadeType.DETACH})
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//    private List<Role> roles;
}
