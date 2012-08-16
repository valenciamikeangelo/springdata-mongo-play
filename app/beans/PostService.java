package beans;

import java.util.List;

import models.Account;
import models.Comment;
import models.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

import repository.PostRepository;
import builders.PostBuilder;


public class PostService {

	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations postOps;	
	
	@Autowired
	private PostRepository repository;
	
	@Autowired
	private PostBuilder postBuilder;
	
	public Post createPost(Account author,String title,String content){
		Post post= new Post(author.id, title, content);
		return updatePost(post);
	}
		
	public List<Post> getPostByAuthor(Account author){
		return  postBuilder.buildPosts(repository.findByAuthor(author.id));
	}
	
	public Post findPostById(ObjectId id){
		return postBuilder.buildPost(repository.findOne(id));
	}
	
	public Post addComment(Account commenter, String content,ObjectId postId) {
		Comment newComment = new Comment(commenter.id, content);
		Post post=findPostById(postId);
		post.comments.add(newComment);
		return updatePost(post);
	}
	
	public Post updatePost(Post post){
		return repository.save(post);    
	}
	
	public MongoOperations getPostOps() {
		return postOps;
	}
}
