package cgtonboardingactorsmain.actorsApi.controller;

import cgtonboardingactorsmain.actorsApi.dto.ActorDto;
import cgtonboardingactorsmain.actorsApi.dto.CreateActorDto;
import cgtonboardingactorsmain.actorsApi.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/actors")
public class ActorsController {

    ActorService actorService;

    @Autowired
    public ActorsController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAll() {
        return new ResponseEntity<>(actorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ActorDto>> getById(@PathVariable int id){
        return new ResponseEntity<>(actorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDto> add(@RequestBody @Validated CreateActorDto createActorDto){
        return new ResponseEntity<>(actorService.add(createActorDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ActorDto> updateActor(@RequestBody @Valid CreateActorDto createActorDto, @PathVariable int id){
        return new ResponseEntity<>(actorService.update(createActorDto, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        actorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/image/{id}")
    public ResponseEntity<String> getCodeImage(@PathVariable int id){
        ActorDto actor = actorService.findById(id).get();
        String code = actor.getActorImage();
        return new ResponseEntity<>(code, HttpStatus.OK);
    }
}
