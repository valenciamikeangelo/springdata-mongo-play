package beans;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import models.Account;
import models.Comment;
import models.Post;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;


public class PostService {

	private static final Log log = LogFactory.getLog(AccountService.class);
	
	@Autowired
	@Qualifier("mongoTemplate")
	private MongoOperations postOps;	
	
	
	public void createPost(Account account,String title,String content){
		Post post= new Post(account, title, content);
		postOps.insert(post);
	}

	public Post findPostById(String id) {
		return postOps.findById(id, Post.class);
	}
	
	public List<Post> getPostByAuthor(Account author){
		 List<Post> posts = postOps.find(query(where("author").is(author)),Post.class);
		 return posts;
	}
	
	public void deletePostByAuthor(Account author){
		postOps.remove(query(where("author").is(author)),Post.class);
	}
	
	public void addComment(Account commenter, String content,String postId) {
		Comment newComment = new Comment(commenter, content);
		Post post=findPostById(postId);
		post.comments.add(newComment);
		postOps.save(post);
				
	}
	
	public void updatePost(Post post){
		postOps.save(post);    
	}
	
	public void deleteAccount(Post post){
		postOps.remove(post);
		log.info("Delete: " + post);
	}

	public MongoOperations getPostOps() {
		return postOps;
	}
}
