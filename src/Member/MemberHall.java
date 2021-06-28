package Member;


import java.util.Scanner;

public class MemberHall {



    public static MemberBoolean isMember(boolean borrow){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("회원이시면 이름을 써주시고, 나가실려면 0번을 넣어주세요.:");
            String member = sc.nextLine();

            if(GuestMemberManager.isMember(member)){
                if(borrow==false){
                    return new MemberBoolean(member,true);
                }
                if(GuestMemberManager.isHeBlacklist(member)) {
                    System.out.printf("%s의 회원은 블랙리스트이므로 도서 대출 불가 합니다.\n",member);

                    return new MemberBoolean(member,false);
                }
                else if(GuestMemberManager.canHeBorrow(member)) {
                    System.out.printf("%s의 회원은 대출한도이므로 도서 대출 불가 합니다.\n",member);


                    return new MemberBoolean(member,false);
                }
                else {

                    System.out.printf("%s의 회원은 대출이 가능하고 %s등급 입니다.\n",member,GuestMemberManager.whatIsHisTier(member));

                    return new MemberBoolean(member,true);
                }
            }
            if(member.equals("0")){
                return new MemberBoolean(member,false);
            }
            System.out.println("존재하지않는 회원이거나 잘못 입력하셨습니다.");


        }while(true);
    }

    public static void skimMemberList(){

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("어떤것을 도와 드릴까요?");
            UImanager.line();
            System.out.println("1.회원 만들기 2.회원 찾기 3.모든 회원 목록 4.회원 등급 관리 5.블랙리스트 목록 6.나가기");
            System.out.print("번호를 입력하세요:");

            try {
                int num =sc.nextInt();
                switch (num){
                    case 1: GuestMemberManager.makeMember(); break;
                    case 2: GuestMemberManager.findMember(); break;
                    case 3: GuestMemberManager.getAllMemberList(); break;
                    case 4: GuestMemberManager.gradeTier(); break;
                    case 5: GuestMemberManager.blacklist(); break;
                    case 6: return;
                    default:
                        throw new Exception();
                }
            }catch (Exception e){

                System.out.println("잘못입력하셨습니다.다시 입력해주세요!");
            }
        }while(true);
    }
}
