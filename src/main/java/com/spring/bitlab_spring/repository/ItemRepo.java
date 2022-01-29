package com.spring.bitlab_spring.repository;


import com.spring.bitlab_spring.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<ShopItems, Long> {

}
