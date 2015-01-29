package pro.elnur.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id = UUID.randomUUID();
}
