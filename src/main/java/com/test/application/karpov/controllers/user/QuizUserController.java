package com.test.application.karpov.controllers.user;


import com.test.application.karpov.assemblers.QuizAssembler;
import com.test.application.karpov.assemblers.UserAssembler;
import com.test.application.karpov.controllers.admin.QuizApiController;
import com.test.application.karpov.dto.Quiz;
import com.test.application.karpov.dto.User;
import com.test.application.karpov.services.quiz.QuizService;
import com.test.application.karpov.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/users/{user_id}/quizzes")
public class QuizUserController {

    private final QuizService quizService;
    private final UserService userService;
    private final QuizAssembler quizAssembler;
    private final UserAssembler userAssembler;

    @Autowired
    public QuizUserController(QuizService quizService, UserService userService, QuizAssembler quizAssembler, UserAssembler userAssembler) {
        this.quizService = quizService;
        this.userService = userService;
        this.quizAssembler = quizAssembler;
        this.userAssembler = userAssembler;
    }

    @GetMapping(produces = "application/json")
    public CollectionModel<EntityModel<Quiz>> findAllActive(@PathVariable String user_id) {
        List<EntityModel<Quiz>> questions = quizService.findAllActive().stream()
                .map(quizAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(questions,
                linkTo(methodOn(QuizApiController.class).all()).withSelfRel());
    }

    @PostMapping(value = "/{quizId}{isAnonymous}", produces = "application/json")
    public EntityModel<User> addQuiz(@PathVariable Long quizId, @PathVariable Boolean isAnonymous, @PathVariable Long user_id) {
        if (isAnonymous) {
            User user = userService.getAnonymousUser();
            userService.addQuiz(user.getId(), quizService.findQuizById(quizId));

            return userAssembler.toModel(userService.update(user, user_id));
        }

        User user = userService.findUserById(user_id);
        userService.addQuiz(user.getId(), quizService.findQuizById(quizId));

        return userAssembler.toModel(userService.update(user, user_id));
    }
}
