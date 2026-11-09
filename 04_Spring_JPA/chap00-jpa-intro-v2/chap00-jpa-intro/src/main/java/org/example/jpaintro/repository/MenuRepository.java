package org.example.jpaintro.repository;

import org.example.jpaintro.dto.CategoryMenuCountProjection;
import org.example.jpaintro.dto.CategoryMenuStatisticsDTO;
import org.example.jpaintro.entity.Category;
import org.example.jpaintro.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    /* JPQL lecture */
    @Query("select menu from Menu menu join fetch menu.category")
    List<Menu> findAllWithCategory();

    @Query("""
            select new org.example.jpaintro.dto.CategoryMenuStatisticsDTO(
            category.categoryCode,
            category.categoryName,
            count(menu),
            avg(menu.menuPrice)
            ) from Menu menu
            join menu.category category
            group by category.categoryCode, category.categoryName
            order by category.categoryCode
            """)
    List<CategoryMenuStatisticsDTO> findCategoryStatistics();


    @Query("""
            select
            category.categoryCode as categoryCode,
            category.categoryName as categoryName,
            count(menu) as menuCount      
            from Menu menu
            join menu.category category
            group by category.categoryCode, category.categoryName
            order by category.categoryCode
            """)
    List<CategoryMenuCountProjection> findCategoryMenuCounts();


    @Query("""
            select menu
            from Menu menu
            join menu.category
            where 1=1
                AND menu.category.categoryCode = :categoryCode
            order by menu.menuCode asc
            """)
    List<Menu> findByCategory(Integer categoryCode);


    @Query("""
            select menu
            from Menu menu
            join fetch menu.category
            where 1=1
                AND menu.menuPrice BETWEEN :minPrice AND :maxPrice
                AND menu.orderableStatus = :orderableStatus
            order by menu.menuPrice asc, menu.menuCode asc
            """)
    List<Menu> searchByPriceRange(
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice,
            @Param("orderableStatus") String orderableStatus);

    @Query("""
            select menu
            from Menu menu
            join fetch menu.category
            where 1=1
                and menu.menuName LIKE %:menuKeyword%
            order by menu.menuCode asc
            """)
    List<Menu> searchByMenuKeyword(String menuKeyword);

    @Query("""
            select menu
            from Menu menu
            join fetch menu.category
            where 1=1
                and menu.menuPrice > (
                    select avg(subMenu.menuPrice)
                    from Menu subMenu
                    where subMenu.category = menu.category
                )
            order by menu.category.categoryCode asc
            """)
    List<Menu> findMenusAboveCategoryAverage();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            update Menu menu
            set menu.menuPrice = menu.menuPrice + (menu.menuPrice * (:increaseRate / 100))
            where menu.category = :category
            """)
    int increasesPriceByCategory(Category category, Integer increaseRate);

    /* Query Method Lecture */
    List<Menu> findByOrderableStatusOrderByMenuPriceAscMenuCodeAsc(
            String orderableStatus);

    List<Menu> findByMenuPriceGreaterThanEqualOrderByMenuPriceAscMenuCodeAsc(
            Integer price);

    List<Menu> findByOrderableStatusAndMenuPriceLessThanEqualOrderByMenuPriceAscMenuCodeAsc(
            String orderableStatus, Integer menuPrice);


    List<Menu> findByCategory_CategoryCodeOrderByMenuCodeAsc(
            Integer categoryCode);

    List<Menu> findByCategory_CategoryNameOrderByMenuCodeAsc(
            String categoryName);

    List<Menu> findByMenuNameContaining(
            String menuName);

    /* Native Query Lecture */

    @Query(value = """
            select menu.*
            from tbl_menu menu
            where 1=1
            and menu.category_code = :categoryCode
            order by menu.menu_code asc
            """, nativeQuery = true)
    List<Menu> findByCategoryNative(Integer categoryCode);


    /* Practice Method */
    List<Menu> findByMenuPriceBetweenOrderByMenuPriceDescMenuCodeAsc(Integer minPrice, Integer maxPrice);

    List<Menu> findByCategoryCategoryCodeAndOrderableStatus(Integer categoryCode, String status);

    @Query("""
            select menu
            from Menu menu
            join fetch menu.category category
            where menu.orderableStatus = 'Y'
            order by menu.menuPrice asc
            """)
    List<Menu> findOrderableMenus();


    @Query("""
            select menu
            from Menu menu
            join fetch menu.category category
            where 1=1
            and menu.category.categoryCode = :categoryCode
            and menu.menuPrice < :maxPrice
            order by menu.menuPrice asc
            """)
    List<Menu> searchMenus(Integer categoryCode, Integer maxPrice);


    @Query("""
            select menu
            from Menu menu
            where menu.menuPrice < (
                        select AVG(subMenu.menuPrice)
                        from Menu subMenu
                        )
            order by menu.menuPrice asc
            """)
    List<Menu> findMenusBelowAverage();

    @Query(value = """
            select menu.*
            from menudb.tbl_menu menu
            where menu.menu_price > :price
            """, nativeQuery = true)
    List<Menu> findExpensiveMenusNative(Integer price);
}
