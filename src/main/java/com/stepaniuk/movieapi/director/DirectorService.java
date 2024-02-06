package com.stepaniuk.movieapi.director;

import com.stepaniuk.movieapi.director.payload.request.CreateDirectorRequest;
import com.stepaniuk.movieapi.director.payload.response.DirectorResponse;
import com.stepaniuk.movieapi.exceptions.country.CountryNotFoundException;
import com.stepaniuk.movieapi.exceptions.director.DirectorNotFoundException;
import com.stepaniuk.movieapi.country.CountryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectorService {

  private final DirectorRepository directorRepository;
  private final DirectorMapper directorMapper;
  private final CountryRepository countryRepository;

  public DirectorResponse getDirectorById(Long id) {
    return directorMapper.toResponse(
        directorRepository.findById(id).orElseThrow(() -> new DirectorNotFoundException(id))
    );
  }

  public DirectorResponse createDirector(CreateDirectorRequest directorRequest) {
    Director director = new Director();
    director.setDescription(directorRequest.getDescription());
    director.setFirstName(directorRequest.getFirstName());
    director.setLastName(directorRequest.getLastName());
    director.setBirthDate(directorRequest.getBirthDate());
    director.setBirthCountry(countryRepository.findById(directorRequest.getCountryId())
        .orElseThrow(() -> new CountryNotFoundException(directorRequest.getCountryId())));
    director.setPhotoUrls(directorRequest.getPhotoUrls());

    return directorMapper.toResponse(directorRepository.save(director));
  }

  public Page<DirectorResponse> getAllDirectors(Pageable pageable,
      @Nullable List<Long> directorIds) {
    Specification<Director> specification = Specification.where(null);
    if (directorIds != null && !directorIds.isEmpty()) {
      specification = specification.and(
          (root, query, criteriaBuilder) -> root.get("id").in(directorIds));
    }

    return directorRepository.findAll(specification, pageable)
        .map(directorMapper::toResponse);
  }
}
