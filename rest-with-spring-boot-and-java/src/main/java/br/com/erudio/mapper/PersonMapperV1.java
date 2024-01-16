package br.com.erudio.mapper;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonMapperV1 {

    public PersonVO ConvertEntityToPersoVOV1(Person person){
        PersonVO vo = new PersonVO();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());

        return vo;
    }


    public Person ConvertPersonVOToEntityV1(PersonVO person) {
        Person entity = new Person();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return entity;
    }

    public List<PersonVO> convertEntityToPersonVOList(List<Person> personList){
        List<PersonVO> personVOlist = new ArrayList<>();

        for (Person person: personList) {
            PersonVO vo = new PersonVO();
            vo.setId(person.getId());
            vo.setFirstName(person.getFirstName());
            vo.setLastName(person.getLastName());
            vo.setAddress(person.getAddress());
            vo.setGender(person.getGender());

            personVOlist.add(vo);
        }
        return personVOlist;
    }


}
