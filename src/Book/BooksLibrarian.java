package Book;


import Member.GuestMemberData;
import Member.GuestMemberManager;
import Member.GuestMemberVO;

import java.util.*;

public class BooksLibrarian {

    public static void whatDoYouBorrow(String member){
        System.out.print("책 제목을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String book = sc.nextLine();

        if(!doesItExist(book)) {
            System.out.println("관련된 책이 없습니다.");
            System.out.println("관련된 책을 찾고 싶으면 \"도서 찾기\"를 이용해주세요.");
        }
        else{
            showDetail(book);
            if(canItBorrow(book)){
                GuestMemberManager.borrowBook(member,book);
                BooksLibrarian.subtractBook(book);
                GuestMemberManager.showAllBorrowedBook(member,book);
            }
        }
    }

    public static void giveBackBook(String member){
        GuestMemberVO memberVO =GuestMemberData.guestMemberHashMap.get(member);
        ArrayList<BookDate> borrowingBooksList = memberVO.getBorrowingBooksList();

        if(borrowingBooksList.isEmpty()) {
            System.out.println("대출중인 도서가 없습니다.");
            return;
        }

        for(BookDate bd: borrowingBooksList){
            System.out.printf("%s %s\n",bd.getBook(),bd.getDate());
        }


        System.out.println("어떤 책을 반납하시겠습니까?");
        Scanner sc = new Scanner(System.in);
        String book = sc.nextLine();
        boolean exist = false;
        for(BookDate bd: borrowingBooksList){
            if(bd.getBook().equals(book)) exist = true;
        }

        if(!exist) System.out.println("관련된 책이 없습니다. 다시 확인해주세요.");
        else{
            GuestMemberManager.giveBackBook(member,book);
//            GuestMemberManager.showAllBorrowedBook(book,member);
        }
    }

    public static void whatAreYouLookingAt(){
        Set<String> set = BooksData.bookMap.keySet();
        Set<String> setFound = new HashSet<>();
        Iterator iter = set.iterator();


        System.out.print("책과 관련된 제목을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String book = sc.nextLine();

        while(iter.hasNext()) {
            String com = iter.next().toString();
            if(com.contains(book)) setFound.add(com);
        }

        Iterator iterBook = setFound.iterator();
        if(!iterBook.hasNext()) System.out.println("관련된 도서가 없습니다.");
        else{
            UImanager.line();
            System.out.println("전체 검색 목록");
            while(iterBook.hasNext()) {
                System.out.println(iterBook.next());
            }
            UImanager.line();
            System.out.println("전체 검색 세부 목록");
            Iterator iterBook1 = setFound.iterator();
            while(iterBook1.hasNext()) {
                String str = (String) iterBook1.next();
                UImanager.line();
                BooksLibrarian.showDetail((str));
                UImanager.line();
            }
        }
    }

    public static void registerUpdateBook(){
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1.도서 추가 2.도서 수정 3.도서 삭제 4.나가기");
            switch (sc.nextInt()){
                case 1:
                    BooksData.bookAdd();
                    break;
                case 2:
                    BooksData.bookUpdate();
                    break;
                case 3:
                    BooksData.bookRemove();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
                    break;
            }
        }while(true);
    }

    public static boolean doesItExist(String bookName){
        if(BooksData.bookMap.get(bookName)!=null) return true;
        return false;
    }

    public static boolean canItBorrow(String bookName){
        BookVO vo = BooksData.bookMap.get(bookName);
        Scanner sc = new Scanner(System.in);
        if(vo.getTotalBooks()<=0){
            System.out.println("모든 책이 대출중 입니다. 죄송합니다.");
            return false;
        }
        else{
            do {
                try {
                    System.out.print("대출이 가능합니다. 대출 하시겠습니까?(1번 대출하기,2번 아니오):");
                    int num=sc.nextInt();
                    if(num==1){
                        return true;
                    }else if(num==2){
                        System.out.println("도서 대출이 취소되었습니다.");
                        return false;
                    }
                }catch (Exception e){
                    System.out.println("잘못 입력하셨습니다.");
                }
            }while(true);
        }
    }

    public static void showDetail(String bookName){
        BookVO vo = BooksData.bookMap.get(bookName);
        System.out.printf("책 제목:%s\n책 작가:%s\n책 출판사:%s\n책 출판날짜:%d\n책 가격:%d\n책 종류:%s\n책 총갯수:%d\n기타사항:%s\n",bookName,vo.getWriter(),vo.getCompany(),vo.getIssuedDate(),vo.getPrice(),vo.getCategory(),vo.getTotalBooks(),vo.getEtc());
    }

    public static void subtractBook(String bookName){
        BookVO vo = BooksData.bookMap.get(bookName);
        vo.subtractBook();
        BooksData.bookMap.put(bookName,vo);
    }

}
