package com.edelweiss.placeholder.domain;

import io.micrometer.common.lang.NonNull;
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
public class Users {
    @Id
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String userName;

    @NonNull
    private String email;
}
