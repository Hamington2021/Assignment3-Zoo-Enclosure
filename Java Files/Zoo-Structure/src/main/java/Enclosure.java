import com.example.zoostructure.Model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {
import java.util.ArrayList;
import java.util.List;


    public class Enclosure implements EnclosureCollection{
        private String name;
        private List<Animal> animals;

        /**
         * Constructs an {@code Enclosure} with the specified name.
         *
         * @param name the name of the enclosure
         * @throws IllegalArgumentException if name is null or empty
         */
        public Enclosure(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Enclosure name cannot be null or empty.");
            }
            this.name = name;
            this.animals = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Animal> getAnimals() {
            return new ArrayList<>(animals);
        }

        public void addAnimal(Animal animal) {
            if (animal == null) {
                throw new IllegalArgumentException("Animal cannot be null.");
            }
            animals.add(animal);
        }

        public boolean removeAnimal(String name) {
            return animals.removeIf(a -> a.getName().equalsIgnoreCase(name));
        }

        @Override
        public void displayInfo() {
            System.out.println("Enclosure: " + name);
            for (Animal animal : animals) {
                System.out.println("  - " + animal.getName() + " (" + animal.getAge() + " years)");
            }
        }
    }



}
