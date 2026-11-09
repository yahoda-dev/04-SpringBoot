package org.example.jpaintro.service;

import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.entity.Category;
import org.example.jpaintro.entity.Menu;
import org.example.jpaintro.repository.CategoryRepository;
import org.example.jpaintro.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/// MenuService의 비즈니스 로직을 검증하는 단위테스트
@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void 최소가격이_NULL이면_예외가_발생한다() {
        // given
        Integer minPrice = null;
        Integer maxPrice = 10_000;
        String status = "Y";
        // 인자가 올바르지 않을 경우 개발자가 지정한 예외가 잘 발생하는지에 대한 테스트를 진행할 것

        // when
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> menuService.searchByPriceRange(minPrice, maxPrice, status));

        // then
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());

        verify(menuRepository, never()).searchByPriceRange(any(), any(), any());
    }


    @Test
    void 주문가능상태가_잘못되면_예외가_발생한다() {
        // given
        Integer minPrice = 3000;
        Integer maxPrice = 5000;
        String status = "Y";

        // when
        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> menuService.searchByPriceRange(minPrice, maxPrice, status));

        // then
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(menuRepository, never()).searchByPriceRange(any(), any(), any());
    }

    @Test
    void 주문가능상태가_Y면_메뉴를_DTO로_반환한다() {
        // given
        Integer minPrice = 3000;
        Integer maxPrice = 5000;
        String status = "Y";

        // 테스트에 필요한 가짜 Category
        Category category = mock(Category.class);

        // when().thenReturn() : getter 호출 시 반환할 값을 미리 설정하는 코드
        when(category.getCategoryCode()).thenReturn(4);
        when(category.getCategoryName()).thenReturn("한식");

        // Repository에서 반환했다고 가정하는 Menu 객체
        Menu menu = new Menu("테스트 메뉴", 5000, category, status);

        // searchByPriceRange 메서드 호출 시 반환될 값을 미리 설정하는 코드
        when(menuRepository.searchByPriceRange(minPrice, maxPrice, status)).thenReturn(List.of(menu));


        // when
        List<MenuResponseDTO> result = menuService.searchByPriceRange(minPrice, maxPrice, status);

        // then
        assertEquals("테스트 메뉴", result.get(0).getMenuName());
        assertEquals(4, result.get(0).getCategoryCode());
        assertEquals("한식", result.get(0).getCategoryName());

        verify(menuRepository.searchByPriceRange(minPrice, maxPrice, status));
    }
}