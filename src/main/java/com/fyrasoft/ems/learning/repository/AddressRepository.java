package com.fyrasoft.ems.learning.repository;

import com.fyrasoft.ems.learning.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
