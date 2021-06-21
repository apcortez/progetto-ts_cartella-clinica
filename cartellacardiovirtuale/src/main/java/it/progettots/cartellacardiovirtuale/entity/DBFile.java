package it.progettots.cartellacardiovirtuale.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DBfiles")
public class DBFile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;
	

	@ManyToOne
	@JoinColumn(name = "scheda_medica", nullable = true)
	private SchedaMedica schedaMedica;

	public DBFile() {

	}

	public DBFile(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public SchedaMedica getSchedaMedica() {
		return schedaMedica;
	}

	public void setSchedaMedica(SchedaMedica schedaMedica) {
		this.schedaMedica = schedaMedica;
	}

}
