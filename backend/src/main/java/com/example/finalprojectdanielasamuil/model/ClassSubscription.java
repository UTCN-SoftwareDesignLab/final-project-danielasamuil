package com.example.finalprojectdanielasamuil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClassSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fitnessclass_id", nullable = false)
    private FitnessClass fitnessClass;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;
}
