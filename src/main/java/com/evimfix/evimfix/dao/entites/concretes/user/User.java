package com.evimfix.evimfix.dao.entites.concretes.user;

import com.evimfix.evimfix.dao.entites.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;

}
