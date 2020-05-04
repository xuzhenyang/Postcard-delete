package co.lilpilot.postcard.postcontext.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("from Post post where post.status = 2 order by post.id desc")
    Page<Post> pagePublicPosts(Pageable pageable);
}
