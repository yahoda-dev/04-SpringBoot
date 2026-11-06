package com.bear.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleJPQLTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void TypedQuery를_이용한_단일메뉴_조회_테스트() {

        // when
        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String resultMenuName = query.getSingleResult();

        assertEquals("민트미역국", resultMenuName);

    }

    @Test
    public void Query를_이용한_단일메뉴_조회_테스트() {

        // given
        String jpql = "SELECT m.menuName FROM menu_section01 as m WHERE m.menuCode = 7";

        // when
        Query query = entityManager.createQuery(jpql);
        Object resultMenuName = query.getSingleResult();

        // then
        assertTrue(resultMenuName instanceof String);
        assertEquals("민트미역국", resultMenuName);

    }

    @Test
    public void TypedQuery를_이용한_단일행_조회_테스트() {

        // when
        String jpql = "SELECT m FROM menu_section01 as m WHERE m.menuCode = 7";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

        Menu foundMenu = query.getSingleResult();

        // then
        assertEquals(7, foundMenu.getMenuCode());
        System.out.println(foundMenu);

    }

    @Test
    public void TypedQuery를_이용한_다중행_조회_테스트() {

        // when
        String jpql = "SELECT m FROM menu_section01 as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);    // 반환 타입을 row와 매핑할 엔티티 타입으로 설정

        List<Menu> foundMenuList = query.getResultList();

        // then
        assertNotNull(foundMenuList);
        foundMenuList.forEach(System.out::println);

    }

    @Test
    public void Query를_이용한_다중행_조회_테스트() {

        // when
        String jpql = "SELECT m FROM menu_section01 as m";
        Query query = entityManager.createQuery(jpql);

        List<Menu> foundMenuList = query.getResultList();

        // then
        assertNotNull(foundMenuList);
        foundMenuList.forEach(System.out::println);

    }


    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트() {

        // when
        String jpql = "SELECT DISTINCT m.categoryCode FROM menu_section01 m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> categoryCodeList = query.getResultList();

        // then
        assertNotNull(categoryCodeList);
        categoryCodeList.forEach(System.out::println);

    }

    @Test
    public void in_연산자를_활용한_조회_테스트() {

        // when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.categoryCode IN (6, 10)";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        // then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    public void like_연산자를_활용한_조회_테스트() {

        // when
        String jpql = "SELECT m FROM menu_section01 m WHERE m.menuName LIKE '%마늘%'";
        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        // then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}
