package com.tree.shop.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private  String name;

    @Column(unique = true, length = 20)
    @Size(min = 3, max = 20)
    private  String username;

    @Column(length = 30)
    private String password;
}
