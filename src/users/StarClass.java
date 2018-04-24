package users;

import util.Iterator;
import util.Array;
import util.ArrayClass;

public abstract class StarClass extends UserClass{
	
	private Array<User> blackList = new ArrayClass<>();
	
	public void addToBlackList(User user){
		blackList.insertLast(user);
	}
	
	public void removeFromBlackList(User user){
		blackList.remove(user);
	}
	
	public boolean isUserOnBlackList(User user){
		return blackList.searchIndexOf(user) >= 0;
	}
	
	public boolean isUserOnBlackList(Array<User> users){
		Iterator<User> it = users.iterator();
		while(it.hasNext()){
			User user= it.next();
			
			if(this.isUserOnBlackList(user))
				return true;
			
		}
		
		return false;
	}
}
