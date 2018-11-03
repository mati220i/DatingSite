package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.City;
import pl.datingSite.repository.CityRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getByName(String name) {
        List<City> cities = cityRepository.getByName(name);
        if(cities != null)
            return cities;
        else
            throw new NoResultException("Cannot find cities like: " + name);
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

}
