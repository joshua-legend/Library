import java.util.ArrayList;

public class UserManager {

    ArrayList<User> userArrayList = new ArrayList<>();
    UserManager(){
        User user = new User("1","1");
        userArrayList.add(user);
    }

    public void userAdd(String id,String pwd){
        User user = new User(id,pwd);
        userArrayList.add(user);
    }

    public boolean doesItsIDExist(String id){
        for(int i=0;i<userArrayList.size();i++){
            if(userArrayList.get(i).getId().equals(id)) return true;
        }
        return false;
    }

    public boolean doesItsPWDExist(String pwd){
        for(int i=0;i<userArrayList.size();i++){
            if(userArrayList.get(i).getPwd().equals(pwd)) return true;
        }
        return false;
    }

}
