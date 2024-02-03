package br.com.jg.advancedquiz.repository;

import br.com.jg.advancedquiz.model.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
    Alternative findByDescription(String description);
}
