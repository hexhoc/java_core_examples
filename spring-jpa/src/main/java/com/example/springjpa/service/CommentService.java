package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.onetomany.Comment;
import com.example.springjpa.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void save(Integer topicId, List<Comment> comments) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        for (Comment comment : comments) {
            comment.setTopicId(topicId);
        }
        commentRepository.saveAll(comments);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveTransactional(Integer topicId, List<Comment> comments) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        for (Comment comment : comments) {
            comment.setTopicId(topicId);
        }
        commentRepository.saveAll(comments);
    }
}
