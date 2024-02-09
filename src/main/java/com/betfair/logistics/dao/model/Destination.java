package com.betfair.logistics.dao.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "destinations")
@EqualsAndHashCode
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //de obicei se foloseste sequence(+ se poate folosi acelasi sequence pt mai multe tabele) sau auto
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer distance;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
//    List<Order> orders;






}
