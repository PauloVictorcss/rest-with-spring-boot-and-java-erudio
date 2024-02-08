package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.controllers.data.vo.v2.PersonVOV2;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.PersonMapperV1;
import br.com.erudio.mapper.custom.PersonMapperV2;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; //Biblioteca para adiciotar o hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn; //esse também
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        PersonMapperV1 mapperV1 = new PersonMapperV1();

        logger.info("Finding all people!");
        List<PersonVO> voList = new ArrayList<>();

        var persons = mapperV1.convertEntityToPersonVOList(repository.findAll());
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO findById(Long id){

        PersonMapperV1 mapperV1 = new PersonMapperV1();

        logger.info("Fading a person");

        var entiy = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var vo = mapperV1.ConvertEntityToPersoVOV1(entiy);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PersonVO create(PersonVO personvo){

        if(personvo == null) throw new RequiredObjectIsNullException();
        PersonMapperV1 mapperV1 = new PersonMapperV1();

        logger.info("Creating one person");

        var entity = mapperV1.ConvertPersonVOToEntityV1(personvo);
        var vo = mapperV1.ConvertEntityToPersoVOV1(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVOV2 createv2(PersonVOV2 data){

        logger.info("Creating one person with V2");

        var entity = mapperV2.convertVOToEntity(data);
        var vo = mapperV2.convertEntityToVO(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person){
        if(person == null) throw new RequiredObjectIsNullException();
        PersonMapperV1 mapperV1 = new PersonMapperV1();

        logger.info("Creating one person");

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

         var vo = mapperV1.ConvertEntityToPersoVOV1(repository.save(entity));
         vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
         return vo;
    }

    public void delete(Long id){

        logger.info("Deleting person");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }

}
