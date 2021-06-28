package Member;

import Book.*;

import java.util.*;

public class GuestMemberData {

    public static HashMap<String, GuestMemberVO> guestMemberHashMap = new HashMap<>();

    public static void basicGuestMemberset(){
        guestMemberHashMap.put("손흥민",new GuestMemberVO("손흥민",19920104,true, GuestMemberVO.Address.서울시));
        guestMemberHashMap.put("황의조",new GuestMemberVO("황의조",19950204,true, GuestMemberVO.Address.부산시));
        guestMemberHashMap.put("김영권",new GuestMemberVO("김영권",19880312,true, GuestMemberVO.Address.서울시));
        guestMemberHashMap.put("이승희",new GuestMemberVO("이승희",20120815,false, GuestMemberVO.Address.세종시));
        guestMemberHashMap.put("김수효",new GuestMemberVO("김수효",19630404,false, GuestMemberVO.Address.울산시));
        guestMemberHashMap.put("전수효",new GuestMemberVO("전수효",19930404,true, GuestMemberVO.Address.경기도));
        guestMemberHashMap.put("김시솔",new GuestMemberVO("김시솔",20010805,true, GuestMemberVO.Address.경기도));
        guestMemberHashMap.put("이기솔",new GuestMemberVO("이기솔",19751221,false, GuestMemberVO.Address.인천시));
        guestMemberHashMap.put("이재효",new GuestMemberVO("이재효",19811202,false, GuestMemberVO.Address.인천시));
        guestMemberHashMap.put("박재효",new GuestMemberVO("박재효",19991111,true, GuestMemberVO.Address.광주시));
        guestMemberHashMap.put("권창훈",new GuestMemberVO("권창훈",19980808,false, GuestMemberVO.Address.광주시));
        guestMemberHashMap.put("김진수",new GuestMemberVO("김진수",19910405,true, GuestMemberVO.Address.광주시));

    }
}
