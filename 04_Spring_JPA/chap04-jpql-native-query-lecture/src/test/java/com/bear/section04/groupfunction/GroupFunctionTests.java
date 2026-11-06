package com.bear.section04.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupFunctionTests {
	
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
	public void 특정_카테고리의_등록된_메뉴_수_조회() {
		
		// given
		int categoryCodeParameter = 4;
		
		// when
		String jpql = "SELECT COUNT(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode = :categoryCode";
		long countOfMenu = entityManager.createQuery(jpql, Long.class)
				.setParameter("categoryCode", categoryCodeParameter)
				.getSingleResult();
		
		// then
		assertTrue(countOfMenu >= 0);
		System.out.println("countOfMenu = " + countOfMenu);
		
	}
	
	@Test
	public void count를_제외한_다른_그룹함수의_조회결과가_없는_경우_테스트() {
		
		// given
		int categoryCodeParameter = 3;

		// when
		String jpql = "SELECT SUM(m.menuPrice) FROM menu_section04 m WHERE m.categoryCode = :categoryCode";

		// then
		assertThrows(NullPointerException.class, () -> {
			long sumOfPrice = entityManager.createQuery(jpql, Long.class)
					.setParameter("categoryCode", categoryCodeParameter)
					.getSingleResult();

		});

		assertDoesNotThrow(() -> {
			Long sumOfPrice = entityManager.createQuery(jpql, Long.class)
					.setParameter("categoryCode", categoryCodeParameter)
					.getSingleResult();

		});
	}
	
	@Test
	public void group_by절과_having절을_사용한_조회_테스트() {
		
		//given
		long minPrice = 50000L;
		
		//when
		String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) " +
						"FROM menu_section04 m " +
						"GROUP BY m.categoryCode " +
						"HAVING SUM(m.menuPrice) >= :minPrice";
		
		List<Object[]> sumPriceOfCategoryList = entityManager.createQuery(jpql, Object[].class)
				.setParameter("minPrice", minPrice)
				.getResultList();
		
		//then
		assertNotNull(sumPriceOfCategoryList);
		sumPriceOfCategoryList.forEach(row -> {
			Arrays.stream(row).forEach(System.out::println);
		});
	}
}
