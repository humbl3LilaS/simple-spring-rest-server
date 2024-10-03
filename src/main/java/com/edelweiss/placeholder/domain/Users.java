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
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Users {
    @Id
    Integer id;

    @NonNull
    String name;

    @NonNull
    String userName;

    @NonNull
    String email;
}
