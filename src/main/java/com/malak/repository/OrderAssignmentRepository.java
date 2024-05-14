package com.malak.repository;

import com.malak.dto.ScoreAllMonthEmployee;
import com.malak.dto.ScoreEmployeSumDTO;
import com.malak.model.Employe;
import com.malak.model.OrderAssignment;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderAssignmentRepository extends CrudRepository<OrderAssignment, Integer> {

    Iterable<OrderAssignment> findByEmployeeAndRegistrationDate(Employe employee, LocalDate date);

    @Query("SELECT new com.malak.dto.ScoreEmployeSumDTO(o.registrationDate, SUM(o.score)) "
            + "FROM OrderAssignment o WHERE o.employee = :employee AND MONTH(o.registrationDate) = :month GROUP BY o.registrationDate")
    List<ScoreEmployeSumDTO> ListScoreEmployeSum(@Param("employee") Employe employee, @Param("month") int month);

//    @Query(value="select "
//    + "o.employee_id,"
//    + "e.name, "
//    + "(select avg(score) from order_assignment where employee_id = o.employee_id and month(registration_date) = month(current_date())) average1, "
//    + "(select avg(score) from order_assignment where employee_id = o.employee_id and month(registration_date) = month(CURDATE() - INTERVAL 1 MONTH)) average2, "
//    + "(select avg(score) from order_assignment where employee_id = o.employee_id and month(registration_date) = month(CURDATE() - INTERVAL 2 MONTH)) average3 "
//    + "from order_assignment o inner join employe e on(o.employee_id=e.id) group by o.employee_id;", nativeQuery = true)
//    List<Object[]> ListScoreEmployeAvg();
    
//    @Query(value = "WITH ScoreSum AS ("
//            + "SELECT employee_id, registration_date, sum(score) sum "
//            + "FROM order_assignment "
//            + "GROUP BY employee_id, registration_date) "
//            + "SELECT o.employee_id, e.name,"
//            + "(SELECT avg(sum) from ScoreSum where employee_id = o.employee_id and YEAR(registration_date) = YEAR(current_date()) and month(registration_date) = month(current_date())) average1,"
//            + "(SELECT avg(sum) from ScoreSum where employee_id = o.employee_id and YEAR(registration_date) = YEAR(current_date() - INTERVAL 1 MONTH) and month(registration_date) = month(CURDATE() - INTERVAL 1 MONTH)) average2,"
//            + "(SELECT avg(sum) from ScoreSum where employee_id = o.employee_id and YEAR(registration_date) = YEAR(current_date() - INTERVAL 2 MONTH) and month(registration_date) = month(CURDATE() - INTERVAL 2 MONTH)) average3 "
//            + "FROM ScoreSum o inner join employe e on(o.employee_id=e.id) group by o.employee_id;", nativeQuery = true)

    @Query(value = "CALL malak.ScoreAverage()", nativeQuery = true)
    List<Object[]> ListScoreEmployeAvg();

    @Query("SELECT new com.malak.dto.ScoreAllMonthEmployee(o.employee.id, o.employee.name, o.registrationDate, SUM(o.score) as score) "
            + "FROM OrderAssignment o "
            + "WHERE MONTH(o.registrationDate) = :month "
            + "GROUP BY o.employee.id, o.registrationDate")
    List<ScoreAllMonthEmployee> ListScoreAllMonth(@Param("month") int month);

    
    
}
