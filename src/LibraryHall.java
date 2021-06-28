import Book.BooksData;
import Book.BooksLibrarian;
import Member.MemberBoolean;
import Member.GuestMemberData;
import Member.MemberHall;

import java.util.Scanner;

public class LibraryHall {

    BooksData booksData = new BooksData();
    GuestMemberData guestMemberData = new GuestMemberData();

    public LibraryHall(){
        guestMemberData.basicGuestMemberset();
        booksData.basicBookSet();
    }

    public static void mainHall(){
        do {
            UImanager.line();
            System.out.println("어떤것을 도와 드릴까요?");
            System.out.println("1.도서 대출 2.도서 반납 3.도서 찾기 4.도서 추가 및 수정 5.회원 관리 6.시스템 종료");
            UImanager.line();
            System.out.print("번호를 입력하세요:");

            try {
                Scanner sc = new Scanner(System.in);
                int num =sc.nextInt();
                sc.nextLine(); //enter 방지

                switch (num){
                    case 1:{
                        MemberBoolean mb = MemberHall.isMember(true);
                        if(mb.isTrueOrFalse())BooksLibrarian.whatDoYouBorrow(mb.getMember());
                        else break;
                        break;
                    }
                    case 2: {
                        MemberBoolean mb = MemberHall.isMember(false);
                        BooksLibrarian.giveBackBook(mb.getMember());
                        break;
                    }
                    case 3: BooksLibrarian.whatAreYouLookingAt(); break;
                    case 4: BooksLibrarian.registerUpdateBook(); break;
                    case 5: MemberHall.skimMemberList(); break;
                    case 6: {
                        UImanager.endEngine();
                        sc.nextLine();
                        System.exit(1);
                    }
                    default:
                        System.out.println("잘못입력하셨습니다. 다시 입력해주세요!");
                        break;
                }
            }catch (Exception e){
                System.out.println("잘못입력하셨습니다. 다시 입력해주세요!");
            }
        }while(true);
    }


}
