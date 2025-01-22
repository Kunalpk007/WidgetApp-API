package com.talentreef.interviewquestions.takehome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Table
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Widget {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "id", nullable = false)
  private @Getter @Setter Long id;

  @NotBlank
  @Size(min = 3, max = 100)
  private @Getter @Setter String name;

  @NotBlank
  @Size(min = 5, max = 1000)
  private @Getter @Setter String description;

  @DecimalMin(value = "1.00")
  @Min(value = 1)
  private @Getter @Setter BigDecimal price;

  // Getters and setters
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public void setDescription(String description) {
//    this.description = description;
//  }
//
//  public BigDecimal getPrice() {
//    return price;
//  }
//
//  public void setPrice(BigDecimal price) {
//    this.price = price;
//  }

}
