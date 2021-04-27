package it.progettots.cartellacardiovirtuale.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.progettots.cartellacardiovirtuale.dao.MedicoRepository;
import it.progettots.cartellacardiovirtuale.entity.Medico;

@Service
public class MedicoServiceImpl implements MedicoService {

	private MedicoRepository medicoRepository;
	
	@Autowired
	public MedicoServiceImpl(MedicoRepository theMedicoRepository) {
		medicoRepository = theMedicoRepository;
	}
	
	@Override
	public List<Medico> findAll() {
		return medicoRepository.findAllByOrderByCognomeAsc();
	}

	@Override
	public Medico findById(int theId) {
		Optional<Medico> result = medicoRepository.findById(theId);
		Medico theMedico = null;
		
		if(result.isPresent()) {
			theMedico = result.get();
			
		}else {
			throw new RuntimeException("medico id non trovato - "+ theId);
		}
		return theMedico;
	}

	@Override
	public void save(Medico theMedico) {
		medicoRepository.save(theMedico);
		
	}

	@Override
	public void deleteById(int theId) {
		medicoRepository.deleteById(theId);
		
	}

}
