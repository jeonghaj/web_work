package test.friend.dto;

public class MemberDto {
	private int num;
	private String name;
	private String pnum;
	
	public MemberDto() {}

	public MemberDto(int num, String name, String pnum) {
		super();
		this.num = num;
		this.name = name;
		this.pnum = pnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPnum() {
		return pnum;
	}

	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
}
