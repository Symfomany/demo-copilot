# Demo
Checkout Sandbox

##  Ecrire du Java

String nom = "Boyer";
System.out.println("Bonjour " + nom);
int age = 36
System.out.println("Vous avez " + age + " ans");
double taille = 1.75;
System.out.println("Vous mesurez " + taille + " m");
boolean estUnHomme = true;
System.out.println("Vous êtes un homme : " + estUnHomme);
char premiereLettre = 'B';
System.out.println("La première lettre de votre nom est : " + premiereLettre);

## Jouer avec couleurs


    String nom = "\u001B[34mBoyer\u001B[0m";
    System.out.println("\u001B[32m*************************\u001B[0m");
    System.out.println("\u001B[32m* Bonjour " + nom + " *\u001B[0m");
    System.out.println("\u001B[32m*************************\u001B[0m");
    int age = 36;
    System.out.println("\u001B[32m*************************\u001B[0m");
    System.out.println("\u001B[32m* Vous avez " + age + " ans *\u001B[0m");
    System.out.println("\u001B[32m*************************\u001B[0m");
    double taille = 1.75;
    System.out.println("\u001B[32m*************************\u001B[0m");
    System.out.println("\u001B[32m* Vous mesurez " + taille + " m *\u001B[0m");
    System.out.println("\u001B[32m*************************\u001B[0m");
    boolean estUnHomme = true;
    System.out.println("\u001B[32m*************************\u001B[0m");
    System.out.println("\u001B[32m* Vous êtes un homme : " + estUnHomme + " *\u001B[0m");
    System.out.println("\u001B[32m*************************\u001B[0m");
    char premiereLettre = 'B';
    System.out.println("\u001B[32m*************************\u001B[0m");
    System.out.println("\u001B[32m* La première lettre de votre nom est : " + premiereLettre + " *\u001B[0m");
    System.out.println("\u001B[32m*************************\u001B[0m");

# Créer un tablea de chat puis afficher



        // Créer 3 objet cat et les afficher
        Cat cat1 = new Cat("Mimi", 2, "black");
        Cat cat2 = new Cat("Mina", 3, "white");
        Cat cat3 = new Cat("Momo", 1, "brown");

        System.out.println("Cat1: " + cat1.getName() + " " + cat1.getAge() + " " + cat1.getColor());
        System.out.println("Cat2: " + cat2.getName() + " " + cat2.getAge() + " " + cat2.getColor());
        System.out.println("Cat3: " + cat3.getName() + " " + cat3.getAge() + " " + cat3.getColor());


        // Créer une collectiond e chat et les afficher
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        for (Cat cat : cats) {
            System.out.println("Cat: " + cat.getName() + " " + cat.getAge() + " " + cat.getColor());
        }

## Generer les chats

```java
// genere 10 chats de couleurs différentes entre black, white et brown puis filtre que les white
List<Cat> cats2 = new ArrayList<>();
for (int i = 0; i < 10; i++) {
String color = i % 3 == 0 ? "black" : i % 3 == 1 ? "white" : "brown";
cats2.add(new Cat("Cat" + i, i, color));
}
// affiche les chats blancs
cats2.stream().filter(cat -> cat.getColor().equals("white")).forEach(cat -> System.out.println("Cat: " + cat.getName() + " " + cat.getAge() + " " + cat.getColor()));


# Genere pas a ma classe persistence en BDD des Cats

package com.example.demo;

// Transforme en Entity avec Jakarta Persistence
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cats")
@Data
public class Cat {
// Genere un id auto incrementé
@Id
private Long id;

    @Column(nullable = false)
    private String name;
```




# Shortcut


