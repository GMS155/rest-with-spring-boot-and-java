package com.fab.treinamento.repositoryV2.repository;

import com.fab.treinamento.modelV2.PersonV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryV2 extends JpaRepository<PersonV2, Long> {
}
