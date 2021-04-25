package ru.sin666.tagcounter.domain.controller;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sin666.tagcounter.domain.service.CounterService;
import ru.sin666.tagcounter.domain.service.TagDictionaryService;
import ru.sin666.tagcounter.api.rest.CounterControllerV1;

@Component
@AllArgsConstructor
public class CounterControllerImpl implements CounterControllerV1 {

  private final CounterService counterService;
  private final TagDictionaryService tagDictionaryService;


  @Override
  public Map<String, Long> getAllSpaceCounters(String space) {
    if (tagDictionaryService.existSpace(space)) {
      return counterService.getAllBySpace(space);
    } else {
      throw new IllegalArgumentException(String.format("Wrong space %S", space));
    }
  }

  @Override
  public Long getCounter(String space, String name) {
    if (tagDictionaryService.existName(space, name)) {
      return counterService.getCounter(space, name);
    }else {
      throw new IllegalArgumentException(String.format("Wrong space %S or name %S", space, name));
    }
  }

  @Override
  public void incrementCounter(String space, String name) {
    if (tagDictionaryService.existName(space, name)) {
      counterService.increment(space, name);
    }else {
      throw new IllegalArgumentException(String.format("Wrong space %S or name %S", space, name));
    }
  }


}
