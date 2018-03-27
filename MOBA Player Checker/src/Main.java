
public class Main {

	public class Person {
	    String name;

	    public Person(String personName) {
	        name = personName;
	    }

		public String greet(String yourName) {
	        return String.format("Hi %s, my name is %s", name, yourName);
	    }
	}

	public static void main(String[] args) {
		
		Main m = new Main();
		
		Person p = m.new Person("John");

		System.out.println(p.greet("qwer"));
		
		
	}
	
	
	
	

}
