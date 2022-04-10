package com.mincom.gescomext.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mincom.gescomext.entities.DomaineActivite;

public interface DomaineActiviteRepository extends JpaRepository<DomaineActivite, Long> {
	DomaineActivite findByTitreDomAct(String titreDomAct);
	List<DomaineActivite> findBytitreDomActContaining(String titreDomAct);
}
