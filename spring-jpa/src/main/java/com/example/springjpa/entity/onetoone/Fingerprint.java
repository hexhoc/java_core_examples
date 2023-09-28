package com.example.springjpa.entity.onetoone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

@Entity
@Table(name = "fingerprints")
@Getter
@Setter
public class Fingerprint {

    @Id
    @Column(name = "id")
    private Integer id;

    @Lob
    @Column(name = "data")
    @JdbcType(VarbinaryJdbcType.class)
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
}
