package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("cars")
    public String countCars(@RequestParam(name = "count", required = false) Integer count, Model model) {
        List<Car> cars;
        if (count == null || count > 5) {
            cars = carService.getCars();
        } else {
            cars = carService.getCountOfCars(count);
        }
        model.addAttribute("cars", cars);
        return "cars";
    }
}
