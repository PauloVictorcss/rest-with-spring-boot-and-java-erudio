package br.com.erudio.mapper.custom;

import br.com.erudio.controllers.data.vo.v2.PersonVOV2;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonMapperV2 {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setKey(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());
        return vo;
    }

    public List<PersonVOV2> convertEntityToPersonVOV2List(List<Person> personList){
        List<PersonVOV2> personVOlist = new ArrayList<>();

        for (Person person: personList) {
            PersonVOV2 vo = new PersonVOV2();
            vo.setKey(person.getId());
            vo.setFirstName(person.getFirstName());
            vo.setLastName(person.getLastName());
            vo.setAddress(person.getAddress());
            vo.setGender(person.getGender());
            vo.setBirthDay(new Date());

            personVOlist.add(vo);
        }
        return personVOlist;
    }

    public Person convertVOToEntity(PersonVOV2 person){
        Person entity = new Person();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }

}
