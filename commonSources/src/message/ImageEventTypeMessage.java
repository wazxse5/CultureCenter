package message;

public class ImageEventTypeMessage extends Message {
    private static final long serialVersionUID = -5766254320661544031L;
    private final int idEventType;
    private String extension;
    private byte[] image;

    public ImageEventTypeMessage(int idEventType) {
        this.idEventType = idEventType;
    }

    public String getExtension() {
        if (extension == null) return "jpg";
        else return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getIdEventType() {
        return idEventType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
