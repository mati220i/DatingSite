package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.City;
import pl.datingSite.tools.DatabaseGenerator;

@Service
public class DatabaseGeneratorService {

    @Autowired
    private DatabaseGenerator databaseGenerator;

    public void generate(int quantity) {
        databaseGenerator.generate(quantity);
    }

    public void generate(int quantity, boolean nearby, City city) {
        databaseGenerator.generate(quantity, nearby, city);
    }

    public void generatePrepare(City nearbyCity) {
        databaseGenerator.generatePrepareData(nearbyCity);
    }

}
