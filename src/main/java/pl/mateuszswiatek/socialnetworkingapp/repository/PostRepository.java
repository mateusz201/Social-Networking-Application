package pl.mateuszswiatek.socialnetworkingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
