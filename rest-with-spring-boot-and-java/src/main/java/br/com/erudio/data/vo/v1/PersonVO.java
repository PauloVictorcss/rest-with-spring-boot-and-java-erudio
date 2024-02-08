package br.com.erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@EqualsAndHashCode(of = "id")
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"}) //serve para definir a ordem em// que os atributos vão ser serializados

//RepresentationModel<> is needed to implement HATEOAS
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    @JsonProperty("id")
    @Mapping("id") //Annotation from dozermapper to link key as an id to convertera
    private Long key;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
