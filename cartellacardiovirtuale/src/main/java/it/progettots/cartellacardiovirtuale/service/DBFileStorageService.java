package it.progettots.cartellacardiovirtuale.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import it.progettots.cartellacardiovirtuale.dao.DBFileRepository;
import it.progettots.cartellacardiovirtuale.dao.SchedaMedicaDAO;
import it.progettots.cartellacardiovirtuale.entity.DBFile;
import it.progettots.cartellacardiovirtuale.exception.FileStorageException;
import it.progettots.cartellacardiovirtuale.exception.MyFileNotFoundException;

@Service
public class DBFileStorageService {
	@Autowired
	private DBFileRepository dbFileRepository;

	@Autowired
	private SchedaMedicaDAO schedaMedicaDAO;

	public DBFile storeFile(MultipartFile file, String username) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
			dbFile.setSchedaMedica(schedaMedicaDAO.findByUtente(username));
			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public DBFile getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

	public List<DBFile> getFilesByPaziente(String username) {
		dbFileRepository.findBySchedaMedica(schedaMedicaDAO.findByUtente(username));

		return dbFileRepository.findBySchedaMedica(schedaMedicaDAO.findByUtente(username));
	}

	public List<DBFile> getPDFFilesByPaziente(String username) {
		dbFileRepository.findBySchedaMedica(schedaMedicaDAO.findByUtente(username));

		return dbFileRepository.findPDFBySchedaMedica(schedaMedicaDAO.findByUtente(username));
	}
}
