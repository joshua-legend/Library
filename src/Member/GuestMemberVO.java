package Member;

import Book.BookDate;
import Book.BookVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;


public class GuestMemberVO{


    static enum Address{
        서울시(0),경기도(1),부산시(2),대구시(3),인천시(4),광주시(5),대전시(6),울산시(7),세종시(8);
        Address(int i) {
        }
        public static GuestMemberVO.Address valueOf(int num) {
            GuestMemberVO.Address address = null;
            switch (num) {
                case 0:
                    address = Address.서울시;
                    break;
                case 1:
                    address = Address.경기도;
                    break;
                case 2:
                    address = Address.부산시;
                    break;
                case 3:
                    address = Address.대구시;
                    break;
                case 4:
                    address = Address.인천시;
                    break;
                case 5:
                    address = Address.광주시;
                    break;
                case 6:
                    address = Address.대전시;
                    break;
                case 7:
                    address = Address.울산시;
                    break;
                case 8:
                    address = Address.세종시;
                    break;
                default:
                    break;
            }
            return address;
        }
        public static int getNum(Address address){
            if(address==서울시) return 0;
            else if(address==경기도) return 1;
            else if(address==부산시) return 2;
            else if(address==대구시) return 3;
            else if(address==인천시) return 4;
            else if(address==광주시) return 6;
            else if(address==대전시) return 7;
            else if(address==울산시) return 9;
            else  return 8;
        }
    };

    static enum Tier{
        Bronze(0),Silver(1),Gold(2),Platinum(3),Diamond(4);
        Tier(int i) {
        }
        public static GuestMemberVO.Tier valueOf(int num) {
            GuestMemberVO.Tier Tier = null;
            switch (num) {
                case 0:
                    Tier = GuestMemberVO.Tier.Bronze;
                    break;
                case 1:
                    Tier = GuestMemberVO.Tier.Silver;
                    break;
                case 2:
                    Tier = GuestMemberVO.Tier.Gold;
                    break;
                case 3:
                    Tier = GuestMemberVO.Tier.Platinum;
                    break;
                case 4:
                    Tier = GuestMemberVO.Tier.Diamond;
                    break;
                default:
                    break;
            }
            return Tier;
        }
        public static int getNum(Tier tier){
            if(tier==Bronze) return 0;
            else if(tier==Silver) return 1;
            else if(tier==Gold) return 2;
            else if(tier==Platinum) return 3;
            else return 4;
        }
    };



    public String name;
    private int year;
    private boolean isMale;
    private Address address;
    private Tier tier = Tier.Bronze;
    private int howManyCanBorrow=2;
    private ArrayList<String> validityBookList = new ArrayList<>();
    private HashMap<String, String> validityBook = new HashMap<>();

    private ArrayList<BookDate> borrowingBooksList = new ArrayList<>();
    private ArrayList<BookDate> borrowedBooksHistoryList = new ArrayList<>();

    private int readBooks =0;
    private boolean isBlacklist=false;


    public GuestMemberVO(String name, int year, boolean isMale, Address address) {
        this.name = name;
        this.year = year;
        this.isMale = isMale;
        this.address = address;

    }

    public void showAllData(boolean one){
        int n = this.year;
        int year = n/10000;
        int month = (n%10000)/100;
        int day = (n%10000)%100;
        String birth = (year+"년"+month+"월"+day+"일");
        String gender= (this.isMale==true) ? "남" : "여";
        String yes = (this.isBlacklist==true) ? "yes" : "no";

        if(one==true)System.out.println("이름 \t     생년월일  \t 성별 \t 행정구역  등급 \t 책대여가능수 \t 블랙리스트 유무\t 대여중인 책과 반납기한");
        System.out.printf("%s\t %s\t %s\t %s    %s\t\t %d\t\t  %s\t",name,birth,gender,address.toString(),tier,howManyCanBorrow,yes);
        if(this.borrowingBooksList.size()==0){
            System.out.println("\t대여중인 책이 없습니다.");
        }
        else{
            for(int i=0;i<this.borrowingBooksList.size();i++){
                System.out.printf("%s %s\n",this.borrowingBooksList.get(i).getBook(),this.borrowingBooksList.get(i).getDate());
            }
        }
    }

    public int getYear() { return year; }

    public Address getAddress() { return address; }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
    public int getHowManyCanBorrow() {
        return howManyCanBorrow;
    }

    public void setHowManyCanBorrow(int howManyCanBorrow) {
        this.howManyCanBorrow = howManyCanBorrow;
    }

    public void borrowOneBook(){
        this.howManyCanBorrow = this.howManyCanBorrow-1;
    }

    public void giveBackBook(){
        this.howManyCanBorrow = this.howManyCanBorrow+1;
    }

    public boolean isBlacklist() {
        return isBlacklist;
    }

    public void setBlacklist(boolean q){
        this.isBlacklist=q;
    }

    public void setBorrowingBooksList(String bookName,String after7) {
        this.readBooks++;
        this.borrowingBooksList.add(new BookDate(bookName,after7));
    }

    public void removeBorrowingBooksList(String bookName) {
        ArrayList<BookDate> bd = new ArrayList<>();
        BookDate hit = null, current = null;
        for(int i=0;i<borrowingBooksList.size();i++){
            current = (BookDate)borrowingBooksList.get(i);
            if(current.getBook().equals(bookName)) hit = current;
        }
        this.borrowingBooksList.remove(hit);
    }

    public int getReadBooks() {
        return readBooks;
    }

    public ArrayList<BookDate> getBorrowedBooksHistoryList() {
        return borrowedBooksHistoryList;
    }

    public ArrayList<BookDate> getBorrowingBooksList() { return borrowingBooksList; }

    public void setBorrowedBooksHistoryList(String bookName, String after7) {
        this.borrowedBooksHistoryList.add(new BookDate(bookName,after7));
    }

    public void setValidityBookList(String validityBookList) {
        this.validityBookList.add(validityBookList);
    }

    public void setValidityBook(String bookName,String time) {
        this.validityBook.put(bookName,time);
    }

    public HashMap<String, String> getValidityBook() {
        return validityBook;
    }
}
