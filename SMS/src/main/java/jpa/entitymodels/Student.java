package jpa.entitymodels;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Column(name = "email",length=50,nullable=false)
	@Id
	private String sEmail;
	@Column(name = "name",length=50,nullable=false)
	private String sName;
	@Column(name = "password",length=50,nullable=false)
	private String sPass;
	@ManyToMany
	private List<Course> sCourses;
	
	// create Student constructor without parameters and initializes every member to an initial value
	public Student(){
		this.sEmail = "";
		this.sName = "";
		this.sPass = "";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sEmail == null) ? 0 : sEmail.hashCode());
		result = prime * result + ((sName == null) ? 0 : sName.hashCode());
		result = prime * result + ((sPass == null) ? 0 : sPass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (sEmail == null) {
			if (other.sEmail != null)
				return false;
		} else if (!sEmail.equals(other.sEmail))
			return false;
		if (sName == null) {
			if (other.sName != null)
				return false;
		} else if (!sName.equals(other.sName))
			return false;
		if (sPass == null) {
			if (other.sPass != null)
				return false;
		} else if (!sPass.equals(other.sPass))
			return false;
		return true;
	}

	//The second Student constructor must initialize every private member with a parameter provided to the constructor.
	public Student(String email,String name,String pass) {
		this.sEmail = email;
		this.sName = name;
		this.sPass = pass;	
	}
	
	//GETTERS and SETTERS methods
	public List<Course> getsCourses() {
		return sCourses;
	}
	
	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	public String getsPass() {
		return sPass;
	}
	public void setsPass(String sPass) {
		this.sPass = sPass;
	}
	@Override
	public String toString() {
		
		return String.format("%-10s%-18s%-15s\n", sEmail, sName, sPass);
	}
}
