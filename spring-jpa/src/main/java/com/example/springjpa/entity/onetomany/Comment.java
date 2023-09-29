package com.example.springjpa.entity.onetomany;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "message", columnDefinition = "text")
    private String message;

    /*
    Данная колонка используется только для чтения, потому что:
    1. Отключили CascadeType
    2. Исключили возможность обновления insertable=false, updatable=false
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable=false, updatable=false)
    private Topic topic;

    @Column(name = "topic_id")
    private UUID topicId;

}
