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
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Todos {
    @Id
    private Integer id;

    private Integer userId;

    @NonNull
    private String title;

    @NonNull
    private Boolean completed;
}
