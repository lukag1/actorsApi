package cgtonboardingactorsmain.actorsApi.service;

import cgtonboardingactorsmain.actorsApi.domain.Actor;
import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.exception.ResourceNotFoundException;
import cgtonboardingactorsmain.actorsApi.fileApi.FileManagerApi;
import cgtonboardingactorsmain.actorsApi.logger.LogLevel;
import cgtonboardingactorsmain.actorsApi.logger.LoggerImpl;
import cgtonboardingactorsmain.actorsApi.logger.LoggerModel;
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
    FileManagerApi fileManagerApi;
    LoggerImpl logger;

    @Autowired
    public ActorService(ActorsRepository actorsRepository, Mapper mapper,FileManagerApi fileManagerApi,LoggerImpl logger) {
        this.mapper = mapper;
        this.actorsRepository = actorsRepository;
        this.fileManagerApi = fileManagerApi;
        this.logger = logger;
    }

    public List<ActorDto> findAll(LoggerModel lm){
        logger.formatLogMessageGen(LogLevel.INFO,lm,"Started findAll in Service");
        List<Actor> actors = actorsRepository.findAll();
        List<ActorDto> actorDtos = actors.stream()
                .map(movie -> mapper.actorToActorDto(movie, lm))
                .toList();
        return actorDtos;
    }

    public Optional<ActorDto> findById(int id,LoggerModel lm){
        logger.formatLogMessageGen(LogLevel.INFO,lm,"Started findAll in Service");
        Actor actor =  actorsRepository.findById(id).get();
        if(actor == null){
            throw new ResourceNotFoundException("Actor dont exist");
        }
        return Optional.ofNullable(mapper.actorToActorDto(actor,lm));
    }

    public ActorDto add(CreateActorDto createActorDto, LoggerModel lm){
        logger.formatLogMessageGen(LogLevel.INFO,lm,"Started adding actor in Service");
        Actor actor = mapper.createActorDtoToActor(createActorDto, lm);
        actorsRepository.save(actor);
        return mapper.actorToActorDto(actor, lm);

    }

    public void delete(int id, LoggerModel lm){
        logger.formatLogMessageGen(LogLevel.INFO,lm,"Started deleting in Service");
        Actor actor = actorsRepository.findById(id).get();
        System.out.println(actor.getImageName());
        fileManagerApi.deleteImage(actor.getImageName(), lm);
        actorsRepository.deleteById(id);
    }

    public ActorDto update(CreateActorDto createActorDto, int id, LoggerModel lm) {
        logger.formatLogMessageGen(LogLevel.INFO,lm,"Started updating in Service");
        Actor actorOld = actorsRepository.findById(id).get();
        ActorDto actorNew = null;
        if(actorOld != null){
            actorOld.setGender(createActorDto.getGender());
            actorOld.setSpouse(createActorDto.getSpouse());
            actorOld.setParents(createActorDto.getParents());
            actorOld.setChildren(createActorDto.getChildren());
            actorOld.setBiography(createActorDto.getBiography());
            actorOld.setDateOfBirth(createActorDto.getDateOfBirth());
            actorOld.setPlaceOfBirth(createActorDto.getPlaceOfBirth());
            actorOld.setFullName(createActorDto.getFullName());
            String code = createActorDto.getActorImage();
            String imgName = actorsRepository.findById(id).get().getImageName();
            actorOld.setImageName(fileManagerApi.updateImage(code, imgName,lm));

            actorsRepository.save(actorOld);
            logger.formatLogMessageGen(LogLevel.INFO,lm,"Saved actor update in Service");
            actorNew = mapper.actorToActorDto(actorOld,lm);
        }else {
            logger.formatErrorLogMessageError(LogLevel.ERROR, lm, "Error to get actor");
            throw new ResourceNotFoundException("Cant find actor");
        }
        return actorNew;
    }

}
