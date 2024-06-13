package dev.Innocent.service;

import dev.Innocent.DTO.request.BookRequest;
import dev.Innocent.DTO.response.BookResponse;
import dev.Innocent.DTO.response.BorrowedBookResponse;
import dev.Innocent.DTO.response.PageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {
    Integer save(BookRequest request, Authentication connectedUser);
    BookResponse findById(Integer bookId);
    PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser);
    PageResponse<BookResponse> findAllBooksByOwner(int page, int size, Authentication connectedUser);
    PageResponse<BorrowedBookResponse> findAllBorrowedBooks(int page, int size, Authentication connectedUser);
    PageResponse<BorrowedBookResponse> findAllReturnedBooks(int page, int size, Authentication connectedUser);
    Integer updateShareableStatus(Integer bookId, Authentication connectedUser);
    Integer updateArchivedStatus(Integer bookId, Authentication connectedUser);
    Integer borrowBook(Integer bookId, Authentication connectedUser);
    Integer returnBorrowedBook(Integer bookId, Authentication connectedUser);
    Integer approveReturnBorrowedBook(Integer bookId, Authentication connectedUser);
    void uploadBookCoverPicture(MultipartFile file, Authentication connectedUser, Integer bookId);
}
