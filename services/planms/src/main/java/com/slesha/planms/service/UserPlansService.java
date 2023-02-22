package com.slesha.planms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.slesha.planms.entity.UserPlan;
import com.slesha.planms.repo.UserPlanRepo;

@Service
public class UserPlansService {

    @Autowired
    private UserPlanRepo repo;

    @Cacheable(value = "userPlan",key = "#p0",sync = true)
    public List<UserPlan> findAllByEmailId(String emailId){
        return repo.findByEmailId(emailId)
        .stream()
        .map(x->{x.getUser().setPassword(""); 
        return x;})
        .collect(Collectors.toList());
    }
    
}
