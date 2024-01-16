package br.com.erudio.services;

import br.com.erudio.controllers.data.vo.v2.PersonVOV2;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapperV1;
import br.com.erudio.mapper.custom.PersonMapperV2;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonMapperV1 mapperV1;
    @Autowired
    PersonMapperV2 mapperV2;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        return mapperV1.convertEntityToPersonVOList(repository.findAll());
    }

    public PersonVO findById(Long id){

        logger.info("Fading a person");

        var entiy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return mapperV1.ConvertEntityToPersoVOV1(entiy);
    }

    public PersonVO create(PersonVO data){

        logger.info("Creating one person");

        var entity = mapperV1.ConvertPersonVOToEntityV1(data);
        var vo = mapperV1.ConvertEntityToPersoVOV1(repository.save(entity));

        return vo;
    }

    public PersonVOV2 createv2(PersonVOV2 data){

        logger.info("Creating one person with V2");

        var entity = mapperV2.convertVOToEntity(data);
        var vo = mapperV2.convertEntityToVO(repository.save(entity));

        return vo;
    }

    public PersonVO update(PersonVO person){

        logger.info("Creating one person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

         var vo = mapperV1.ConvertEntityToPersoVOV1(repository.save(entity));
         return vo;
    }

    public void delete(Long id){

        logger.info("Deleting person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }

}
