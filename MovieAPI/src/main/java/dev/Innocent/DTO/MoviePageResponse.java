package dev.Innocent.DTO;

import java.util.List;

public record MoviePageResponse(
        List<MovieDTO> movies,
        Integer pageNumber,
        Integer pageSize,
        int totalPages,
        Integer currentPage,
        boolean isLast,
        boolean isFirst,
        int totalElements
) {
}
