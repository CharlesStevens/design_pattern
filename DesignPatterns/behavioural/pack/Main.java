package pack;


public class Main {


  public static void main(String[] args) {
    MyModel model = new MyModel();
    MyObserver observer = new MyObserver(model);
   NewObserver observer1=new NewObserver(model);
    // We change the last name of the person, observer will get notified
    for ( MyModel.Person person : model.getPersons()) {
        person.setLastName(person.getLastName() + "1");
    }
    // We change the  name of the person, observer will get notified
    for ( MyModel.Person person : model.getPersons()) {
        person.setFirstName(person.getFirstName() + "1");
    }
    
  }
} 