package com.yahodadev.jpaintro.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
@Getter // 변형이 되면 안 되기 때문에 Setter는 만들지 않는다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 일반적으로 접근 제어자는 프로텍트로 지정한다.
@ToString
public class Menu {
    /* Columns */
    @Id // PK에게 붙여주는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 식별자를 의미한다.(=Auto Increament로 지정)
    @Column(name = "menu_code")
    private Integer menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Integer menuPrice;

//    @Column(name = "category_code")
//    private Integer categoryCode;

    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_code")
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    /* Constructor */
    // 조회가 되는지 확인하는 용도로 만들어진 임시 생성자임. 표준적으로 사용되는 방식은 아니다
    public Menu(String menuName, Integer menuPrice, Category category, String orderableStatus) {
        change(menuName, menuPrice, category, orderableStatus);
    }

    public void change(String menuName, Integer menuPrice, Category category, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }
}