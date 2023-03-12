public class Gardener {
	private String name;
	private int grade;
	private String email;
	private int vHours;
	private String reason;
	private String[] daysAvailable;

	public Gardener() {

	}

	public String getName() {
		return name;
	}

	public int getGrade() {
		return grade;
	}

	public String getEmail() {
		return email;
	}

	public int getVHours() {
		return vHours;
	}

	public String getReason() {
		return reason;
	}

	public String[] getDaysAvailable() {
		return daysAvailable;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setGrade(int newGrade) {
		grade = newGrade;
	}

	public void setEmail(String newEmail) {
		email = newEmail;
	}

	public void setVHours(int newVHours) {
		vHours = newVHours;
	}

	public void setReason(String newReason) {
		reason = newReason;
	}

	public void setDaysAvailable(String[] newDays) {
		// code??
	}

	public void addVHours(int addend) {
		vHours += addend;
	}

	public void subVHours(int subtract) {
		vHours -= subtract;
	}

}
