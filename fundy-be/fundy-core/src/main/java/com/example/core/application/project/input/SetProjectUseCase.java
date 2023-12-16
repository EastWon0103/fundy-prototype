package com.example.core.application.project.input;

public interface SetProjectUseCase {
    boolean setProjectExpired(long id);
    boolean setProjectTransactionEnd(long id);
}
