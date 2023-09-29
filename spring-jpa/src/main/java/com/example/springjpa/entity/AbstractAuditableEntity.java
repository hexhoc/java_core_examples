package com.example.springjpa.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Общий каркас для всех сущностей. Если хотим использовать то Entity должна extend AbstractAuditableEntity
 */
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@MappedSuperclass // позволяет вынести общие поля в родительский класс, но при этом
// не создавать для него отдельную таблицу. При такой стратегии классы-наследники преобразуются в независимые таблицы.
// @MappedSuperclass никак не влияет на структуру в базе — это просто способ вынести общие поля.
@EntityListeners(AuditingEntityListener.class) // @EntityListeners позволяет указать класс, который будет
// выполнять инициализацию полей аудита перед сохранением сущности. Здесь мы используем стандартный спринговый класс
// AuditingEntityListener, но при необходимости вы можете определить и свой. Без него аннотации
// @CreatedBy, @CreatedDate, @LastModifiedBy, @LastModifiedDate работать не будут.
public abstract class AbstractAuditableEntity {

    @CreatedBy // Заполняеются через класс SecurityAuditorAware
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedBy // Заполняеются через класс SecurityAuditorAware
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    private Instant updatedDate;

}
