package pro.elnur.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(MembershipId.class)
public class Membership {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
