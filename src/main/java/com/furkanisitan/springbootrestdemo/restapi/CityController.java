package com.furkanisitan.springbootrestdemo.restapi;

import com.furkanisitan.springbootrestdemo.business._abstract.ICityService;
import com.furkanisitan.springbootrestdemo.entities.concrete.City;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final ICityService cityService;

    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable int id) {
        return cityService.getById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody City city) {
        cityService.add(city);
    }

    @PostMapping("/update")
    public void update(@RequestBody City city) {
        cityService.update(city);
    }

    @PostMapping("/delete-by-id")
    public void deleteById(@RequestParam int id) {
        cityService.deleteById(id);
    }
}
