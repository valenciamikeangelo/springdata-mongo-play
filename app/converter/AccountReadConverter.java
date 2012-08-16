package converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Account;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class AccountReadConverter implements Converter<DBObject, Account>{
	
	@Override
	public Account convert(DBObject source) {
		Account account = new Account((String) source.get("email"), (String) source.get("name"));
		account.id=(ObjectId) source.get("_id");
		account.colleaguesIds=resolveObjectIds((BasicDBList)source.get("colleaguesIds"));
		account.participatedPostIds=resolveObjectIds((BasicDBList)source.get("participatedPostIds"));
		return account;
	}
	
	private List<ObjectId> resolveObjectIds(BasicDBList objectIds){
		List<ObjectId> pobjectIds = new ArrayList<ObjectId>();
		for(Object obj:objectIds){
			pobjectIds.add((ObjectId)((DBObject)obj).get("_id"));
		}
		return pobjectIds;
	}
		
}


