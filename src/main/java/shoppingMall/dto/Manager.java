package shoppingMall.dto;

public class Manager {
	private int Managerno;
	
	private String ManagerId;
	private String ManagerPass;
	public int getManagerno() {
		return Managerno;
	}
	
	public Manager() {
	}

	public Manager(String managerId, String managerPass) {
		ManagerId = managerId;
		ManagerPass = managerPass;
	}

	public void setManagerno(int managerno) {
		Managerno = managerno;
	}
	public String getManagerId() {
		return ManagerId;
	}
	public void setManagerId(String managerId) {
		ManagerId = managerId;
	}
	public String getManagerPass() {
		return ManagerPass;
	}
	public void setManagerPass(String managerPass) {
		ManagerPass = managerPass;
	}

	@Override
	public String toString() {
		return String.format("Manager [Managerno=%s, ManagerId=%s, ManagerPass=%s]", Managerno, ManagerId, ManagerPass);
	}
	
	
}
