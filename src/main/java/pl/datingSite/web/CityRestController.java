package pl.datingSite.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.datingSite.model.City;
import pl.datingSite.services.CityService;

import javax.persistence.NoResultException;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityRestController {

    final static Logger logger = Logger.getLogger(CityRestController.class);

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity getByName(@RequestParam("name") String name) {
        try {
            List<City> list = cityService.getByName(name);
            return new ResponseEntity(list, HttpStatus.OK);
        } catch (NoResultException e) {
            logger.warn(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/getCities", method = RequestMethod.GET)
    public ResponseEntity getCities() {
        List<City> cities = cityService.getCities();

        if(cities.isEmpty()) {
            logger.warn("Cities were not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity(cities, HttpStatus.OK);
    }

}
