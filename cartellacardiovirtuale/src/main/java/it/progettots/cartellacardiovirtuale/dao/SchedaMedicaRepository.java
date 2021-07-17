package it.progettots.cartellacardiovirtuale.dao;
import it.progettots.cartellacardiovirtuale.entity.SchedaMedica;

public interface SchedaMedicaRepository{
SchedaMedica findByUtente(String username);
}
 