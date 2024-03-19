package pl.mateuszswiatek.socialnetworkingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateuszswiatek.socialnetworkingapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
