package ru.sin666.tagcounter.domain.service;



public interface TagDictionaryService {

  Long getSurrogateKeyBySpaceAndName(String space, String name);
  boolean existSpace(String space);
  boolean existName(String space, String name);

}
