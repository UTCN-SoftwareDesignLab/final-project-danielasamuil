package com.example.finalprojectdanielasamuil.service;

import com.example.finalprojectdanielasamuil.mapper.ClassSubscriptionMapper;
import com.example.finalprojectdanielasamuil.mapper.UserMapper;
import com.example.finalprojectdanielasamuil.model.ClassSubscription;
import com.example.finalprojectdanielasamuil.model.FitnessClass;
import com.example.finalprojectdanielasamuil.model.User;
import com.example.finalprojectdanielasamuil.model.dtos.ClassSubscriptionDto;
import com.example.finalprojectdanielasamuil.repository.ClassSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class ClassSubscriptionService {

    private final ClassSubscriptionRepository classSubscriptionRepository;

    private final UserService userService;

    private final FitnessClassService fitnessClassService;

    private final ClassSubscriptionMapper classSubscriptionMapper;

    private final UserMapper userMapper;

    private final JavaMailSender javaMailSender;

    public ClassSubscription findById(Integer id) {
        return classSubscriptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class subscription not found: " + id));
    }

    public List<ClassSubscriptionDto> findAll() {
        List<ClassSubscriptionDto> classSubscriptionDtoList = new ArrayList<>();

        List<ClassSubscription> classSubscriptions = classSubscriptionRepository.findAll();

        for(ClassSubscription c: classSubscriptions){
            ClassSubscriptionDto classSubscriptionDto = ClassSubscriptionDto.builder()
                    .id(c.getId())
                    .fitnessClassId(c.getFitnessClass().getId())
                    .customerId(c.getCustomer().getId())
                    .build();
            classSubscriptionDtoList.add(classSubscriptionDto);
        }

        return classSubscriptionDtoList;
    }

    public void delete(int id) {

        classSubscriptionRepository.deleteById(id);
    }

    public void deleteAll() {

        classSubscriptionRepository.deleteAll();
    }

    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("test.email.danielasamuil@gmail.com");

        msg.setSubject("New subscription to our gym");
        msg.setText("Hello \n This is your confirmation email for your new gym class subscription \n Have a nice day");

        javaMailSender.send(msg);
    }

    private Boolean makeTransactionSubscription(User customer, ClassSubscription classSubscription, Integer amount) {

        if (amount >= 0) {
            classSubscriptionMapper.toDto(classSubscriptionRepository.save(classSubscription));
            Integer subscriptionsSoFar = customer.getNrOfSubscriptionsSoFar();
            subscriptionsSoFar++;
            customer.setNrOfSubscriptionsSoFar(subscriptionsSoFar);
            customer.setAmountOfMoney(amount);
            userService.update(customer.getId(), userMapper.toDto(customer));

            sendEmail();
            return true;
        } else return false;
    }

    public Boolean create(ClassSubscriptionDto classSubscriptionDto) {

        User customer = userService.findById(classSubscriptionDto.getCustomerId());

        FitnessClass fitnessClass = fitnessClassService.findById(classSubscriptionDto.getFitnessClassId());

        ClassSubscription classSubscription = classSubscriptionMapper.fromDto(classSubscriptionDto);

        classSubscription.setFitnessClass(fitnessClass);
        classSubscription.setCustomer(customer);

        if (!customer.getIsLoyal()) {
            return makeTransactionSubscription(customer, classSubscription, customer.getAmountOfMoney() - fitnessClass.getPrice());
        }
        else {
            Integer discount = 20 / 100 * fitnessClass.getPrice();
            Integer newPrice = fitnessClass.getPrice() - discount;
            return makeTransactionSubscription(customer, classSubscription, customer.getAmountOfMoney() - newPrice);
        }
    }
}
