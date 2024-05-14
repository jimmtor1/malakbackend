package com.malak.controller;

import com.malak.dto.OrderDto;
import com.malak.model.Element;
import com.malak.model.Order;
import com.malak.repository.ElementRepository;
import com.malak.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ElementRepository elementRepository;

    @GetMapping
    public Iterable<OrderDto> OrderListNoApproved() {
        return this.orderService.OrderListByApproved(1);
    }
    
    @GetMapping("all")
    public Iterable<Order> OrderList() {
        return this.orderService.AllOrderList();
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return this.orderService.saveOrder(order);
    }
    
    @PostMapping("elements")
    public boolean saveElements(@RequestBody List<Element> elements) {
        this.elementRepository.saveAll(elements);
        return true;
    }

    //para aprobar y editar orden
    @PutMapping()
    public Order editOrder(@RequestBody Order order) {
        if (order.getId() == null) {
            return null;
        }
        return this.orderService.saveOrder(order);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable String id) {
        this.orderService.archiveOrder(id);
    }
    
    @GetMapping("approve/{id}/{status}")
    public boolean changeStatusOrder(@PathVariable String id, @PathVariable int status) {
        return this.orderService.changeStatusOrder(id, status);
    }

    @GetMapping("approved")
    public Iterable<OrderDto> OrderListApproved() {
        return this.orderService.OrderListByApproved2();
    }

    @GetMapping("{customer}")
    public Iterable<Order> OrderListByCustomer(@PathVariable int customer) {
        //Iterator<Order> o = this.orderService.OrderListByCustomer(customer).iterator();
        
        var b = this.orderService.OrderListByCustomer(customer);
//        b.forEach(a->System.out.println(a));
        
//        while (o.hasNext()) {            
//            System.out.println(o.next().getCustomer());
//        }

        return b;
    }

    @GetMapping("nextsuffix/{suffix}")
    public int lastOrder(@PathVariable char suffix) {
        return this.orderService.nextOrderSubId(suffix);
    }
    
    @GetMapping("elements/{idorder}")
    public Iterable<Element> ElemenstByOrder(@PathVariable String idorder){ 
        Order o = new Order(idorder);     
        return this.elementRepository.findByOrder(o); 
    }


}
