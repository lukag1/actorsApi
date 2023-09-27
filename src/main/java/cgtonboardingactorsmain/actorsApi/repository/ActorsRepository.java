package cgtonboardingactorsmain.actorsApi.repository;

import cgtonboardingactorsmain.actorsApi.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorsRepository extends JpaRepository<Actor, Integer> {

}
