import java.util.Arrays;


public class Caller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s[]={"Prince","Vikram","Arun","Aruna","Nupur","Mansi","Deepa"};
		
		Employee e[]={
				new Employee("Prince", 101, 89898),
				new Employee("Vikram", 102, 67677),
				new Employee("Arun", 103, 56565),
				new Employee("Vishal", 104, 909090),
				new Employee("Nupur", 105, 222333),
				
				
				
		};
		
		Arrays.sort(e);
		
		for(Employee obj:e){
			System.out.println(obj);
		}
		java.util.Arrays.sort(s);
		for(String obj:s){
			System.out.println(obj);
		}

	}
	
	
	

}

class Employee implements Comparable<Employee>{
	String name;
	int code;
	int salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [code=" + code + ", name=" + name + ", salary="
				+ salary + "]";
	}
	public Employee(String name, int code, int salary) {
		super();
		this.name = name;
		this.code = code;
		this.salary = salary;
	}
	public Employee() {
		super();
	}
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		if(o.salary>this.salary)
			return -1;
		else if(o.salary<this.salary)
			return 1;
		return 0;
	}
	
}
