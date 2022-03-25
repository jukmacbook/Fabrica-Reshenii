package com.test.application.karpov.controllers.user;

import com.test.application.karpov.assemblers.QuizAssembler;
import com.test.application.karpov.assemblers.SubmissionAssembler;
import com.test.application.karpov.controllers.admin.QuizApiController;
import com.test.application.karpov.dto.Answer;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.Submission;
import com.test.application.karpov.services.quiz.QuizService;
import com.test.application.karpov.services.submission.SubmissionService;
import com.test.application.karpov.services.user.UserService;
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
@RequestMapping("/users/{userId}/quizzes")
public class UserController {

    private final UserService userService;
    private final SubmissionAssembler submissionAssembler;
    private final QuizService quizService;
    private final SubmissionService submissionService;
    private final QuizAssembler quizAssembler;

    @Autowired
    public UserController(UserService userService, SubmissionAssembler submissionAssembler, QuizService quizService, SubmissionService submissionService, QuizAssembler quizAssembler) {
        this.userService = userService;
        this.submissionAssembler = submissionAssembler;
        this.quizService = quizService;
        this.submissionService = submissionService;
        this.quizAssembler = quizAssembler;
    }


    @GetMapping(produces = "application/json")
    public CollectionModel<EntityModel<Quiz>> findAllActive() {
        List<EntityModel<Quiz>> quizzes = quizService.findAllActive().stream()
                .map(EntityModel::of)
                .collect(Collectors.toList());

        return CollectionModel.of(quizzes,
                linkTo(methodOn(QuizApiController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/quiz/{id}", produces = "application/json")
    public EntityModel<Quiz> oneQuiz(@PathVariable Long id) {

        return quizAssembler.toModel(quizService.findQuizById(id));
    }

    @GetMapping(value = "/quizzesByUserId")
    public CollectionModel<EntityModel<Submission>> allSubmissionsByUserID(@PathVariable Long userId) {
        List<EntityModel<Submission>> questions = userService.findUserById(userId).getSubmissions().stream()
                .map(submissionAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions);
    }

    @PostMapping(value = "/{quizId}", produces = "application/json")
    public ResponseEntity<?> takeQuiz(@PathVariable Long quizId, @RequestBody List<Answer> answers, @PathVariable Long userId) {
        Submission submission = new Submission();
        submission.setUser(userService.findUserById(userId));
        submission.setQuiz(quizService.findQuizById(quizId));
        submission.setAnswers(answers);
        submissionService.save(submission);
        EntityModel<Submission> entityModel = submissionAssembler.toModel(submission);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
