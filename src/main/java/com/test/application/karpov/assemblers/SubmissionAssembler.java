package com.test.application.karpov.assemblers;

import com.test.application.karpov.controllers.user.UserController;
import com.test.application.karpov.dto.Submission;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubmissionAssembler implements RepresentationModelAssembler<Submission, EntityModel<Submission>> {
    @Override
    public EntityModel<Submission> toModel(Submission submission) {
        return EntityModel.of(submission);
    }
}
