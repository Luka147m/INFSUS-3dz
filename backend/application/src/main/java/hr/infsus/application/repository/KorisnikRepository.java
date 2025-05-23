package hr.infsus.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.infsus.application.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

}
