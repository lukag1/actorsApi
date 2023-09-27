package cgtonboardingactorsmain.actorsApi.mapper;

import cgtonboardingactorsmain.actorsApi.controller.ActorsController;
import cgtonboardingactorsmain.actorsApi.domain.Actor;
import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.repository.ActorsRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class Mapper {

    ActorsRepository actorsRepository;

    public Mapper(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }

    public ActorDto actorToActorDto(Actor actor){
        ActorDto actorDto = new ActorDto();
        actorDto.setId(actor.getActorId());
        actorDto.setGender(actor.getGender());
        actorDto.setFullName(actor.getFullName());
        actorDto.setDateOfBirth(actor.getDateOfBirth());
//        actorDto.setActorImage(actor.getImageName());
        actorDto.setParents(actor.getParents());
        actorDto.setBiography(actor.getBiography());
        actorDto.setSpouse(actor.getSpouse());
        actorDto.setPlaceOfBirth(actor.getPlaceOfBirth());
        actorDto.setChildren(actor.getChildren());

        LocalDate now = LocalDate.now();
        int year = now.getYear() - actor.getDateOfBirth().getYear();
        actorDto.setAge(year);
        actorDto.setHref(linkTo(ActorsController.class).slash(actorDto.getId()).toString());

        File file = new File(actor.getImageName());

        try (FileInputStream fis = new FileInputStream(file)){
            byte[] fileContent = new byte[(int) file.length()];
            fis.read(fileContent);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            actorDto.setActorImage("<base64 image>,"+encodedString);
            actorDto.setImageName(actor.getImageName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        String[] name = actorDto.getActorImage().split(",");
        String dataUri = name[1];
        Random random = new Random();
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(dataUri);
            String imageName = "actorImage_" + random.nextInt(100) + ".jpg";
            actor.setImageName(imageName);
            try (FileOutputStream fos = new FileOutputStream(imageName)) {
                fos.write(decodedBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return actor;
    }
}
