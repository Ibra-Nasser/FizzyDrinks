package com.qa.FizzyDrinks.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class FizzyDrink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String brand;

	@NotNull
	private String name;

	@NotNull
	private int sugarContent;

	@Column
	private String flavour;

	public FizzyDrink() {
	}

	public FizzyDrink(String brand, String name, int sugarContent, String flavour) {
		this.brand = brand;
		this.name = name;
		this.sugarContent = sugarContent;
		this.flavour = flavour;
	}

	public FizzyDrink(Long id, String brand, String name, int sugarContent, String flavour) {
		super();
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.sugarContent = sugarContent;
		this.flavour = flavour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(int sugarContent) {
		this.sugarContent = sugarContent;
	}

	public String getFlavour() {
		return flavour;
	}

	public void setFlavour(String flavour) {
		this.flavour = flavour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, flavour, name, sugarContent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FizzyDrink other = (FizzyDrink) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(flavour, other.flavour)
				&& Objects.equals(name, other.name) && sugarContent == other.sugarContent;
	}

	@Override
	public String toString() {
		return "FizzyDrink [id=" + id + ", brand=" + brand + ", name=" + name + ", sugarContent=" + sugarContent
				+ ", flavour=" + flavour + "]";
	}

}
