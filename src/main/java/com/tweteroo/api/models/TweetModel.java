package com.tweteroo.api.models;

import lombok.Data;

import com.tweteroo.api.dtos.TweetDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
public class TweetModel {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false, length = 280)
  private String text;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private UserModel user;

  public TweetModel(TweetDTO dto) {
    this.text = dto.getText();
    // this.user.setId(dto.getUserId());
  }

  public TweetModel(TweetDTO dto, UserModel user) {
    this.text = dto.getText();
    this.user = user;
  }
}
