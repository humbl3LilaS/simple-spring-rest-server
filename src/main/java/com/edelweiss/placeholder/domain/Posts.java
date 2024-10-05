package com.edelweiss.placeholder.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = false)
public class Posts {
    
    @Id
    private Integer id;

    private Integer userId;

    private String title;

    private String body;
}
