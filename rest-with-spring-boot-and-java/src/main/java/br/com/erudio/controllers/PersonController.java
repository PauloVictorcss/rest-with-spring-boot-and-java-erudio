package br.com.erudio.controllers;

import br.com.erudio.controllers.data.vo.v2.PersonVOV2;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/person/v1")
    public class PersonController {

        @Autowired //Com essa anotation o spring boot cuida da instanciação = new PersonServices()
        public PersonServices services;

        @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        public List<PersonVO> findAll(){

            return services.findAll();
        }

        @GetMapping(value = "/{id}",
        produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        public PersonVO findById(@PathVariable(value = "id") Long id){

            return services.findById(id);
        }

        @PostMapping(
                consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        public PersonVO create(@RequestBody PersonVO data){

            return services.create(data);
        }

        @PostMapping(value = "/v2",
                consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        public PersonVOV2 create(@RequestBody PersonVOV2 data){

            return services.createv2(data);
        }

        @PutMapping(
                consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
        public PersonVO update(@RequestBody PersonVO personvo){
            return services.update(personvo);
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity delete(@PathVariable(value = "id") Long id){
            services.delete(id);

            return ResponseEntity.noContent().build();
        }

    }