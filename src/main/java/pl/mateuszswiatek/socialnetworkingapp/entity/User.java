package pl.mateuszswiatek.socialnetworkingapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User extends BaseEntity{
    private String username;
    private String password;
    private String email;
    private String description;
}
