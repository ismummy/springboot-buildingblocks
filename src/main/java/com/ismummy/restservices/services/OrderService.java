package com.ismummy.restservices.services;

import com.ismummy.restservices.entities.Order;
import com.ismummy.restservices.entities.User;
import com.ismummy.restservices.exceptions.UserNotFoundException;
import com.ismummy.restservices.repositories.OrderRepository;
import com.ismummy.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Order> getAllOrders(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException("Invalid user Id passed!");
        }
        return user.get().getOrders();
    }

    public Order createOrder(Order order, Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException("Invalid user Id passed!");
        }
        order.setUser(user.get());

        return orderRepository.save(order);
    }
}
