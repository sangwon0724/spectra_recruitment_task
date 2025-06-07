package spectra.recruitment.task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import spectra.recruitment.task.dto.CustomerDto;

/**
 * 고객
 */
@Slf4j
@NoArgsConstructor
@Getter
@Table(name = "tb_customer")
@Entity
@Comment("고객")
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", columnDefinition = "BIGINT UNSIGNED")
    @Comment("PK")
    private Long id;

    @Column(name = "consult_type", length = 30, nullable = false)
    @Comment("상담 유형")
    private String consultType;

    public static Customer dtoToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.consultType = customerDto.getConsultType();
        return customer;
    }
}
