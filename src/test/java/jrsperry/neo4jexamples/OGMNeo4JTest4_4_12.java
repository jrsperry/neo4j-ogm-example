package jrsperry.neo4jexamples;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
class OGMNeo4JTest4_4_12 extends ContainerBaseTest4_4_12 {
	// this tests against a neo4j 4 container

	@Autowired
	SessionFactory sessionFactory;



	@Test
	void contextLoads() {
	}

	@Test
	void loadTest() {
		// load some people in, warm up
		IntStream.range(0, 100).forEach(i -> {
			Person person = complexPerson("johnny", i);
			sessionFactory.openSession().save(person);
		});

		Instant before = Instant.now();
		Integer iterations = 1000;
		IntStream.range(0, iterations).forEach(i -> {
			Person person = complexPerson("johnny", i);
			sessionFactory.openSession().save(person);
		});
		Instant after = Instant.now();
		Long totalMillisElapsed = Duration.between(before, after).toMillis();
		Long totalSecondsElapsed = Duration.between(before, after).toSeconds();
		log.info("time to load all {} complex people: {}s", iterations, totalSecondsElapsed);
		log.info("average time per complex person:  {}ms", totalMillisElapsed.doubleValue() / iterations);
	}

	private Person basePerson(String baseName, Integer i){
		Person basePerson = new Person(baseName, i);
		Person knows = new Person("knows", i);
		Person dislike = new Person("dislike", i);
		Person love = new Person("love", i);
		Person tolerate = new Person("tolerate", i);
		basePerson.addKnow(knows);
		basePerson.addDislike(dislike);
		basePerson.addLove(love);
		basePerson.addTolerates(tolerate);
		return basePerson;
	}

	private Person complexPerson(String complexName, Integer i){
		// builds a "complex" person, 25 total person objects
		Person knows = basePerson("knows", i);
		Person dislike = basePerson("dislike", i);
		Person love = basePerson("love", i);
		Person tolerate = basePerson("tolerate", i);
		Person complexPerson = basePerson("johnny", i);
		complexPerson.addKnow(knows);
		complexPerson.addDislike(dislike);
		complexPerson.addLove(love);
		complexPerson.addTolerates(tolerate);
		return complexPerson;
	}

}
