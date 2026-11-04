package com.yahodadev.jpaintro.repository;


import com.yahodadev.jpaintro.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;


// JPA Repository<엔티티 타입, 식별자 타입>
public interface MenuRepository extends JpaRepository<Menu, Integer> {

}