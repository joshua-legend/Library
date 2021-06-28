package Member;

public class MemberBoolean {

    private String member;
    private boolean trueOrFalse;

    public MemberBoolean(String member, boolean trueOrFalse) {
        this.member = member;
        this.trueOrFalse = trueOrFalse;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public boolean isTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }
}


