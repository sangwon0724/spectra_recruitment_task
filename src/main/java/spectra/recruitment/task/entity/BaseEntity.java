package spectra.recruitment.task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @Column(name = "created_by", length = 30)
    @Comment("최초 작성자")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_date")
    @Comment("최초 작성일시")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_by", length = 30)
    @Comment("최종 수정자")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @Comment("최종 수정일시")
    private LocalDateTime lastModifiedDate;

    /**
     * 최초 persist를 위한 처리
     * @param by 등록자 아이디
     */
    public void persist(String by){
        LocalDateTime now = LocalDateTime.now();

        this.createdBy = by;
        this.lastModifiedBy = by;

        this.createdDate = now;
        this.lastModifiedDate = now;
    }
}
