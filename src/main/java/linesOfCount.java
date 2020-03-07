import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class linesOfCount{

    private static MessageDigest messageDigest;

    public static void main(String args[]) throws IOException, NoSuchAlgorithmException {

        int blankLines = 0;
        int commentLines = 0;
        int codeLines = 0;
        int totalLines = 0;
        int totalNumberOfJavaFiles = 0;
        int uniqueFiles = 0;
        String fullPath= args[0];

        List<String> files = new ArrayList<String>();

        try (Stream<Path> listFolder = Files.walk(Paths.get(fullPath))) {
            files = listFolder.map(x -> x.toString()).filter(f -> f.endsWith(".java")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, String> HashMapListForFiles = new HashMap<>();
        messageDigest = MessageDigest.getInstance("SHA-512");

        for (int i = 0; i < files.size(); i++) {
            FileInputStream fileInput = new FileInputStream(files.get(i));
            File file = new File(files.get(i));
            byte fileData[] = new byte[(int) file.length()];
            fileInput.read(fileData);
            fileInput.close();
            String hashVal = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
            if (HashMapListForFiles.containsKey(hashVal)) {
                continue;
            } else {

                HashMapListForFiles.put(hashVal, files.get(i));
            }
        }
        uniqueFiles = HashMapListForFiles.size();
        Iterator hashMapIterator = HashMapListForFiles.entrySet().iterator();

        while (hashMapIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hashMapIterator.next();
            totalNumberOfJavaFiles++;
            String name = (String) mapElement.getValue();
            File f1 = new File(name);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                totalLines++;
                if (s.trim().isEmpty()) {
                    blankLines++;
                } else if (!(s.trim().isEmpty())) {
                    s = s.trim();
                    int length = s.length();
                    if (length > 1) {
                        String ini = s.substring(0, 2);
                        if (ini.equals("/*")) {
                            commentLines++;
                            while (!(s.contains("*/"))) {
                                commentLines++;
                                totalLines++;
                                s = br.readLine();
                            }
                        } else if (ini.equals("//")) {
                            commentLines++;
                        } else
                            codeLines++;
                    } else
                        codeLines++;
                }
            }
            fr.close();
        }

        System.out.println(
                totalNumberOfJavaFiles + "-" + uniqueFiles + "-" + blankLines + "-" + commentLines + "-" + codeLines);

    }
}