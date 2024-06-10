package co.yedam;

public class Test {
	public static void main(String[] args) {
		Student std = new Student();
		std.setBldType("O");
		std.setPhone("010-2222-3333");
		std.setStdName("HOng");
		std.setStdNo("S0010");
		StudentDAO sdao = new StudentDAO();
		if (sdao.insertStudent(std)) {
			System.out.print("<b>OK</b>");
		} else {
			System.out.print("<b>Fail</b>");
		}
	}
}
