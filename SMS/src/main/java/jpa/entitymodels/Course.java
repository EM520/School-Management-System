package jpa.entitymodels;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Column(name = "id",nullable=false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	@Column(name = "name",length=50,nullable=false)
	private String cName;
	@Column(name = "Instructor",length=50,nullable=false)
	private String cInstructorName;
	
	// create Course constructor without parameters and initializes every member to an initial value
		public Course(){
			this.cId = 0;
			this.cName = "";
			this.cInstructorName = "";
		}
		
	//The second Student constructor must initialize every private member with a parameter provided to the constructor.
		public Course(String cName,String cInstructorName) {
			//this.cId = cId ;
			this.cName = cName;
			this.cInstructorName = cInstructorName;		
		}
	//GETTERS and SETTERS methods
		
		public int getcId() {
			return cId;
		}

		public void setcId(int cId ) {
			this.cId  = cId;
		}
		
		public String getcName() {
			return cName;
		}

		public void setcName(String cName) {
			this.cName = cName;
		}
		
		public String getcInstructorName() {
			return cInstructorName;
		}

		public void setcInstructorName(String cInstructorName) {
			this.cInstructorName = cInstructorName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + cId;
			result = prime * result + ((cInstructorName == null) ? 0 : cInstructorName.hashCode());
			result = prime * result + ((cName == null) ? 0 : cName.hashCode());
			return result;
		}

		@Override
		public String toString() {
			return String.format("%-15s%-18s%-15s\n", cId, cName, cInstructorName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Course))
				return false;
			Course other = (Course) obj;
			if (cId != other.cId)
				return false;
			if (cInstructorName == null) {
				if (other.cInstructorName != null)
					return false;
			} else if (!cInstructorName.equals(other.cInstructorName))
				return false;
			if (cName == null) {
				if (other.cName != null)
					return false;
			} else if (!cName.equals(other.cName))
				return false;
			return true;
		}
}
