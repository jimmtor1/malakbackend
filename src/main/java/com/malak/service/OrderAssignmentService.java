package com.malak.service;

import com.malak.dto.JobPerformanceDTO;
import com.malak.dto.ScoreAllMonthEmployee;
import com.malak.dto.ScoreEmployeSumDTO;
import com.malak.dto.titleAndDataTableScoreMonth;
import com.malak.model.Employe;
import com.malak.model.Coefficientb;
import com.malak.model.OrderAssignment;
import com.malak.repository.CoeficientBRepository;
import com.malak.repository.OrderAssignmentRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAssignmentService {

    @Autowired
    private OrderAssignmentRepository orderAssignmentRepository;

    @Autowired
    private CoeficientBRepository coeficientBRepository;

    @Autowired
    private EmployeService employeService;

    public OrderAssignment assign(OrderAssignment orderAssignment) {
        orderAssignment.setRegistrationDate(LocalDate.now());
        OrderAssignment rs = this.orderAssignmentRepository.save(orderAssignment);
        return rs;
    }

    public boolean assigns(List<OrderAssignment> orderAssignment) {
//        System.out.println(orderAssignment);
//        orderAssignment.forEach(o -> o.setRegistrationDate(LocalDate.now()));
        this.orderAssignmentRepository.saveAll(orderAssignment);

        List<OrderAssignment> orderAssignment2 = new ArrayList<>();
        OrderAssignment neworder;
        for (OrderAssignment oa : orderAssignment) {
            if (oa.getAssistant() > 0) {
                neworder = new OrderAssignment();
                neworder.setElementId(oa.getElementId());
                neworder.setStartTime(oa.getStartTime());
                neworder.setEndTime(oa.getEndTime());
                neworder.setQuantity(oa.getQuantity());
                neworder.setIO(oa.getIO());
                neworder.setMachine(oa.getMachine());
                neworder.setManager(oa.getManager());
                neworder.setOrder(oa.getOrder());
                neworder.setScore(oa.getScore());
                neworder.setRegistrationDate(oa.getRegistrationDate());
                neworder.setEmployee(new Employe(oa.getAssistant()));
                neworder.setAssistant(0);
                orderAssignment2.add(neworder);
            }
        }

        if (orderAssignment2.size() > 0) {
            this.orderAssignmentRepository.saveAll(orderAssignment2);
        }

        return true;
    }

    public Iterable<OrderAssignment> getAssignsByEmployeeAndRegistrationDate(int idemployee, LocalDate registrationDate) {
        return this.orderAssignmentRepository.findByEmployeeAndRegistrationDate(new Employe(idemployee), registrationDate);
    }

    public Optional<Coefficientb> getBCoefficient(String processingType) {
        return this.coeficientBRepository.findById(processingType);
    }

    public List<ScoreEmployeSumDTO> getScoreEmployeeList(Employe employee, int month) {
        return this.orderAssignmentRepository.ListScoreEmployeSum(employee, month);
    }

    public List<JobPerformanceDTO> getLast3MonthsScoreEmployeeAvg() {

        List<Object[]> results = orderAssignmentRepository.ListScoreEmployeAvg();

//        System.out.println(results);

        List<JobPerformanceDTO> averages = new ArrayList<>();
        Iterable<Employe> employees = employeService.employeList();
        List<Object[]> results2 = new ArrayList<>();

        boolean isthere = false;
        for (Employe e : employees) {
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i)[0] == e.getId()) {
                    isthere = true;
                    break;
                }
            }
            if (!isthere) {
                Object[] d = new Object[5];
                d[0] = e.getId();
                d[1] = e.getName();
                results2.add(d);
            }
            isthere = false;
        }

        results.addAll(results2);

        for (Object[] result : results) {
            Integer employeeId = ((Number) result[0]).intValue();
            String name = (String) result[1];
            Double average1 = result[2] == null ? 0.0 : ((BigDecimal) result[2]).doubleValue();
            Double average2 = result[3] == null ? 0.0 : ((BigDecimal) result[3]).doubleValue();
            Double average3 = result[4] == null ? 0.0 : ((BigDecimal) result[4]).doubleValue();

            averages.add(new JobPerformanceDTO(employeeId, name, average1, average2, average3));
        }
        return averages;

    }

    public titleAndDataTableScoreMonth getScoreAllMonth(int month) {
        List<ScoreAllMonthEmployee> scores = this.orderAssignmentRepository.ListScoreAllMonth(month);

        Iterable<Employe> employees = employeService.employeList();

        List<LocalDate> dates = new ArrayList<>();
        List<Employe> employee = new ArrayList<>();

        for (ScoreAllMonthEmployee s : scores) {
            if (!dates.contains(s.getRegistrationDate())) {
                dates.add(s.getRegistrationDate());
            }
//            if (!employee.contains(new Employe(s.getIdEmployee(), s.getEmployeeName()))) {
//                employee.add(new Employe(s.getIdEmployee(), s.getEmployeeName()));
//            }
        }

        for (Employe e : employees) {
            if (!employee.contains(e)) {
                employee.add(e);
            }
        }

        Object[][] data = new Object[employee.size()][dates.size() + 2];

        for (int i = 0; i < employee.size(); i++) {
            data[i][0] = employee.get(i).getId();
            data[i][1] = employee.get(i).getName();

            datesfor:
            for (int j = 0; j < dates.size(); j++) {
                for (int k = 0; k < scores.size(); k++) {
                    if (scores.get(k).getIdEmployee() == employee.get(i).getId() && scores.get(k).getRegistrationDate().equals(dates.get(j))) {
                        data[i][j + 2] = scores.get(k).getScore();
                        continue datesfor;
                    }
                }
                data[i][j + 2] = 0.0;
            }

        }

//        for (Object[] data1 : data) {
//            for (Object data11 : data1) {
//                System.out.print(data11 + " ");
//            }
//            System.out.println("");
//        }
        return new titleAndDataTableScoreMonth(dates, data);
    }

}
