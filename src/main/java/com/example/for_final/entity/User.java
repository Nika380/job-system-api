package com.example.for_final.entity;

import com.example.for_final.RoleAndTypes.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
public class User {
    public User(String username, String password, String email, AccountType accountType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Basic
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        updatedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToMany()
    @JoinTable(
            name = "companies_and_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")

    )
    private List<Company> companies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_name")
    )
    private List<Permission> permissions;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "companies_and_users_2",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")

    )
    private List<Company> companyList;

}
