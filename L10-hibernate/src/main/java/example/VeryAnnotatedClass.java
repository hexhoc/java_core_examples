package example;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import exercise_2.Book;

@Entity
@Table(name = "demo_annotated", indexes = {
        @Index(name = "name_idx", columnList = "name"),
        @Index(name = "id_name_idx", columnList = "id, name"),
        @Index(name = "unique_name_idx", columnList = "name", unique = true)
})
public class VeryAnnotatedClass {
    @Id
    @GeneratedValue
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "weight")
    float weight;

    //we can use contraint like NOT NULL or length
    @Column(name = "short_str", nullable = false, length = 10) // varchar
    String shortString;

    @Column(name = "weight")
    //ColumnTransformer help us convert one value to another while read or write 
    @ColumnTransformer(
            read = "weight / 2.0",
            write = "? * 2.0"
    )
    float dividedWeight;

    //When we read our instance, this field automatically calculate by formula
    @Formula("SELECT avg(p.cost) FROM Product p")
    BigDecimal avgManufacturerProductCost;

    //Temporal in most case is used with java.Date or java.Calendar to fix problem while write record to db
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Column(name = "manual_def_str", columnDefinition = "VARCHAR(50) NOT NULL UNIQUE CHECK (NOT substring(lower(manual_def_str), 0, 5) = 'admin')")
    String manualDefinedString;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCT_ID")
    )
    Book book;

    //Version of this object do not increment if we change this field
    @OptimisticLock(excluded = true)
    int junkField;

    //changed each time when transaction for update object is complete
    //Uses for optimistic lock
    //Optimistic lock - if both transaction start modify one object, when the transaction is completed,
    //the object version will be increased, in this reason another transaction will be rollback
    @Version
    long version;
}