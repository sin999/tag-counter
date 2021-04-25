package ru.sin666.tagcounter.domain.service.impl;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;
import ru.sin666.tagcounter.domain.repository.entity.PersistedCounter;
import ru.sin666.tagcounter.domain.service.TagDictionaryService;


public class TagDictionaryServiceImpl implements TagDictionaryService {

  private  Map<String, Map<String, Long>> spaceToNameToSurrogateKyeMap;

  public TagDictionaryServiceImpl(Iterable<PersistedCounter> allPersistedCounters) {
    renewData(allPersistedCounters);
  }

  public void renewData(Iterable<PersistedCounter> allPersistedCounters) {
    spaceToNameToSurrogateKyeMap = StreamSupport.stream(allPersistedCounters.spliterator(), true)
        .collect(
            groupingBy(PersistedCounter::getSpace,
                toMap(PersistedCounter::getName, PersistedCounter::getId)));
  }

  @Override
  public Long getSurrogateKeyBySpaceAndName(String space, String name) {
    return Optional.ofNullable(spaceToNameToSurrogateKyeMap.get(space))
        .map(nameToCounter -> nameToCounter.get(name)).orElse(null);

  }

  @Override
  public boolean existSpace(String space) {
    return spaceToNameToSurrogateKyeMap.containsKey(space);
  }

  @Override
  public boolean existName(String space, String name) {
    return spaceToNameToSurrogateKyeMap.containsKey(space)
        && spaceToNameToSurrogateKyeMap.get(space).containsKey(name);
  }

}
