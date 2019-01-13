package message;


public class AddInfoRequestMessage extends Message {
    private static final long serialVersionUID = 577111173596981704L;


    private String info;

    public AddInfoRequestMessage(String info ){
      this.info=info;
    }


    public String getInfo() {
        return info;
    }


}
