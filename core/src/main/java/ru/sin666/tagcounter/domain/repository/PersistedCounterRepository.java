package ru.sin666.tagcounter.domain.repository;

import java.util.Collection;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sin666.tagcounter.domain.repository.entity.PersistedCounter;


@Repository
public interface PersistedCounterRepository extends CrudRepository<PersistedCounter, Long>{

  @Modifying
  @Query("UPDATE PERSISTED_COUNTER SET value = value + 1 WHERE id = :id")
  boolean increment(@Param("id") Long id);

  Collection<PersistedCounter> findAllBySpace(String space);

}
