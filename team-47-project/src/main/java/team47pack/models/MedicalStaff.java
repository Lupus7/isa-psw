package team47pack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MedicalStaff extends User {
	
	@Column(name="onVacation", unique=false, nullable=false)
	protected Boolean onVacation;
}
