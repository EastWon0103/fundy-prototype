package com.example.core.application.project.output;

public interface SaveProjectPort {
    boolean updateProjectExpired(long projectId);
    boolean updateProjectTransactionEnd(long projectId);
}