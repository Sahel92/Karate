package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.ibm.icu.text.SimpleDateFormat;

import net.datafaker.Faker;

public class DataGenerator {

	/*
	 * In Karate we can call Java methods each method should be static for each data
	 * type we need to generate separate methods
	 */

	public static String getEmail() {
		Faker faker = new Faker();
		String email = faker.name().firstName() + faker.name().lastName() + "@tekschool.us";
		return email;
	}

	public static String getFirstName() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		return firstName;

	}

	public static String getLastName() {
		Faker faker = new Faker();
		String lastName = faker.name().lastName();
		return lastName;
	}

	public static String getTitle() {
		Faker faker = new Faker();
		return faker.name().prefix().toUpperCase();
	}

	public static String getGender() {
		Faker faker = new Faker();
		return faker.dog().gender().toUpperCase();
	}

	public static String getDOB() {
		Faker faker = new Faker();
		Date date = faker.date().birthday();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		return formatDate.format(date);
	}

	public static String getEmploymentStatus() {
		Faker faker = new Faker();
		return faker.job().position();
	}

	public static String getMaritalStatus() {
		ArrayList<String> maritalStatus = new ArrayList<String>();
		maritalStatus.add("SINGLE");
		maritalStatus.add("MARRIED");
		maritalStatus.add("DIVORCED");
		maritalStatus.add("WIDOW");
		maritalStatus.add("WIDOWER");
		Collections.shuffle(maritalStatus);
		return maritalStatus.get(0);
	}

	public static String getMake() {
		Faker faker = new Faker();
		return faker.vehicle().make();
	}

	public static String getModel() {
		Faker faker = new Faker();
		return faker.vehicle().model();
	}

	public static String getCountry() {
		Faker faker = new Faker();
		return faker.address().country();
	}

	public static String getFullName() {
		return getFirstName() + " " + getLastName().replaceAll("[^a-zA-Z]", " ");
	}

	public static String getCarYear() {
		Random random = new Random();
		Integer carYear = random.nextInt(2023 - 1990 + 1) + 1900;
		return carYear.toString();

	}

	public static String getPhoneNumber() {
		Faker faker = new Faker();
		return faker.phoneNumber().phoneNumber();

	}

	public static String getStreet() {
		Faker faker = new Faker();
		return faker.address().streetAddress();

	}

	public static String getApartment() {
		Faker faker = new Faker();
		return faker.address().buildingNumber();
	}

	public static String getCity() {
		Faker faker = new Faker();
		return faker.address().city();
	}

	public static String getState() {
		Faker faker = new Faker();
		return faker.address().state();
	}

	public static String getZipCode() {
		Faker faker = new Faker();
		return faker.address().zipCode().toString();
	}

	public static String getCardNumber() {
		Faker faker = new Faker();
		return faker.numerify("4213############");
	}

	public static String getCCV() {
		Faker faker = new Faker();
		return faker.numerify("###");
	}

	public static Integer getExpMonth() {
		Random random = new Random();
		Integer month = random.nextInt(12 - 1) + 1;
		return month;
	}

	public static Integer getExpYear() {
		Random random = new Random();
		Integer expYear = random.nextInt(2030 - 2024 + 1) + 2024;
		return expYear;
	}

	public static String getNameOnCard() {
		return getFullName();
	}

	public static String getDropOff() {

		List<String> reason = new LinkedList<>();
		reason.add("fedex");
		reason.add("usps");
		reason.add("ups");
		Collections.shuffle(reason);
		return reason.get(0);
	}

	public static String getDropReason() {

		List<String> reason = new LinkedList<>();
		reason.add("wrong");
		reason.add("damage");
		Collections.shuffle(reason);
		return reason.get(0);
	}

}
