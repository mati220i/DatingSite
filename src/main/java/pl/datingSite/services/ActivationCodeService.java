package pl.datingSite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.datingSite.model.ActivationCode;
import pl.datingSite.repository.ActivationCodeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
public class ActivationCodeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Transactional
    public void addCode(ActivationCode activationCode) {
        entityManager.persist(activationCode);
    }

    @Transactional
    public void deleteCode(ActivationCode activationCode) {
        activationCodeRepository.deleteByEmail(activationCode.getEmail());
    }

    public boolean checkCode(ActivationCode activationCode) {
        List<ActivationCode> list = activationCodeRepository.findAll();
        Iterator<ActivationCode> iterator = list.iterator();

        while (iterator.hasNext()) {
            if(iterator.next().equals(activationCode))
                return true;
        }
        return false;
    }

}
