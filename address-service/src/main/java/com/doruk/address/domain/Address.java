package com.doruk.address.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String id;
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    @Column(name = "TOWN",nullable = false)
    private String town;
    @Column(name = "ZIPCODE",nullable = false)
    private String zipCode;
}
