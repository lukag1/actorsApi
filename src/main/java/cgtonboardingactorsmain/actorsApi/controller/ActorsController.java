package cgtonboardingactorsmain.actorsApi.controller;

import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.fileApi.FileManagerApi;
import cgtonboardingactorsmain.actorsApi.logger.LogLevel;
import cgtonboardingactorsmain.actorsApi.logger.LoggerImpl;
import cgtonboardingactorsmain.actorsApi.logger.LoggerModel;
import cgtonboardingactorsmain.actorsApi.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/actors")
public class ActorsController {
    ActorService actorService;
    LoggerImpl logger;
    FileManagerApi fileManagerApi;

    @Autowired
    public ActorsController(ActorService actorService,LoggerImpl logger,FileManagerApi fileManagerApi) {
        this.actorService = actorService;
        this.logger = logger;
        this.fileManagerApi=fileManagerApi;
    }

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAll(@RequestHeader ("X-Request-ID") int xRequestedId) {
        LoggerModel lm = logger.generateLM(xRequestedId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Get all method invoked");
        List<ActorDto> actorDtos = actorService.findAll(lm);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Get all method finished");
        return new ResponseEntity<>(actorDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ActorDto>> getById(@PathVariable int id,@RequestHeader ("X-Request-ID") int xRequestedId){
        LoggerModel lm = logger.generateLM(xRequestedId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Get by ID method invoked");
        Optional<ActorDto> actorDto = actorService.findById(id, lm);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Get by ID method finished");
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDto> add(@RequestBody @Valid CreateActorDto createActorDto,@RequestHeader ("X-Request-ID") int xRequestedId){
        LoggerModel lm = logger.generateLM(xRequestedId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Add actor method invoked");
        ActorDto actorDto = actorService.add(createActorDto, lm);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Add actor method finished");
        return new ResponseEntity<>(actorDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ActorDto> updateActor(@RequestBody @Valid CreateActorDto createActorDto, @PathVariable int id,@RequestHeader ("X-Request-ID") int xRequestedId){
        LoggerModel lm = logger.generateLM(xRequestedId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Update actor method invoked");
        ActorDto actorDto = actorService.update(createActorDto, id,lm);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Update actor method finished");
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,@RequestHeader ("X-Request-ID") int xRequestedId){
        LoggerModel lm = logger.generateLM(xRequestedId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Delete actor method invoked");
        actorService.delete(id,lm);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Delete actor method finished");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/image/{id}")
    public ResponseEntity<String> getCodeImage(@PathVariable int id, @RequestHeader("TransactionID") int transactionId) {
        LoggerModel lm = logger.generateLM(transactionId);
        logger.formatLogMessageGen(LogLevel.INFO, lm, "Call from Movies Api to get image name");

        Optional<ActorDto> actorOptional = actorService.findById(id, lm);
        if (actorOptional.isPresent()) {
            ActorDto actor = actorOptional.get();
            String code = actor.getActorImage();
            return new ResponseEntity<>(code, HttpStatus.OK);
        } else {
            logger.formatErrorLogMessageError(LogLevel.ERROR, lm, "Actor not found with ID: " + id);
            return new ResponseEntity<>("Actor not found", HttpStatus.NOT_FOUND);
        }
    }
}
