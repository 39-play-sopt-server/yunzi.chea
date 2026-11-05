package org.sopt.post.repository;


import org.sopt.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);
    List<Post> findAll();

    void deleteById(Long id);

    boolean existsByMemberId(Long memberId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
    int increaseViewCount(@Param("id") Long id);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAll(Pageable pageable);

    // 게시글을 담기.
    // private final Map<Long,Post> posts = new LinkedHashMap<>();
    // private long nextId = 0;

    /*public Post save(String title, String content, Category category, String author) {
        nextId++;
        Post nextPost = new Post(nextId,title, content, category, author);
        posts.put(nextId,nextPost);
        return nextPost;
    }*/

    /*public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public List<Post> findAll () {
        return new ArrayList<>(posts.values());
    }

    public void deleteById(Long id) {
        posts.remove(id);
    }*/
}
