package com.example.for_final.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "permissions")
public class Permission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permission_name")
    private String permissionName;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<User> users;

}
