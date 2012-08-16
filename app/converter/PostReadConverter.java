package converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.Post;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class PostReadConverter implements Converter<DBObject, Post>{

	@Override
	public Post convert(DBObject source) {
		Post post= new Post((ObjectId) source.get("authorId"),(String)  source.get("title"), (String) source.get("content"));
		post.postedAt =(Date) source.get("postedAt");
		post.id =(ObjectId) source.get("_id");
		post.comments= resolveComments((BasicDBList)source.get("comments"));
		return post;
	}

	private List<Comment> resolveComments(BasicDBList comments){
		List<Comment> commentsList = new ArrayList<Comment>();
		for(Object source:comments){
			Comment comment = new Comment((ObjectId)((DBObject) source).get("commenterId"),(String)((DBObject) source).get("content"));
			comment.id=(ObjectId) ((DBObject) source).get("_id");
			comment.postedAt =(Date) ((DBObject) source).get("postedAt");
			commentsList.add(comment);
		}
		return commentsList;
	}
	
}

