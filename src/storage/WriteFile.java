package storage;

import model.Staff;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class WriteFile<T> {
    private String filePath;

    public WriteFile(String filePath) {
        this.filePath = filePath;
    }

    public void write(ArrayList<T> objects) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void write(Staff objects) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(objects.getId() + " " + objects.getTel() + "\n");
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
