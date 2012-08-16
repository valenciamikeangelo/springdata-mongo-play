package builders;

import java.util.List;

import models.Comment;
import models.Post;

import org.springframework.beans.factory.annotation.Autowired;

import repository.AccountRepository;

public class PostBuilder {

	@Autowired
	private AccountRepository repository;

	public Post buildPost(Post post){
		if(post!=null && !post.comments.isEmpty()){
			buildComments(post);
		}
		return post;
	}
	
	public List<Post> buildPosts(List<Post> posts){
		for(Post post:posts)
		{
			buildPost(post);
		}	
		return posts;
	}
	
	private void buildComments(Post post){
		for(Comment comment: post.comments){
			comment.commenter=repository.findOne(comment.commenterId);
		}
	}
		
}
