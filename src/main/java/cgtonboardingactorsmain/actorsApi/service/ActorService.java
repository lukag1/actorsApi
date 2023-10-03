package cgtonboardingactorsmain.actorsApi.service;

import cgtonboardingactorsmain.actorsApi.FilesHandling.FileManager;
import cgtonboardingactorsmain.actorsApi.domain.Actor;
import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.mapper.Mapper;
import cgtonboardingactorsmain.actorsApi.repository.ActorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    ActorsRepository actorsRepository;
    Mapper mapper;

    FileManager fileManager;

    @Autowired
    public ActorService(ActorsRepository actorsRepository, Mapper mapper,FileManager fileManager) {
        this.mapper = mapper;
        this.actorsRepository = actorsRepository;
        this.fileManager = fileManager;
    }

    public List<ActorDto> findAll(){
        return actorsRepository.findAll().stream().map(mapper::actorToActorDto).toList();
    }

    public Optional<ActorDto> findById(int id){
        return actorsRepository.findById(id).map(mapper::actorToActorDto);
    }

    public ActorDto add(CreateActorDto createActorDto){
        Actor actor = mapper.createActorDtoToActor(createActorDto);
        actorsRepository.save(actor);
        return mapper.actorToActorDto(actor);

    }

    public void delete(int id){
        actorsRepository.deleteById(id);
    }

    public ActorDto update(CreateActorDto createActorDto, int id) {
        Actor actorOld = actorsRepository.findById(id).get();
        actorOld.setGender(createActorDto.getGender());
        actorOld.setSpouse(createActorDto.getSpouse());
        actorOld.setParents(createActorDto.getParents());
        actorOld.setChildren(createActorDto.getChildren());
        actorOld.setBiography(createActorDto.getBiography());
        actorOld.setDateOfBirth(createActorDto.getDateOfBirth());
        actorOld.setPlaceOfBirth(createActorDto.getPlaceOfBirth());
        actorOld.setFullName(createActorDto.getFullName());
        actorOld.setImageName(fileManager.updateImage(createActorDto, actorsRepository.findById(id)));

        actorsRepository.save(actorOld);
        return mapper.actorToActorDto(actorOld);

    }

}
