package com.example.demo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @NotBlank
    @Length(min = 4, max = 10)
    private String content;

    private LocalDateTime createdAt;
}
