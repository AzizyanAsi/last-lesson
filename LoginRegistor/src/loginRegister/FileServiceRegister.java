package loginRegister;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileServiceRegister {
    public static void createFolder(String path){
        File file=new File(path);
        file.mkdir();

    }
    public static void createFile(String path ,String name) throws IOException {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(path + File.separator + name);
        file.createNewFile();

    }
    public static void write(String path,String text) throws IOException {

        Files.write(Paths.get(path),text.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.WRITE);
    }
    public static List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void writeToFile(User user){
        StringBuilder sb=new StringBuilder();
        try {
            if(!(read("files\\register.txt").isEmpty())){
            sb. append("\n");
            }
            sb
                    .append(user.fullName)
                    .append(",")
                    .append(user.username)
                    .append(",")
                    .append(user.email)
                    .append(",")
                    .append(user.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileServiceRegister.write("files\\register.txt",sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
