package pro.elnur.repository;

import org.springframework.data.repository.CrudRepository;
import pro.elnur.model.Membership;
import pro.elnur.model.MembershipId;

public interface MembershipRepository extends CrudRepository<Membership, MembershipId> {
}
