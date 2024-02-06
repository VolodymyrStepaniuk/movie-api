package com.stepaniuk.movieapi.movie;

import com.stepaniuk.movieapi.country.Country;
import com.stepaniuk.movieapi.director.Director;
import com.stepaniuk.movieapi.genre.Genre;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
@Entity(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "required_age", nullable = false)
    private Integer requiredAge;
    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToMany
    @JoinTable(
        name = "movie_producing_countries",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> producingCountries;

    @ManyToMany
    @JoinTable(
        name = "movie_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(
        name = "movie_makers",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "director_id")
    )
    private Set<Director> directors;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "duration", columnDefinition = "interval",nullable = false)
    private Duration duration;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "description",columnDefinition = "text", nullable = false)
    private String description;
    @Column(name = "trailer_url", columnDefinition = "text", nullable = true)
    private String trailerUrl;
    @Column(name = "video_url", columnDefinition = "text", nullable = true)
    private String videoUrl;
    @Type(ListArrayType.class)
    @Column(name = "image_urls", columnDefinition = "text[]", nullable = false)
    private List<String> imageUrls;

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
        Movie movie = (Movie) o;
        return getId() != null && Objects.equals(getId(), movie.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer()
            .getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "id = " + id + ", " +
            "title = " + title + ", " +
            "year = " + year + ", " +
            "duration = " + duration + ", " +
            "language = " + language + ", " +
            "description = " + description + ", " +
            "imageUrls = " + imageUrls + ")";
    }
}
