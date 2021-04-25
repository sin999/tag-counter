package ru.sin666.tagcounter.domain.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sin666.tagcounter.domain.repository.PersistedCounterRepository;
import ru.sin666.tagcounter.domain.service.TagDictionaryService;
import ru.sin666.tagcounter.domain.service.impl.TagDictionaryServiceImpl;

@Configuration
public class TagDictionaryConfiguration {

  @Bean
  public TagDictionaryService tagDictionaryService(
      PersistedCounterRepository persistedCounterRepository) {
    return new TagDictionaryServiceImpl(persistedCounterRepository.findAll());
  }

}
