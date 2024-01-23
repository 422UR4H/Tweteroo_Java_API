package com.tweteroo.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
  @NotBlank
  private String avatar;

  @NotBlank
  @Size(max = 100, message = "Maximum length of username is 100 characters")
  private String username;
}
