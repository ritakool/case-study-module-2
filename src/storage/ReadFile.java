package storage;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadFile<T> {
    private final String filePath;

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<T> read() {
        ArrayList<T> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (ArrayList<T>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Lỗi chưa có dữ liệu hoặc không có file");
        }
        return data;
    }
    public Map<String, String> read2() {
        Map<String, String> userMap = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                userMap.put(parts[0], parts[1]);
            }
            reader.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Lỗi chưa có dữ liệu hoặc không có file");
        }
        return userMap;
    }
}