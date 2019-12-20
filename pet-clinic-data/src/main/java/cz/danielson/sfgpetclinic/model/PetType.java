package cz.danielson.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pet_type")
@Setter
@Getter
@NoArgsConstructor
public class PetType extends NamedEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id, name);
    }
}
