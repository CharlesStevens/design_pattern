package abstractfactory;

public class SeaFactory implements AnimalFactory {

	public Animal createAnimal() {
		return new Shark();
	}

}