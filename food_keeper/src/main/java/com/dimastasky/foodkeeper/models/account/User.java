package com.dimastasky.foodkeeper.models.account;

import com.dimastasky.foodkeeper.models.food_warehouse.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
 uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
         @UniqueConstraint(columnNames = "email")
 })
@Getter
@Setter
public class User {

    //ToDo: UUID for User
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 25)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 50)
    private String fullname;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"),
                                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String fullname, String password) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.password = password;
    }
}
