package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.assemblers.QuizAssembler;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.services.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/admin/quizzes")
public class QuizApiController {

    private final QuizService quizService;
    private final QuizAssembler quizAssembler;

    @Autowired
    public QuizApiController(QuizService quizService, QuizAssembler quizAssembler) {
        this.quizService = quizService;
        this.quizAssembler = quizAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Quiz>> all(){
        List<EntityModel<Quiz>> questions = quizService.findAll().stream()
                .map(quizAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions,
                linkTo(methodOn(QuizApiController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/{id}")
    public EntityModel<Quiz> one(@PathVariable Long id) {
            Quiz quiz = quizService.findQuizById(id);

            return quizAssembler.toModel(quiz);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Quiz quiz){
        EntityModel<Quiz> entityModel = quizAssembler.toModel(quizService.save(quiz));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> replace(@PathVariable Long id, @RequestBody Quiz quiz){
        EntityModel<Quiz> entityModel = quizAssembler.toModel(quizService.update(quiz, id));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id){
        quizService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
