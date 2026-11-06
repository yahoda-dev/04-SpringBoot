package com.yahodadev.jpaintro.dto;


import com.yahodadev.jpaintro.entity.Menu;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuRespDTO {
    private Integer menuCode;
    private String menuName;
    private Integer menuPrice;
    private Integer categoryCode;
    private String orderableStatus;

    public static MenuRespDTO from(Menu menu) {
        return new MenuRespDTO(
                menu.getMenuCode(), menu.getMenuName(), menu.getMenuPrice(), menu.getCategory().getCategoryCode(), menu.getOrderableStatus()
        );
    }
}
