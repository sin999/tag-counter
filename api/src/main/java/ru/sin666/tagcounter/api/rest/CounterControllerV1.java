package ru.sin666.tagcounter.api.rest;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/counter")
public interface CounterControllerV1 {

  @GetMapping(value = "/{space}")
  @Description("Получить значения всех счетчиков пространства")
  Map<String,Long> getAllSpaceCounters(
      @PathVariable String space);

  @GetMapping(value = "/{space}/{name}")
  @Description("Получить значение счетчика")
  Long getCounter(
      @PathVariable String space,
      @PathVariable String name);

  @PutMapping(value = "/{space}/{name}/increment")
  @Description("Инкрементировать счетчик")
  void incrementCounter(
      @PathVariable  String space,
      @PathVariable  String name);
}
