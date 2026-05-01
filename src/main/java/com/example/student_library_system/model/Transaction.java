package com.example.student_library_system.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="transactions")
@Data
public class Transaction {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="transaction_date", nullable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name="due_date", nullable = false)
    private String dueDate;

    @Column(name="transaction_type", nullable = false)
    private String transactionType;

    @JoinColumn
    @ManyToOne
    private Card card;

    @JoinColumn
    @ManyToOne
    private Book book;
}
