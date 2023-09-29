package com.example.springjpa.service;

import java.util.List;
import java.util.Optional;

import com.example.springjpa.entity.onetomany.Comment;
import com.example.springjpa.entity.onetomany.Topic;
import com.example.springjpa.repository.CommentRepository;
import com.example.springjpa.repository.TopicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // TODO: Заменить в примерах использование Topic и Comment на Person и Fingerprint
    /**
     * Из транзакционного метода вызываем другой транзакционный в рамках ОДНОГО КЛАССА.
     * Результат: Ожидаем что начнется новая транзакция, но ее не будет.
     * Если родительская транзакция выкинет исключение, то данные дочерний так же будут отброшены, потому что дочерней нет.
     */
    @Transactional
    public Topic saveTopicTransactionalAndCommentTransactional_bad(Topic topic) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        var savedTopic = topicRepository.save(topic);
        saveCommentTransactional(savedTopic.getId(), topic.getComments());
        return savedTopic;
    }

    /**
     * Из транзакционного метода вызываем другой транзакционный в рамках ОДНОГО КЛАССА.
     * Результат: Ожидаем что начнется новая транзакция, и она начнется, а существующая будет на паузе
     * Возникнет ошибка целостности данных, потому что мы пытаемся записать комменты к еще не записанному посту.
     * Но новая транзакция для комментариев будет открыта
     */
    @Transactional
    public Topic saveTopicTransactionalAndCommentTransactional_good(Topic topic) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        var savedTopic = topicRepository.save(topic);
        commentService.saveTransactional(savedTopic.getId(), topic.getComments());
        return savedTopic;
    }

    /**
     * Из транзакционного метода вызываем другой НЕ транзакционный.
     * Результат: Ожидаем что вызываемый метод тоже будет выполняется в транзакции
     * Все вызываеме методы будут выполняется в рамках родителькой транзакции
     */
    @Transactional
    public Topic saveTopicTransactionalAndCommentNonTransactional(Topic topic) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        var savedTopic = topicRepository.save(topic);
        commentService.save(savedTopic.getId(), topic.getComments());
        return savedTopic;
    }

    /**
     * Из НЕ транзакционного метода вызываем другой транзакционный.
     * Результат: Ожидаем что запустится транзакция, но ее не будет
     */
    public Topic saveTopicNonTransactionalAndCommentTransactional(Topic topic) {
        var savedTopic = topicRepository.save(topic);
        saveCommentTransactional(savedTopic.getId(), topic.getComments());
        return savedTopic;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveCommentTransactional(Integer topicId, List<Comment> comments) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        for (Comment comment : comments) {
            comment.setTopicId(topicId);
        }
        commentRepository.saveAll(comments);
    }

    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

}
