//author: Soumik Paul
//SID: 801308500
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Encode
{
    public static String input_file=null;
    public static void main(String[] args) throws IOException
    {
        //reading the command line arguments
        input_file=args[0]; //reading the file name
        String content = new String(Files.readAllBytes(Paths.get(input_file)));
        int bit_length=Integer.parseInt(args[1]);// reading the bit length
        encode(content,bit_length);

    }
    public static void encode(String content,double bit_length) throws IOException {
        // Mapping each charater with its respective code
        Map<String, Integer> table = new HashMap<String, Integer>();
        for (int i = 0; i < 255; i++) {
            table.put("" + (char) i, i);
        }
        // System.out.println(table);
        // System.out.print(content);

        /////////////////////////////////Encode Algorithm//////////////////////////////////////

        double max_table_size = Math.pow(2, bit_length);
        String str = "";
        double table_size = 256;

        List<Integer> code = new ArrayList<Integer>();

        for (char symbol : content.toCharArray()) {
            String str_symbol = str + symbol;
            if (table.containsKey(str_symbol)) {
                str = str_symbol;
            } else {
                code.add(table.get(str));
                if (table_size < max_table_size) // if table is not full
                {
                    table.put(str_symbol, (int) table_size++); // code of str+symbol
                }
                str = "" + symbol;
            }

        }
        //adding the code of last character
        if (!str.equals("")) {
            code.add(table.get(str));
        }
        ////////////////////////////////////////////////////////////////////////////////////////

        System.out.println(code);
        createfile(code);
    }

    public static void createfile(List<Integer> encode_list) throws IOException
    {
        String outputfilename=input_file.substring(0,input_file.indexOf("."))+".lzw";

        try{
            //Create BufferWriter

            BufferedWriter output=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfilename), StandardCharsets.UTF_16BE));

            //Writes data to the file

            for(int i=0;i<encode_list.size();i++)
            {
                output.write((encode_list.get(i)));
            }

            //Flushed to the output file
            output.flush();
            System.out.println("Data is flushed to the "+outputfilename+" file.");
            output.close();
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

}
