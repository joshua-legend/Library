package Book;

import java.util.ArrayList;

public class BookVO {
    public static enum Category{
        TOTAL(0), PHILOSOPHY(1), RELIGION(2), SOCIAL_SCIENCE(3), NATURAL_SCIENCE(4), TECHNICAL_SCIENCE(5), ART(6), LANGUAGE(7), LITERATURE(8), HISTORY(9);

        Category(int i) {
        }
        public static Category valueOf(int num) {
            Category category = null;
            switch (num) {
                case 0:
                    category = Category.TOTAL;
                    break;
                case 1:
                    category = Category.PHILOSOPHY;
                    break;
                case 2:
                    category = Category.RELIGION;
                    break;
                case 3:
                    category = Category.SOCIAL_SCIENCE;
                    break;
                case 4:
                    category = Category.NATURAL_SCIENCE;
                    break;
                case 5:
                    category = Category.TECHNICAL_SCIENCE;
                    break;
                case 6:
                    category = Category.ART;
                    break;
                case 7:
                    category = Category.LANGUAGE;
                    break;
                case 8:
                    category = Category.LITERATURE;
                    break;
                case 9:
                    category = Category.HISTORY;
                    break;
                default:
                    break;
            }
            return category;
        }
    };



    private String writer;
    private String company;
    private int price;
    private int issuedDate;
    private Category category;
    private int totalBooks;
    private int borrowedBooks=0;
    private String etc;
    private ArrayList<String> memberList = new ArrayList<>();

    public BookVO(String writer, String company, int price, int issuedDate, BookVO.Category category, int totalBooks, String etc) {
        this.writer = writer;
        this.company = company;
        this.price = price;
        this.issuedDate = issuedDate;
        this.category = category;
        this.totalBooks = totalBooks;
        this.etc = etc;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

    public int getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(int issuedDate) {
        this.issuedDate = issuedDate;
    }

    public BookVO.Category getCategory() {
        return category;
    }

    public void setCategory(BookVO.Category category) {
        this.category = category;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int num) {
        this.totalBooks = num;
    }

    public void subtractBook(){
        this.totalBooks -= 1;
    }

    public void addBook(){
        this.totalBooks += 1;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public ArrayList<String> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<String> memberList) {
        this.memberList = memberList;
    }
}
