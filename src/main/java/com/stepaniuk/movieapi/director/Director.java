package com.stepaniuk.movieapi.director;

import com.stepaniuk.movieapi.country.Country;
import com.stepaniuk.movieapi.movie.Movie;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "directors")
public class Director {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @ManyToOne
  @JoinColumn(name = "country_id", referencedColumnName = "id")
  private Country birthCountry;

  @Column(name = "description",columnDefinition = "text", nullable = false)
  private String description;

  @ManyToMany(mappedBy = "directors")
  private List<Movie> movies;

  @Type(ListArrayType.class)
  @Column(name = "photo_urls", columnDefinition = "text[]", nullable = false)
  private List<String> photoUrls;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy
        ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
        : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    Director director = (Director) o;
    return getId() != null && Objects.equals(getId(), director.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "(" +
        "id = " + id + ", " +
        "firstName = " + firstName + ", " +
        "lastName = " + lastName + ", " +
        "birthDate = " + birthDate + ", " +
        "birthCountry = " + birthCountry + ", " +
        "description = " + description + ", " +
        "photoUrls = " + photoUrls + ")";
  }
}
