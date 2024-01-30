package com.tweteroo.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TweetDTO {
  @NotNull(message = "Field userId is mandatory")
  private Long userId;

  @NotBlank(message = "Field text is mandatory")
  @Size(max = 280, message = "Maximum length of tweet is 280 characters")
  private String text;
}
