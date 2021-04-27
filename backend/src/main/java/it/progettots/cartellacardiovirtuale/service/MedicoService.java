package it.progettots.cartellacardiovirtuale.service;

import java.util.List;

import it.progettots.cartellacardiovirtuale.entity.Medico;

public interface MedicoService {
	public List<Medico> findAll();
	public Medico findById(int theId);
	public void save(Medico theMedico);
	public void deleteById(int theId);
}
