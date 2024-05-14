package com.malak.service;

import com.malak.dto.OrderDto;
import com.malak.model.Customer;
import com.malak.model.Order;
import com.malak.repository.OrderRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //consulta orden por status 1-creado 2-aprovado 3-finalizado
    public Iterable<OrderDto> OrderListByApproved(int status) {
        return this.orderRepository.findByStatusAndActiveTrue(status);
    }
    
    //consulta orden con status mayor a 1(aprovadas y finalizadas)
    public Iterable<OrderDto> OrderListByApproved2() {
        return this.orderRepository.findByStatusGreaterThanAndActiveTrueOrderByDateDesc(1);
    }
    
    //consulta orden con status mayor a 1(aprovadas y finalizadas)
    public Iterable<Order> AllOrderList() {
        return this.orderRepository.findAll();
    }

    //crea nueva orden, edita nueva orden, cambia status de las ordenes
    @Transactional
    public Order saveOrder(Order order) {

        int subid = this.nextOrderSubId(order.getSuffix());

        order.setSubId(subid);
        order.setId(order.getSuffix() + (subid + ""));
        order.setStatus(1);
        order.setDate(LocalDate.now());
        order.setActive(true);
        
//        List<Element> elements = order.getElement();        
//        elements.forEach(a-> a.setOrder(order));
        return this.orderRepository.save(order);
    }

    public int nextOrderSubId(char suffix) {
        return this.orderRepository.findByLastSuffix(suffix).map(v -> v.getSubId() + 1).orElse(1);
    }

    public Iterable<Order> OrderListByCustomer(int customer) {
        return this.orderRepository.findByCustomer(new Customer(customer));
    }
    
    public Boolean archiveOrder(String idorder){
        Optional<Order> optional = this.orderRepository.findById(idorder);
        if(optional.isPresent()){
            Order o = optional.get();
            o.setActive(false);
            this.orderRepository.save(o); 
            return true;
        }
        return false;
    }
    
    public Boolean changeStatusOrder(String idorder, int status){
        Optional<Order> optional = this.orderRepository.findById(idorder);
        if(optional.isPresent()){
            Order o = optional.get();
            o.setStatus(status);
            this.orderRepository.save(o); 
            return true;
        }
        return false;
    }

    public void deleteOrder(int idorder) {
        Optional<Order> optional = this.orderRepository.findById(idorder);
        optional.ifPresent(a -> {
            this.orderRepository.delete(a);
        });
    }
    
  

}
