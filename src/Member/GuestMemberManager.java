package Member;

import Book.BookVO;
import Book.BooksData;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

public class GuestMemberManager {

    public static boolean isMember(String name){
        Set<String> set = GuestMemberData.guestMemberHashMap.keySet();
        Iterator<String> it = set.iterator();
        boolean isMember = false;
        while(it.hasNext()){
            String finder = it.next();
            if(name.equals(finder)) isMember=true;
        }
        return isMember;
    }

    public static Boolean isHeBlacklist(String name){
        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);
        if(member.isBlacklist()) return true;
        else return false;
    }

    public static void blacklist(){
        System.out.println("1번.블랙리스트 명단 2번.블랙리스트 등록 및 취소 3번.나가기");
        Scanner sc = new Scanner(System.in);
        do {
            try {
                int num = sc.nextInt();
                switch (num){
                    case 1:
                        showAllBlacklist();
                        return;
                    case 2:
                        updateBlacklist();
                        return;
                    case 3:
                        return;
                    default: throw new Exception();
                }
            }catch (Exception e){
                System.out.println("잘못 입력하였습니다. 다시해주세요.");
                continue;
            }
        }while (true);
    }

    public static Boolean canHeBorrow(String name){
        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);
        if(member.getHowManyCanBorrow()>0) return false;
        else return true;
    }

    public static GuestMemberVO.Tier whatIsHisTier(String name){
        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);
        return member.getTier();
    }

    public static void borrowBook(String name,String book){

        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);

        member.borrowOneBook();

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
        Date timeAfter7 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(timeAfter7);
        cal.add(Calendar.DATE,+7);

        String after7 = format.format(cal.getTime());
        System.out.println("반납 기한은 "+after7+"까지 입니다.");

        member.setBorrowingBooksList(book,after7);
        member.setBorrowedBooksHistoryList(book,after7);
        member.setValidityBook(book,after7);
    }

    public static void giveBackBook(String name,String book){

        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);

        SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
        Date giveBackTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(giveBackTime);

        String giveBack = format.format(cal.getTime());
        System.out.println("반납된 날짜는 "+giveBack+"까지 입니다. 감사합니다.");
        member.removeBorrowingBooksList(book);
        member.giveBackBook();
    }

    public static void showAllBorrowedBook(String name,String book){
        GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(name);
        System.out.printf("%s 고객님은 %s 책을 대출하였으며, 지금 남은 대출 한도는 %d개 입니다.\n",name,book,member.getHowManyCanBorrow());
        System.out.print("대출 이력\n");
        System.out.println("책 제목\t\t 반납기한");
        for(int i=0;i<member.getBorrowedBooksHistoryList().size();i++)
            System.out.printf("%s %s\n",member.getBorrowedBooksHistoryList().get(i).getBook(),member.getBorrowedBooksHistoryList().get(i).getDate());
    }

    public static void showAllBlacklist(){
        Set<String> memberSet = GuestMemberData.guestMemberHashMap.keySet();
        ArrayList<GuestMemberVO> guestMemberVOSList = new ArrayList<>();
        List memberList = new ArrayList(memberSet);

        for(Object str:memberList){
            if(GuestMemberData.guestMemberHashMap.get((String)str).isBlacklist())
                guestMemberVOSList.add(GuestMemberData.guestMemberHashMap.get((String)str));
        }
        boolean a = true;
        if(guestMemberVOSList.isEmpty()) System.out.println("블랙리스트가 존재하지 않습니다.");
        else for(GuestMemberVO vo : guestMemberVOSList) {
            vo.showAllData(a);
            a=false;
        }
    }

    public static void updateBlacklist(){
        System.out.print("회원님의 성함을 올바르게 입력해주세요 [찾고 싶을 경우 \"회원 찾기\"를 이용해 주세요]:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        if(!GuestMemberData.guestMemberHashMap.containsKey(name)) {
            System.out.printf("%s의 회원을 찾지 못했습니다.\n", name);
            System.out.println("다시 확인해 주세요.");
            return;
        }
        else{
            GuestMemberVO vo = GuestMemberData.guestMemberHashMap.get(name);

            vo.showAllData(true);

            System.out.println("어떤것으로 변경할까요? [1.블랙리스트 설정 2.블랙리스트 해제 그 외 번호: 나가기]");
            int num = sc.nextInt();
            switch (num){
                case 1:
                    vo.setBlacklist(true);
                    break;
                case 2:
                    vo.setBlacklist(false);
                    break;
                default:
                    System.out.println("블랙리스트 설정이 취소되었습니다.");
                    return;
            }
            System.out.println("변경사항");
            vo.showAllData(true);
        }

    }

    public static void makeMember(){
        String name,sex;
        int year,gender;
        boolean isMale = false;
        GuestMemberVO guestMemberVO;
        GuestMemberVO.Address address;
        int num,num1;
        Scanner sc =new Scanner(System.in);

        do {
            System.out.print("성함을 입력해 주세요:");
            name=sc.nextLine();
            do {
                try {
                    {
                        System.out.print("출생 년도를 입력해주세요.(예시 1999년 1월 1일 -> 19990101):");
                        year=sc.nextInt();
                        int n = year;
                        int birth = n/10000;
                        int month = (n%10000)/100;
                        int day = (n%10000)%100;
                        if(birth<1900 || birth > 3000) throw new Exception();
                        if(month<0 || month>12) throw new Exception();
                        if(day<0 || day >31 ) throw new Exception();
                    }
                    System.out.print("성별을 선택해주세요.(1번.남자 2번.여자):");
                    gender=sc.nextInt();
                    if(gender != 1 && gender != 2) throw new Exception();
                    if(gender == 1) {
                        sex ="남성";
                        isMale= true;
                    }
                    else {
                        sex ="여성";
                        isMale = false;
                    }

                    System.out.print("지역 종류를 선택해주세요.(Seoul(0),Gyeonggi(1),Busan(2),Daegu(3),Incheon(4),Gwangju(5),Daejeon(6),Ulsan(7),Sejong(8)):");
                    num = sc.nextInt();
                    if(num<0 || num>8) throw new Exception();
                    address=GuestMemberVO.Address.valueOf(num);
                    break;
                }catch (Exception e){
                    System.out.println("잘못 입력하였습니다. 다시 해주세요.");
                }
            }while(true);
            boolean flag = false;

            do {
                System.out.printf("성함:%s\n출생년도:%d\n성별:%s\n지역:%s\n위 상황들이 맞습니까(1번:예 2번:아니오 3번:회원등록 취소하기):",name,year,sex,address.toString());
                num1 = sc.nextInt();
                if(num1==1) {
                    flag=true;
                    guestMemberVO = new GuestMemberVO(name,year,isMale,address);
                    GuestMemberData.guestMemberHashMap.put(name,guestMemberVO);
                    System.out.printf("%s님이 성공적으로 회원등록되었습니다.\n",name);
                    break;
                }
                else if(num1==2) break;
                else if(num1==3) return;
                else {
                    System.out.println("잘못 입력하였습니다.");
                    continue;
                }
            }while(true);
            if(flag==true) break;
        }while(true);
    }

    public static void findMember(){
        Set<String> set = GuestMemberData.guestMemberHashMap.keySet();
        Set<String> setFound = new HashSet<>();
        Iterator iter = set.iterator();
        boolean a = true;

        System.out.print("회원님의 성함을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        while(iter.hasNext()) {
            String com = iter.next().toString();
            if(com.contains(name)) setFound.add(com);
        }

        Iterator iterName= setFound.iterator();
        if(!iterName.hasNext()) System.out.println("관련된 회원이 없습니다.");
        while(iterName.hasNext()) {
            GuestMemberVO member = (GuestMemberVO)GuestMemberData.guestMemberHashMap.get(iterName.next());
            member.showAllData(a);
            a=false;
        }
    }

    public static void gradeTier(){

        System.out.print("회원님의 성함을 올바르게 입력해주세요 [찾고 싶을 경우 \"회원 찾기\"를 이용해 주세요]:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        boolean a = true;

        if(!GuestMemberData.guestMemberHashMap.containsKey(name)) {
            System.out.printf("%s의 회원을 찾지 못했습니다.\n", name);
            System.out.println("다시 확인해 주세요.");
            return;
        }
        else{
            GuestMemberVO vo = GuestMemberData.guestMemberHashMap.get(name);
            vo.showAllData(true);

            System.out.println("어떤 등급으로 변경할까요? [1.Bronze 2.Sliver 3.Gold 4.Platinum 5.Diamond 그 외 번호: 나가기]");
            int num = sc.nextInt();
            switch (num){
                case 1:
                    vo.setTier(GuestMemberVO.Tier.Bronze);
                    vo.setHowManyCanBorrow(1);
                    break;
                case 2:
                    vo.setTier(GuestMemberVO.Tier.Silver);
                    vo.setHowManyCanBorrow(2);
                    break;
                case 3:
                    vo.setTier(GuestMemberVO.Tier.Gold);
                    vo.setHowManyCanBorrow(3);
                    break;
                case 4:
                    vo.setTier(GuestMemberVO.Tier.Platinum);
                    vo.setHowManyCanBorrow(4);
                    break;
                case 5:
                    vo.setTier(GuestMemberVO.Tier.Diamond);
                    vo.setHowManyCanBorrow(5);
                    break;
                default:
                    System.out.println("등급 변경이 취소되었습니다.");
                    return;
            }
            System.out.println("변경사항");
            vo.showAllData(true);
        }
    }

    public static void getAllMemberList(){
        Scanner sc =new Scanner(System.in);
        System.out.println("어떤 정렬순으로 나열할까요?");
        System.out.println("1번.나이 2번.가나다순 3번.등급별 4번.지역별 5번.나가기");

        try {
            int num = sc.nextInt();
            sortBySomething(num);

        }catch (Exception e){
            System.out.println("잘못입력하였습니다.");
        }
    }

    public static void sortBySomething(int num){
        Set<String> memberSet = GuestMemberData.guestMemberHashMap.keySet();
        ArrayList<GuestMemberVO> guestMemberVOSList = new ArrayList<>();
        List memberList = new ArrayList(memberSet);
        boolean a= true;

        for(Object str:memberList){
            guestMemberVOSList.add(GuestMemberData.guestMemberHashMap.get((String)str));
        }

        switch (num){
            case 1:
                Collections.sort(guestMemberVOSList, new Comparator<GuestMemberVO>() {
                    @Override
                    public int compare(GuestMemberVO o1, GuestMemberVO o2) {
                        if(o1.getYear() < o2.getYear()) return -1;
                        else if(o1.getYear() > o2.getYear()) return 1;
                        return 0;
                    }
                });

                for(GuestMemberVO vo : guestMemberVOSList){
                    vo.showAllData(a);
                    a=false;
                }
                break;
            case 2:
                Collections.sort(memberList);
                for(Object ob : memberList){
                    GuestMemberData.guestMemberHashMap.get(ob).showAllData(a);
                    a=false;
                }
                break;
            case 3:
                Collections.sort(guestMemberVOSList, new Comparator<GuestMemberVO>() {
                    @Override
                    public int compare(GuestMemberVO o1, GuestMemberVO o2) {
                        if(GuestMemberVO.Tier.getNum(o1.getTier()) < GuestMemberVO.Tier.getNum(o1.getTier())) return -1;
                        else if(GuestMemberVO.Tier.getNum(o1.getTier()) > GuestMemberVO.Tier.getNum(o1.getTier())) return 1;
                        return 0;
                    }
                });
                System.out.println("정렬 순서입니다. Bronze -> Sliver -> Gold -> Platinum -> Diamond;");
                for(GuestMemberVO vo : guestMemberVOSList){
                    vo.showAllData(a);
                    a=false;

                }
                break;
            case 4:
                Collections.sort(guestMemberVOSList, new Comparator<GuestMemberVO>() {
                    @Override
                    public int compare(GuestMemberVO o1, GuestMemberVO o2) {
                        if(GuestMemberVO.Address.getNum(o1.getAddress()) < GuestMemberVO.Address.getNum(o1.getAddress())) return -1;
                        else if(GuestMemberVO.Address.getNum(o1.getAddress()) > GuestMemberVO.Address.getNum(o1.getAddress())) return 1;
                        return 0;
                    }
                });
                System.out.println("정렬 순서입니다. 서울(0),경기(1),부산(2),대구(3),인천(4),광주(5),대전(6),울산(7),세종(8);");
                for(GuestMemberVO vo : guestMemberVOSList) {
                    vo.showAllData(a);
                    a=false;
                }
                break;
        }


    }
}
