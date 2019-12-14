package team47pack.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "medicalfile")
public class MedicalFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MedFileEntry> entries = new ArrayList<>();

	public MedicalFile() {
		
	}

	public MedicalFile(Long id, List<MedFileEntry> entries) {
		this.id = id;
		this.entries = entries;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MedFileEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<MedFileEntry> entries) {
		this.entries = entries;
	}

	// private Patient patient;

}
