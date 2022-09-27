package com.gsmh.kz.home.model.entity;

import com.gsmh.kz.home.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    private Long id;

    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    private boolean isActive;

    @NotBlank
    @Size(max = 120)
    private String password;

    private String avatarUrl;

    private String city;
    private String company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setCity(this.getCity());
        userDto.setAvatarUrl(this.getAvatarUrl());
        userDto.setName(this.getName());
        userDto.setCompany(this.getCompany());
        userDto.setEmail(this.getEmail());
        userDto.setPhone(this.getPhone());
        userDto.setId(this.getId());
        return userDto;
    }
}
