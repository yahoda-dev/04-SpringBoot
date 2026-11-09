package org.example.jpaintro.service;

import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.*;
import org.example.jpaintro.entity.Category;
import org.example.jpaintro.entity.Menu;
import org.example.jpaintro.repository.CategoryRepository;
import org.example.jpaintro.repository.MenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findAll() {
        return menuRepository.findAllWithCategory().stream()
                .map(MenuResponseDTO::from)
                .toList();
    }

    @Transactional
    public MenuResponseDTO create(MenuRequestDTO request) {
        Category category = findCategory(request.getCategoryCode());
        Menu menu = new Menu(
                request.getMenuName(),
                request.getMenuPrice(),
                category,
                request.getOrderableStatus()
        );

        Menu savedMenu = menuRepository.save(menu);
        return MenuResponseDTO.from(savedMenu);
    }

    @Transactional
    public MenuResponseDTO update(Integer menuCode, MenuRequestDTO request) {
        Menu menu = menuRepository.findById(menuCode)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "메뉴를 찾을 수 없습니다. menuCode=" + menuCode
                ));
        Category category = findCategory(request.getCategoryCode());

        menu.change(
                request.getMenuName(),
                request.getMenuPrice(),
                category,
                request.getOrderableStatus()
        );

        return MenuResponseDTO.from(menu);
    }


    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByOrderableStatus(String status) {
        List<Menu> menuList =
                menuRepository.findByOrderableStatusOrderByMenuPriceAscMenuCodeAsc(status);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByMinimumPrice(Integer price) {
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanEqualOrderByMenuPriceAscMenuCodeAsc(price);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByStatusAndMaximumPrice(Integer price, String status) {
        List<Menu> menuList = menuRepository.findByOrderableStatusAndMenuPriceLessThanEqualOrderByMenuPriceAscMenuCodeAsc(status, price);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByCategoryQueryMethod(Integer categoryCode) {
        List<Menu> menuList = menuRepository.findByCategory_CategoryCodeOrderByMenuCodeAsc(categoryCode);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByCategoryNameQueryMethod(String categoryName) {
        List<Menu> menuList = menuRepository.findByCategory_CategoryNameOrderByMenuCodeAsc(categoryName);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByMenuName(String menuName) {
        List<Menu> menuList = menuRepository.findByMenuNameContaining(menuName);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }


    @Transactional
    private Category findCategory(Integer categoryCode) {
        return categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "카테고리를 찾을 수 없습니다. categoryCode=" + categoryCode
                ));
    }

    @Transactional(readOnly = true)
    public List<CategoryMenuStatisticsDTO> findCategoryStatistics() {
        return menuRepository.findCategoryStatistics();
    }

    @Transactional(readOnly = true)
    public List<CategoryMenuCountProjection> findCategoryMenuCounts() {
        return menuRepository.findCategoryMenuCounts();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByCategory(Integer categoryCode) {
        List<Menu> menuList = menuRepository.findByCategory(categoryCode);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> searchByPriceRange(Integer minPrice, Integer maxPrice, String orderableStatus) {
        List<Menu> menuList = menuRepository.searchByPriceRange(minPrice, maxPrice, orderableStatus);
        // Validation 실습
        // - minPrice 또는 maxPrice가 NULL인 경우 -> 가격 범위를 올바르게 입력해야 합니다.
        // - minPrice와 maxPrice는 0보다 작은 경우 -> 가격 범위를 올바르게 입력해야 합니다.
        // - maxPrice가 minPrice보다 작은 경우 -> 가격 범위를 올바르게 입력해야 합니다.

        if (minPrice == null
                || maxPrice == null
                || minPrice < 0
                || maxPrice < 0
                || maxPrice < minPrice) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가격 범위를 올바르게 입력해야 합니다.");
        }

        // - status가 Y 혹은 N 이외 문자가 들어올 경우
        if (!"Y".equals(orderableStatus)
                && !"N".equals(orderableStatus)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "주문 가능 상태는 Y 또는 N이어야 합니다.");
        }


        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> searchByMenuKeyword(String menuKeyword) {
        List<Menu> menuList = menuRepository.searchByMenuKeyword(menuKeyword);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findMenusAboveCategoryAverage() {
        List<Menu> menuList = menuRepository.findMenusAboveCategoryAverage();

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional
    public PriceUpdateResponseDTO increasesPriceByCategory(Integer categoryCode, Integer increaseRate) {
        PriceUpdateResponseDTO resposne = new PriceUpdateResponseDTO();

        Category category = findCategory(categoryCode);
        int updatedCount = menuRepository.increasesPriceByCategory(category, increaseRate);
        resposne.setUpdatedCount(updatedCount);

        return resposne;
    }


    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findByCategoryNative(Integer categoryCode) {
        List<Menu> menuList = menuRepository.findByCategoryNative(categoryCode);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }


    /* Practice */
    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findMenusByPriceRange(Integer minPrice, Integer maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "가격은 0보다 작을 수 없습니다.");
        }

        if (minPrice > maxPrice) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "최소 가격이 최대 가격보다 클 수 없습니다.");
        }

        List<Menu> menuList = menuRepository.findByMenuPriceBetweenOrderByMenuPriceDescMenuCodeAsc(minPrice, maxPrice);

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findMenusByCategoryAndStatus(Integer categoryCode, String status) {
        List<Menu> menuList = menuRepository.findByCategoryCategoryCodeAndOrderableStatus(categoryCode, status);

        if (menuList.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다.");
        }

        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findOrderableMenus() {
        List<Menu> menuList = menuRepository.findOrderableMenus();
        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<?> searchMenus(Integer categoryCode, Integer maxPrice) {
        List<Menu> menuList = menuRepository.searchMenus(categoryCode, maxPrice);
        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MenuResponseDTO> findMenusBelowAverage() {
        List<Menu> menuList = menuRepository.findMenusBelowAverage();
        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }

    public List<MenuResponseDTO> findExpensiveMenusNative(Integer price) {
        List<Menu> menuList = menuRepository.findExpensiveMenusNative(price );
        return menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();
    }
}
