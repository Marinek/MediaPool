package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import models.Car;
import play.data.validation.Valid;
import play.libs.MimeTypes;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void createCar(int platenumber, String location, String color, String type, String special) {
		Car car = new Car();
		car.platenumber = platenumber;
		car.location = location;
		car.color = color;
		car.type = type;
		car.special = special;
		car.save();
	}

	public static void car(@Valid Car car) {
		car.save();
		render(car);
	}

	public static void save(@Valid Car car, File image) throws IOException {
		if (image != null) {
			car.image = new byte[(int) image.length()];
			FileInputStream stream = new FileInputStream(image);
			stream.read(car.image);
			stream.close();
			car.imageMimeType = MimeTypes.getContentType(image.getName());
		}
		car.save();
		render(car);
	}

	public static void image(Long carId) {
		Car car = Car.findById(carId);
		renderBinary(new ByteArrayInputStream(car.image), "image", car.imageMimeType, false);
	}

}