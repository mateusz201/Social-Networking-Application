package pl.mateuszswiatek.socialnetworkingapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String description;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;
}
