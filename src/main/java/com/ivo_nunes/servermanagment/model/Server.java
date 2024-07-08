package com.ivo_nunes.servermanagment.model;

import com.ivo_nunes.servermanagment.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  @Column(unique = true)
  @NotEmpty(message = "IP Address cannot be empty or null")
  private String ipAddress;
  private String name;
  private String memory;
  private String type;
  private String imgUrl;
  private Status status;

}
