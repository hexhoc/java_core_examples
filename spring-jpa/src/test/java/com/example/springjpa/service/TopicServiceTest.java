package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.onetomany.Comment;
import com.example.springjpa.entity.onetomany.Topic;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Интеграционный тест на прод окружении
 */
@SpringBootTest
@EnableJpaAuditing
class TopicServiceTest {

    @Autowired
    TopicService topicService;

    @Test
    @DisplayName("Тест. Из транзакционного метода вызываем другой транзакционный в рамках ОДНОГО КЛАССА.")
    void saveTopicTransactionalAndCommentTransactional_bad() {
        topicService.saveTopicTransactionalAndCommentTransactional_bad(topic());
    }

    @Test
    @DisplayName("Тест. Из транзакционного метода вызываем другой транзакционный в рамках РАЗНЫХ КЛАССОВ.")
    void saveTopicTransactionalAndCommentTransactional_good() {
        topicService.saveTopicTransactionalAndCommentTransactional_good(topic());
    }


    @Test
    @DisplayName("Тест. Из транзакционного метода вызываем другой НЕ транзакционный.")
    void saveTopicTransactionalAndCommentNonTransactional() {
        topicService.saveTopicTransactionalAndCommentNonTransactional(topic());
    }

    @Test
    void saveTopicNonTransactionalAndCommentTransactional() {
        topicService.saveTopicNonTransactionalAndCommentTransactional(topic());
    }

    Topic topic() {
        Topic t = new Topic();
        t.setTitle("title");
        t.setContent("content");
        t.setComments(List.of(
                new Comment(null, "comment message 1", null, null),
                new Comment(null, "comment message 1", null, null)
        ));
        return t;
    }

}