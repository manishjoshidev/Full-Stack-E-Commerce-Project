package com.mani.E_commerce.entity;

import com.mani.E_commerce.enums.userRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;

@Entity    //from jpa
@Data
@Table(name="users")
public class User {
    @Id
    private Long id;
    private String mail;
    private String password;
    private String name;
    private userRole role;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] img;



}
