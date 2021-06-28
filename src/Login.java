public class Login {

    public final static String TOKEN = "bitcamp";
    public final static String USERID = "1";
    public final static String PASSWORD = "1";

    public boolean loginCheck(String userid, String userpwd){
        if(userid.equals(this.USERID)&&userpwd.equals(this.PASSWORD)) return true;
        else return false;
    }

    public void makeMember(){

    }
}
