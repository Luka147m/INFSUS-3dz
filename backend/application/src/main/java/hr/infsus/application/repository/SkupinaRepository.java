package hr.infsus.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.Skupina;

public interface SkupinaRepository extends JpaRepository<Skupina, Integer> {

}