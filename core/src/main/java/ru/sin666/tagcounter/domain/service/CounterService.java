package ru.sin666.tagcounter.domain.service;

import java.util.Map;

public interface CounterService {

  void increment(String space, String name);

  Map<String,Long> getAllBySpace(String space);

  Long getCounter(String space, String name);
}
