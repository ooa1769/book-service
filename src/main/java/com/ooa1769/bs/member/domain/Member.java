package com.ooa1769.bs.member.domain;

import com.ooa1769.bs.book.domain.SearchHistory;
import com.ooa1769.bs.support.domain.AbstractEntity;
import com.ooa1769.bs.support.domain.BooleanToYNConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "member")
@Convert(converter = BooleanToYNConverter.class, attributeName = "enabled")
public class Member extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    @Getter
    private String email;

    @Getter
    private String name;

    @Getter
    private String password;

    @Getter
    private boolean enabled;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(
            name = "search_history",
            joinColumns = @JoinColumn(name = "member_id")
    )
    @org.hibernate.annotations.OrderBy(clause = "searchDate desc")
    private Set<SearchHistory> searchHistories;

    protected Member() {}

    public Member(String email, String name, String password, boolean enabled) {
        this(0L, email, name, password, enabled);
    }

    public Member(Long id, String email, String name, String password, boolean enabled) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    public void addSearchHistory(SearchHistory searchHistory) {
        if (searchHistories.contains(searchHistory)) {
            searchHistories.remove(searchHistory);
        }
        searchHistories.add(searchHistory);
    }

    public void removeHistory(SearchHistory searchHistory) {
        searchHistories.remove(searchHistory);
    }

    public SearchHistory findByKeyword(String searchKeyword) {
        SearchHistory searchHistory = new SearchHistory(searchKeyword);
        return findByKeyword(searchHistory);
    }

    public SearchHistory findByKeyword(SearchHistory searchHistory) {
        return searchHistories.stream()
                .filter(s -> s.equals(searchHistory))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Search keyword not found."));
        //TODO:Exception추가
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", searchHistories=" + searchHistories +
                '}';
    }
}
