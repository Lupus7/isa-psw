package team47pack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicalfile")
public class MedicalFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "med_file_id", referencedColumnName = "id", nullable = false)
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
		return this.entries;
	}

	public void setEntries(List<MedFileEntry> entries) {
		this.entries = entries;
	}

	// private Patient patient;

}
