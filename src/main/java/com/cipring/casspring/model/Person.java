package com.cipring.casspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;


import java.util.UUID;

public @Data class Person {
  private @Getter final UUID id;

  // @NotBlank
  private @Getter final String name;

  public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }
}
