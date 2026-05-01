package com.example.student_library_system.requestdto;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private String dueDate;
    private String transactionType;

    private int bookId;
    private int cardId;
}
