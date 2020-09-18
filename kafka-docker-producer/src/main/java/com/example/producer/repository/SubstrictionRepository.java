package com.example.producer.repository;

import com.example.producer.model.Subscription;
import com.example.producer.model.SubscriptionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubstrictionRepository extends JpaRepository<Subscription, Long>{

    Set<Subscription> findByStatus(SubscriptionInterface.SubscriptionStatus notSend);
}
