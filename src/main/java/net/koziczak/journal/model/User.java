package net.koziczak.journal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table (name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotBlank
    private String login;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
//    @Pattern(regexp = "[A-Z]{1,}")
    private String password;

    public User(@NotBlank String login, @Email @NotBlank String email, @NotBlank @Size(min = 6) @Pattern(regexp = "[A-Z]+") String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
    // dane generowane automatycznie
    private LocalDateTime registration_datetime = LocalDateTime.now();
private boolean isActivated = true;

    @OneToMany(mappedBy = "user")
    private List<Entry> entries = new ArrayList<>();

    @ManyToMany
    @JoinTable(     // adnotacja złączająca tabele na podstawie id
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
    // metoda dodawania roli
    public void addRole(Role role){
        this.roles.add(role);
    }

}
