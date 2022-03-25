package com.test.application.karpov.controllers.admin;

import com.test.application.karpov.assemblers.QuestionAssembler;
import com.test.application.karpov.dto.Question;
import com.test.application.karpov.services.question.QuestionService;
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
@RequestMapping("/admin/quizzes/{quizId}/questions")
public class QuestionApiController {

    private final QuestionService questionService;
    private final QuestionAssembler questionAssembler;
    private final QuizService quizService;

    @Autowired
    public QuestionApiController(QuestionService questionService, QuestionAssembler questionAssembler, QuizService quizService) {
        this.questionService = questionService;
        this.questionAssembler = questionAssembler;
        this.quizService = quizService;
    }

    @GetMapping(produces = "application/json")
    public CollectionModel<EntityModel<Question>> all(@PathVariable Long quizId) {
        List<EntityModel<Question>> questions = quizService.findQuizById(quizId).getQuestions().stream()
                .map(questionAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions,
                linkTo(methodOn(QuestionApiController.class)
                        .all(quizId))
                        .withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public EntityModel<Question> one(@PathVariable Long id) {
        Question question = questionService.findQuestionById(id);

        return questionAssembler.toModel(question);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Question question, @PathVariable Long quizId) {
        EntityModel<Question> entityModel = questionAssembler.toModel(questionService.save(question));
        quizService.addQuestion(question, quizId);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> replace(@PathVariable Long id, @RequestBody Question question) {
        EntityModel<Question> entityModel = questionAssembler.toModel(questionService.update(question, id));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        questionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
