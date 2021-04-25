package ru.sin666.tagcounter.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@Data
@AllArgsConstructor
public class PersistedCounter {
  @Id
  private Long id;
  private String space;
  private String name;
  private Long value;
}
