package jrsperry.neo4jexamples;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
@NoArgsConstructor
@Slf4j
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, Person knows) {
        this.name = name;
        this.age = age;
        this.knowsPeople.add(knows);
    }

    @Relationship(type = "KNOWS")
    private Set<Person> knowsPeople = new HashSet<>();

    @Relationship(type = "DISLIKES")
    private Set<Person> dislikes = new HashSet<>();

    @Relationship(type = "LOVES")
    private Set<Person> loves = new HashSet<>();

    @Relationship(type = "TOLERATES")
    private Set<Person> tolerates = new HashSet<>();

    public void addKnow(Person person){
        this.knowsPeople.add(person);
    }

    public void addDislike(Person person){
        this.dislikes.add(person);
    }

    public void addLove(Person person){
        this.loves.add(person);
    }

    public void addTolerates(Person person){
        this.tolerates.add(person);
    }


}
