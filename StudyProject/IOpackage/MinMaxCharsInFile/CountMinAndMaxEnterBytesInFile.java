package IOpackage.MinMaxCharsInFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountMinAndMaxEnterBytesInFile {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInput = new FileInputStream(reader.readLine());
        reader.close();
        ArrayList<Integer> filebytes = new ArrayList<>();
        while(fileInput.available() != 0)
        {
            filebytes.add(fileInput.read());
        }
        fileInput.close();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        int q = 0;
        for (Integer i : filebytes)
        {
            if(!map.containsKey(i))
            {
                for (Integer j : filebytes)
                {
                    if (j.equals(i))
                    {
                        q++;
                    }
                }
                if(q > 1)
                {
                    map.put(i, q);
                    q = 0;
                }
                else q = 0;
            }
        }
        System.out.println(filebytes);
        System.out.println(map);
        filebytes.clear();
        int max = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (pair.getValue() > max) max = pair.getValue();
        }
        int min = 0;
        for (int i = max; i > 1 ; i--)
        {
            for (Map.Entry<Integer, Integer> pair : map.entrySet())
            {
                if (pair.getValue() == i)min = pair.getValue();
            }
        }
        for (Map.Entry<Integer, Integer> pair : map.entrySet())
        {
            if (pair.getValue() == min) filebytes.add(pair.getKey());
        }
        for (int i = 0;i < filebytes.size();i++)
        {
            System.out.print(filebytes.get(i));
            if (i != filebytes.size() - 1)System.out.print(" - ");
        }
    }
}
