package converter;

import java.util.List;

import models.Comment;
import models.Post;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PostWriteConverter extends BaseConverter implements Converter<Post, DBObject>{

	@Override
	public DBObject convert(Post source) {
		DBObject dbo = new BasicDBObject();
	    dbo.put("_id", source.id);
	    dbo.put("title", source.title);
	    dbo.put("content", source.content);
	    dbo.put("postedAt", source.postedAt);
	    dbo.put("authorId", source.authorId);
	    dbo.put("comments", resolveComments(source.comments));
	    return dbo;
	}

	private BasicDBList resolveComments(List<Comment> comments){
		BasicDBList commentsList = new BasicDBList();
		for(Comment source:comments){
			DBObject dbo = new BasicDBObject();
		    dbo.put("_id", source.id);
		    dbo.put("commenterId", source.commenterId);
		    dbo.put("content", source.content);
		    dbo.put("postedAt", source.postedAt);
		    commentsList.add(dbo);
		}
		return commentsList;
	}
}
