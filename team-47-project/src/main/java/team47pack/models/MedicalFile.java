package team47pack.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "medicalfile")
public class MedicalFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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
