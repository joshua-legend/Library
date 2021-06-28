
import Book.BooksData;
import Member.GuestMemberData;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class LibraryManager {
    Scanner sc = new Scanner(System.in);
    String menuTitle = "메뉴[1. 사원전체 2. 사원추가, 3. 사원수정, 4.사원삭제, 5.종료]";
    UserManager um = new UserManager();

    public LibraryManager() {

    }

    public void start() {
        UImanager.startEngine();
        whoAreYou(um);
        UImanager.welcome();
        GuestMemberData.basicGuestMemberset();
        BooksData.basicBookSet();
        LibraryHall.mainHall();
    }

    public void whoAreYou(UserManager um){
        boolean flag = true;
        do {
            UImanager.line();
            System.out.println("1.관리자 로그인 하기 2.관리자 등록하기 3.프로그램 종료 ");
            UImanager.line();
            try {
                System.out.print("번호를 입력하세요:");
                int num =sc.nextInt();
                sc.nextLine(); //enter 방지
                switch (num){
                    case 1: getLogin(um); flag= false; break;
                    case 2: insertToken(um); break;
                    case 3: {
                        UImanager.endEngine();
                        sc.nextLine();
                        System.exit(1);
                    }
                    default:
                        System.out.println("잘못입력하셨습니다. 다시 입력해주세요!");break;
                }
            }catch (Exception e){
                System.out.println("잘못입력하셨습니다. 다시 입력해주세요!");
            }
        }while(flag);
    }

    public void getLogin(UserManager um){
        String user,pwd;
        do {
            System.out.print("아이디를 입력하세요(exit 종료):");
            user = sc.nextLine();
            if(user.equals("exit")){
                UImanager.endEngine();
                sc.nextLine();
                System.exit(1);
            }

            System.out.print("비밀번호를 입력하세요:");
            pwd = sc.nextLine();

            if(um.doesItsIDExist(user) && um.doesItsPWDExist(pwd)){
                System.out.println("로그인 성공");
                break;
            }
            else System.out.println("아이디 또는 비밀번호가 틀렸으니 다시 입력해주세요!");
        }while(true);
    }

    public boolean insertToken(UserManager um){
        System.out.print("토큰 값을 입력해주세요[나가기 0번]:");
        do {
            String check= sc.next();
            if(Login.TOKEN.equals(check)){
                registerMember(um);
                return true;
            }
            else if(check.equals("0")) return false;
            else System.out.print("토큰 값이 다릅니다. 다시 입력해주세요[나가기 0번]:");
        }while (true);
    }

    public void registerMember(UserManager um){
        String id;
        int pwd,checkpwd,check;
        Scanner sc = new Scanner(System.in);

        System.out.print("등록할 ID를 넣어주세요:");
        id = sc.nextLine();
        System.out.print("비밀번호를 넣어주세요:");
        pwd = sc.nextInt();
        System.out.print("비밀번호를 다시 넣어주세요:");
        checkpwd = sc.nextInt();

        do{
            try {
                if(pwd != checkpwd) throw new Exception();
                System.out.printf("%s로 등록하시겠습니까?(예:1 아니오:2):",id);
                check = sc.nextInt();
                if(check==1) {
                    um.userAdd(id,Integer.toString(pwd));
                    break;
                }
                else if(check==2){
                    System.out.println("등록이 취소되었습니다.");
                    break;
                }
                else{
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("잘못 입력하였습니다. 다시 해주세요!");
                break;
            }
        }while(true);
    }
}
