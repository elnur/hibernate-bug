package pro.elnur.model;

import java.io.Serializable;
import java.util.UUID;
import org.hibernate.annotations.Type;

public class MembershipId implements Serializable {
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    public UUID employee;
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    public UUID group;

    public UUID getEmployee() {
        return employee;
    }

    public void setEmployee(UUID employee) {
        this.employee = employee;
    }

    public UUID getGroup() {
        return group;
    }

    public void setGroup(UUID group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MembershipId that = (MembershipId) o;

        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
