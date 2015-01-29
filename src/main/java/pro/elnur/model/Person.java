package pro.elnur.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public abstract class Person {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id = UUID.randomUUID();
}
