package team47pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team47pack.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}