package models;

import javax.persistence.Table;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "drugs")
public class Drug extends Entity<Integer>{
    private String name;
    private Integer price;
    private String drugCategory;
    private String details;

    public Drug() {
    }

    public Drug(String name, Integer price, String drugCategory, String details) {
        this.name = name;
        this.price = price;
        this.drugCategory = drugCategory;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDrugCategory() {
        return drugCategory;
    }

    public void setDrugCategory(String drugCategory) {
        this.drugCategory = drugCategory;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(name, drug.name) && Objects.equals(price, drug.price) && Objects.equals(drugCategory, drug.drugCategory) && Objects.equals(details, drug.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, drugCategory, details);
    }
}
