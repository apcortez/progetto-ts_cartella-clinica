package it.progettots.cartellacardiovirtuale.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.progettots.cartellacardiovirtuale.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
	//add a method to sort by last name
	public List<Medico> findAllByOrderByCognomeAsc();
}
