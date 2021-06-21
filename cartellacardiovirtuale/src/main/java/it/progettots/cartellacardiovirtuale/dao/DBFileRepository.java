package it.progettots.cartellacardiovirtuale.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.progettots.cartellacardiovirtuale.entity.DBFile;
import it.progettots.cartellacardiovirtuale.entity.SchedaMedica;


@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
	@Query("select f from DBFile f where schedaMedica=:schedaMedica and fileType='image/jpeg'")
List<DBFile> findBySchedaMedica(@Param("schedaMedica") SchedaMedica schedaMedica);
	@Query("select f from DBFile f where schedaMedica=:schedaMedica and fileType='application/pdf'")
List<DBFile> findPDFBySchedaMedica(@Param("schedaMedica") SchedaMedica schedaMedica);
}
