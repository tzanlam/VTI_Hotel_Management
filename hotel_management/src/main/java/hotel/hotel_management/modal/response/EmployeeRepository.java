package hotel.hotel_management.modal.response;

import hotel.hotel_management.modal.entity.hotel.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
