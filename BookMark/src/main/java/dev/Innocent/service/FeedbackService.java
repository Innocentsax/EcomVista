package dev.Innocent.service;

import dev.Innocent.DTO.request.FeedbackRequest;
import dev.Innocent.DTO.response.FeedbackResponse;
import dev.Innocent.DTO.response.PageResponse;
import org.springframework.security.core.Authentication;

public interface FeedbackService {
    Integer save(FeedbackRequest request, Authentication connectedUser);
    PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, int page, int size, Authentication connectedUser);
}
