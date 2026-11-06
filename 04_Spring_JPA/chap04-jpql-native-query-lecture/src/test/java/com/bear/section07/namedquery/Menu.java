package com.bear.section07.namedquery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import jakarta.persistence.*;

@Entity(name="menu_section07")
@Table(name="tbl_menu")
@NamedQueries({
	@NamedQuery(name="menu_section07.selectMenuList", query="SELECT m FROM menu_section07 m")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {

	@Id
	@Column(name="menu_code")
	private int menuCode;

	@Column(name="menu_name")
	private String menuName;

	@Column(name="menu_price")
	private int menuPrice;

	@Column(name="category_code")
	private int categoryCode;

	@Column(name="orderable_status")
	private String orderableStatus;
}
