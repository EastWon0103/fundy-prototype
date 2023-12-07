package com.example.fundydomain.domain.devnotecomment;

import com.example.fundydomain.domain.devnote.DevNote;
import com.example.fundydomain.domain.user.FundyUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEVNOTE_COMMENT")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DevNoteComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private FundyUser writer;

    @ManyToOne
    @JoinColumn(name = "devnote_id")
    private DevNote devNote;
}
