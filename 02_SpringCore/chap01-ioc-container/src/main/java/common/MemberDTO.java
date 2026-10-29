package common;

public class MemberDTO {
    private int sequence;
    private String id;
    private String pwd;
    private String name;

    public MemberDTO() {
    }

    public MemberDTO(int sequence, String id, String pwd, String name) {
        this.sequence = sequence;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "sequence=" + sequence +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }
}
