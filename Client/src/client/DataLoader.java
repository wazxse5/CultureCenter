package client;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import message.ImageEventTypeMessage;

import java.io.*;
import java.util.*;

public class DataLoader {
    private ThreadClient threadClient;
    private String userDir;
    private File fileDir;

    private List<Integer> recommendation;
    private Map<Integer, ObjectProperty<Image>> imagesProperty;

    public DataLoader(ThreadClient threadClient) {
        this.threadClient = threadClient;
        recommendation = new ArrayList<>(Collections.nCopies(8, -1));
        userDir = System.getProperty("user.home");
        fileDir = new File(userDir, ".aSZOK");
        if (!fileDir.exists()) fileDir.mkdir();

        imagesProperty = new HashMap<>();
        File[] files = fileDir.listFiles();
        if (files != null) {
            for (File f : files) {
                String extension = "";
                int i = f.getName().lastIndexOf('.');
                if (i > 0) extension = f.getName().substring(i+1);
                if (extension.equals("jpg")) {
                    Image image = new Image(String.valueOf(f.toURI()));
                    int idEventType = Integer.parseInt(f.getName().substring(0, i));
                    ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>(image);
                    imagesProperty.put(idEventType, imageProperty);
                }
                if (extension.equals("txt")) {
                    try {
                        Scanner scanner = new Scanner(f);
                        int counter = 0;
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (!line.isEmpty()) recommendation.set(counter++, Integer.valueOf(line));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void saveImageEventType(ImageEventTypeMessage message) {
        String fullName = message.getIdEventType() + "." + message.getExtension();
        File fileImage = new File(fileDir, fullName);

        try (FileOutputStream stream = new FileOutputStream(fileImage)) {
            stream.write(message.getImage());
            imagesProperty.get(message.getIdEventType()).setValue(new Image(String.valueOf(fileImage.toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectProperty<Image> getImageProperty(int idEventType) {
        if (!imagesProperty.containsKey(idEventType)) {
            Image defaultImage = new Image(String.valueOf(getClass().getResource("/../commonSources/images/no-image.jpg")));
            ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>(defaultImage);
            imagesProperty.put(idEventType, imageProperty);
        }
        return imagesProperty.get(idEventType);
    }

    public List<Integer> getRecommendation() {
        return recommendation;
    }

    public void setFileDir(File fileDir) {
        this.fileDir = fileDir;
    }

    public void setRecommendationValues(List<Integer> recommendation) {
        for (int i = 0; i < recommendation.size(); i++) {
            this.recommendation.set(i, recommendation.get(i));
        }
        threadClient.getViewManager().getRecommendationViewController().rebindImageViews();
        File recommendedFile = new File(fileDir, "recommended.txt");
        try (PrintWriter out = new PrintWriter(recommendedFile)) {
            for (int r : recommendation) out.println(r);
        } catch (IOException e) {

        }
    }
}
