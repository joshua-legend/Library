package Book;

import java.util.HashMap;
import java.util.Scanner;

public class BooksData {

    public static HashMap<String, BookVO> bookMap = new HashMap<>();


    public static void basicBookSet(){
        bookMap.put("공정한 정의",new BookVO("마이클 샌델","와이즈베리",16200,20201201,BookVO.Category.PHILOSOPHY,1,"없음"));
        bookMap.put("질서 너머",new BookVO("조던 피터슨","웅진지식하우스",16020,20210322,BookVO.Category.PHILOSOPHY,2,"없음"));
        bookMap.put("윤회의 본질",new BookVO("크리스토퍼 M.베이치","정신세계사",13500,202140305,BookVO.Category.RELIGION,5,"없음"));
        bookMap.put("완전한 행복",new BookVO("정유정","은행나무",14220,20210608,BookVO.Category.LITERATURE,3,"없음"));
        bookMap.put("물은 H2O인가?",new BookVO("장하석","김영사",26820,20210610,BookVO.Category.NATURAL_SCIENCE,3,"없음"));
        bookMap.put("혼자 공부하는 머신러닝+딥러닝",new BookVO("박해선","한빛미디어",23400,20201221,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
        bookMap.put("클린 아키텍처: 소프트웨어 구조와 설계의 원칙",new BookVO("로버트 C.마틴","인사이트",26100,20190820,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
        bookMap.put("모던 자바스크립트 Deep Dive",new BookVO("이웅모","위키북스",40500,20200925,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
        bookMap.put("혼자 공부하는 자바",new BookVO("신용권","한빛미디어",21600,20190610,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
        bookMap.put("코어 자바스크립트",new BookVO("정재남","위키북스",19800,20190910,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
        bookMap.put("이펙티브 자바",new BookVO("조슈아","인사이트",32400,20181101,BookVO.Category.TECHNICAL_SCIENCE,3,"없음"));
    }

    public static void bookAdd(){
        UImanager.line();
        System.out.print("책 제목을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(BooksLibrarian.doesItExist(name)) {
            System.out.printf("%s는 존재하는 책입니다. 수량을 늘리고 싶으면 \"도서 수정\"을 이용해주세요\n", name);
        }
        else{
            addMethod(name);
        }
    }

    public static void bookUpdate(){

        System.out.print("책 제목을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(BooksLibrarian.doesItExist(name))
            updateMethod(name);
        else{
            System.out.printf("%s는 존재하지 않는 책입니다. 책을 찾고 싶으면 \"도서 찾기\"를 이용해주세요\n",name);
        }
    }

    public static void bookRemove(){
        System.out.print("책 제목을 입력해주세요:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(BooksLibrarian.doesItExist(name))
            removeMethod(name);
        else{
            System.out.printf("%s는 존재하지 않는 책입니다. 책을 찾고 싶으면 \"도서 찾기\"를 이용해주세요\n",name);
        }
    }

    public static void addMethod(String bookName){
        String writer,company,etc;
        int price,issuedDate,totalBooks,num,num1;
        BookVO.Category category;
        BookVO bookVO;
        Scanner sc =new Scanner(System.in);
        do {
            System.out.print("책 저자를 입력해주세요:");
            writer=sc.nextLine();
            System.out.print("책 출판사를 입력해주세요:");
            company=sc.nextLine();
            do {
                try {
                    System.out.print("책 가격을 입력해주세요.(예시 21500원 -> 21500 입력):");
                    price=sc.nextInt();
                    System.out.print("책  출판일을 입력해주세요.(예시 2020년 1월 1일 출판 -> 202001010):");
                    issuedDate=sc.nextInt();
                    System.out.print("책 종류를 선택해주세요.(0.총류 1.철학 2.종교 3.사회과학 4.자연과학 5.기술과학 6.예술 7.문학 8.역사):");
                    num = sc.nextInt();
                    if(num<0 || num>8) throw new Exception();
                    category=BookVO.Category.valueOf(num);
                    System.out.print("책 총 수량을 선택해주세요:");
                    totalBooks=sc.nextInt();
                    sc.nextLine();
                    System.out.print("책 기타사항을 넣어주세요(없으면 엔터입력):");
                    etc=sc.nextLine();
                    break;
                }catch (Exception e){
                    System.out.println("잘못 입력하였습니다. 다시 해주세요.");
                }
            }while(true);

            boolean flag = false;
            do {
                System.out.printf("책 제목:%s\n책 저자:%s \n책 출판사:%s\n책 가격:%d\n책 출판일:%d\n책 종류:%s\n책 수량:%d\n기타사항:%s\n위 상황들이 맞습니까(예:1 아니오:2 도서 추가 취소하기:3)",bookName,writer,company,price,issuedDate,category.toString(),totalBooks,etc);
                num1 = sc.nextInt();
                if(num1==1) {
                    flag=true;
                    bookVO = new BookVO(writer,company,price,issuedDate,category,totalBooks,etc);
                    BooksData.bookMap.put(bookName,bookVO);
                    System.out.printf("%s 책이 성공적으로 등록되었습니다.\n",bookName);
                    break;
                }
                else if(num==2) break;
                else if(num==3){
                    flag=true;
                    break;
                }
                else {
                    System.out.println("잘못 입력하였습니다.");
                    continue;
                }
            }while(true);
            if(flag==true) break;
        }while(true);
    }

    public static void updateMethod(String bookName){
        BooksLibrarian.showDetail(bookName);
        Scanner sc = new Scanner(System.in);
        int num;
        do {
            try{
                System.out.println("어떤 것을 수정하고 싶으십니까?");
                System.out.print("1번.제목  2번.작가  3번.출판사  4번.출판날짜  5번.가격  6번.종류  7번.총 갯수  8번.기타사항 9번.나가기\n번호 입력:");
                num = sc.nextInt();
                if(num<0 || num>10) throw new Exception();
                else break;
            }catch (Exception e){
                System.out.println("잘못 입력하였습니다. 다시해주세요!");
            }
        }while(true);

        sc.nextLine(); //엔터
        String str="";
        BookVO vo;
        BookVO.Category category;
        int int1;

        switch (num){
            case 1:
                System.out.print("제목을 입력하세요:");
                str = sc.nextLine();
                vo = BooksData.bookMap.get(bookName);
                BooksData.bookMap.remove(bookName);
                BooksData.bookMap.put(str,vo);
                break;
            case 2:
                System.out.print("작가를 입력하세요:");
                str = sc.nextLine();
                vo = BooksData.bookMap.get(bookName);
                vo.setWriter(str);
                BooksData.bookMap.put(bookName,vo);
                break;
            case 3:
                System.out.print("출판사를 입력하세요:");
                str = sc.nextLine();
                vo = BooksData.bookMap.get(bookName);
                vo.setCompany(str);
                BooksData.bookMap.put(bookName,vo);
                break;
            case 4:
                System.out.print("출판날짜를 입력하세요:");
                str = sc.nextLine();
                vo = BooksData.bookMap.get(bookName);
                vo.setWriter(str);
                BooksData.bookMap.put(bookName,vo);
                break;
            case 5:
                do {
                    try {
                        System.out.print("가격을 입력하세요:");
                        int1 = sc.nextInt();
                        vo = BooksData.bookMap.get(bookName);
                        vo.setPrice(int1);
                        BooksData.bookMap.put(bookName,vo);
                        break;
                    }catch (Exception e){
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }while(true);
            case 6:
                do {
                    try {
                        System.out.print("책 종류를 선택해주세요.(0.총류 1.철학 2.종교 3.사회과학 4.자연과학 5.기술과학 6.예술 7.문학 8.역사):");
                        int1 = sc.nextInt();
                        if(int1<0 || int1>8) throw new Exception();
                        category=BookVO.Category.valueOf(int1);
                        vo = BooksData.bookMap.get(bookName);
                        vo.setCategory(category);
                        BooksData.bookMap.put(bookName,vo);
                        break;
                    }catch (Exception e){
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }while(true);
            case 7:
                do {
                    try {
                        System.out.print("총 갯수를 입력하세요:");
                        int1 = sc.nextInt();
                        vo = BooksData.bookMap.get(bookName);
                        vo.setTotalBooks(int1);
                        BooksData.bookMap.put(bookName,vo);
                        break;
                    }catch (Exception e){
                        System.out.println("잘못 입력하셨습니다.");
                    }
                }while(true);
                break;
            case 8:
                System.out.print("기타사항을 입력하세요:");
                str = sc.nextLine();
                vo = BooksData.bookMap.get(bookName);
                vo.setEtc(str);
                BooksData.bookMap.put(bookName,vo);
                break;
            }
    }

    public static void removeMethod(String bookName){
        BooksLibrarian.showDetail(bookName);
        System.out.println("삭제하고 싶으십니까?(1번. 삭제하기 아무 키. 취소)");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        if(Integer.parseInt(name)==1){
            BooksData.bookMap.remove(bookName);
            System.out.println("도서가 삭제되었습니다.");
        }
        else System.out.println("도서 삭제가 취소되었습니다.");
    }
}
