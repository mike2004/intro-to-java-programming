package ch_17;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * *17.3 (Sum all the integers in a binary data file) Suppose a binary data file named
 * Exercise17_03.dat has been created and its data are created using
 * writeInt(int) in DataOutputStream. The file contains an unspecified
 * number of integers. Write a program to find the sum of the integers
 *
 * @author Harry Dulaney
 */
public class E1703 {
    public static void main(String[] args) {

        String filePath = "src/ch_17/Exercise17_03.dat";
        if (!(new File(filePath).exists())) {
            try (FileOutputStream fis = new FileOutputStream(filePath)) {
                DataOutputStream dos = new DataOutputStream(fis);
                runCreateTestDatFile(dos);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getLocalizedMessage());
            }

        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            DataInputStream dis = new DataInputStream(fis);

            int sum = 0;
            int numIntegers = dis.available() / 4; // An integer is stored as 4 bytes

            System.out.print("The sum of: ");
            for (int i = 0; i < numIntegers; i++) {
                int n = dis.readInt();
                if (i == 0) {
                    System.out.print(n + "");
                } else {
                    System.out.print(" + " + n);
                }
                sum += n;
            }
            System.out.println(" = " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void runCreateTestDatFile(DataOutputStream dos) throws IOException {
        for (int i = 0; i < 10; i++) {
            int n = (int) (1 + Math.random() * 10);
            dos.writeInt(n);
        }
        dos.flush();
        dos.close();

    }
}