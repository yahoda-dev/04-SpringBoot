package org.example.jpaintro.dto;



public interface CategoryMenuCountProjection {
    /// 카테고리 코드
    Integer getCategoryCode();
    /// 카테고리 이름
    String getCategoryName();
    /// 메뉴 개수
    Long getMenuCount();
}
