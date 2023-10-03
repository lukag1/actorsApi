package cgtonboardingactorsmain.actorsApi.mapper;

import cgtonboardingactorsmain.actorsApi.FilesHandling.FileManager;
import cgtonboardingactorsmain.actorsApi.controller.ActorsController;
import cgtonboardingactorsmain.actorsApi.domain.Actor;
import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.repository.ActorsRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class Mapper {

    ActorsRepository actorsRepository;
    FileManager fileManager;

    public Mapper(ActorsRepository actorsRepository,FileManager fileManager) {
        this.actorsRepository = actorsRepository;
        this.fileManager = fileManager;
    }

    public ActorDto actorToActorDto(Actor actor){
        ActorDto actorDto = new ActorDto();
        actorDto.setId(actor.getActorId());
        actorDto.setGender(actor.getGender());
        actorDto.setFullName(actor.getFullName());
        actorDto.setDateOfBirth(actor.getDateOfBirth());
        actorDto.setParents(actor.getParents());
        actorDto.setBiography(actor.getBiography());
        actorDto.setSpouse(actor.getSpouse());
        actorDto.setPlaceOfBirth(actor.getPlaceOfBirth());
        actorDto.setChildren(actor.getChildren());

        LocalDate now = LocalDate.now();
        int year = now.getYear() - actor.getDateOfBirth().getYear();
        actorDto.setAge(year);
        actorDto.setHref(linkTo(ActorsController.class).slash(actorDto.getId()).toString());
        actorDto.setActorImage(fileManager.saveCode(actor));
        actorDto.setImageName(actor.getImageName());

        return actorDto;
    }

    public Actor createActorDtoToActor(CreateActorDto actorDto){
        Actor actor = new Actor();
        actor.setBiography(actorDto.getBiography());
        actor.setChildren(actorDto.getChildren());
        actor.setGender(actorDto.getGender());
        actor.setParents(actorDto.getParents());
        actor.setPlaceOfBirth(actorDto.getPlaceOfBirth());
        actor.setFullName(actorDto.getFullName());
        actor.setDateOfBirth(actorDto.getDateOfBirth());
        actor.setSpouse(actorDto.getSpouse());

        actor.setImageName(fileManager.saveImageName(actorDto));

        return actor;
    }
}
