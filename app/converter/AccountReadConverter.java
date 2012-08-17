package converter;

import models.Account;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class AccountReadConverter extends BaseConverter implements Converter<DBObject, Account>{
	
	@Override
	public Account convert(DBObject source) {
		Account account = new Account((String) source.get("email"), (String) source.get("name"));
		account.id=(ObjectId) source.get("_id");
		account.colleaguesIds=resolveObjectIdsToObjectId((BasicDBList)source.get("colleaguesIds"));
		account.participatedPostIds=resolveObjectIdsToObjectId((BasicDBList)source.get("participatedPostIds"));
		return account;
	}
	
	
}


