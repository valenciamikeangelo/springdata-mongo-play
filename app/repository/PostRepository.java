package repository;

import java.util.List;

import models.Post;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, ObjectId>{

	@Query("{'authorId' : ?0 }")
	public List<Post> findByAuthor(ObjectId  authorId);
}
