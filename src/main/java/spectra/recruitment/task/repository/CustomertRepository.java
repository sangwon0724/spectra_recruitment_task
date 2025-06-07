package spectra.recruitment.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spectra.recruitment.task.entity.Customer;

@Repository
public interface CustomertRepository extends JpaRepository<Customer, Long> {

}
