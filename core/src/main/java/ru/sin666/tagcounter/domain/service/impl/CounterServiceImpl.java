package ru.sin666.tagcounter.domain.service.impl;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sin666.tagcounter.domain.repository.entity.PersistedCounter;
import ru.sin666.tagcounter.domain.repository.PersistedCounterRepository;
import ru.sin666.tagcounter.domain.service.CounterService;
import ru.sin666.tagcounter.domain.service.TagDictionaryService;

@Component
@AllArgsConstructor
public class CounterServiceImpl implements CounterService {

  private PersistedCounterRepository persistedCounterRepository;
  private TagDictionaryService tagDictionaryService;

  @Override
  @Transactional
  public void increment(String space, String name) {
    Long surrogateKey = getSurrogateKey(space, name);
    boolean result = persistedCounterRepository.increment(surrogateKey);
//    if (!result) {
//      throw new NoSuchElementException(String
//          .format("The element with space %S, name %S and surrogateKey %d in data base found ",
//              space, name, surrogateKey));
//    }
  }

  @Override
  public Map<String, Long> getAllBySpace(String space) {
    return persistedCounterRepository.findAllBySpace(space)
        .stream()
        .collect(toMap(PersistedCounter::getName, PersistedCounter::getValue));
  }

  @Override
  public Long getCounter(String space, String name) {
    long surrogateKey = getSurrogateKey(space, name);
    Optional<PersistedCounter> persistedCounterOptional = persistedCounterRepository.findById(surrogateKey);
    return persistedCounterOptional.orElseThrow(()->new NoSuchElementException(String
        .format("The element with space %S, name %S and surrogateKey %d in data base found ", space,
            name, surrogateKey))).getValue();

  }

  private long getSurrogateKey(String space, String name) {
    Long surrogateKey = tagDictionaryService.getSurrogateKeyBySpaceAndName(space, name);
    if (nonNull(surrogateKey)) {
      return surrogateKey;
    }
    throw new NoSuchElementException(
        String.format("No such element - space %S and name %S ", space, name));
  }
}
