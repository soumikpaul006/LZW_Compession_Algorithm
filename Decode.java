//author: Soumik Paul
//SID: 801308500
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Decode
{
    public static String input_file=null;
    public static double max_table_size=0.0;
    public static void main(String[] args) throws IOException
    {
        //reading the comment line arguments
        input_file=args[0]; //reading the inpute file
        //String content = new String(Files.readAllBytes(Paths.get(input_file)));
        int bit_length=Integer.parseInt(args[1]);//reading the bit length
        decode(input_file,bit_length);

    }
    public static void decode(String inputFile,int bit_length) throws IOException
    {
        //read file

        Reader inputStreamReader = new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_16BE); // The Charset UTF-16BE is used to read the 16-bit compressed file.
        BufferedReader br = new BufferedReader(inputStreamReader);

        List<Integer> intCode=new ArrayList<>();
        double value=0;

        // reads to the end of the stream
        while((value = br.read()) != -1) {
            intCode.add((int) value);
        }
        br.close();

        System.out.println(intCode);

        /////////////////////////////////////Decode algorithm//////////////////////////////////////
        max_table_size=Math.pow(2,bit_length);

        int table_size=256;

        Map<Integer,String> table=new HashMap<>();
        for(int i=0;i<=255;i++)
        {
            table.put(i, String.valueOf((char) i)); //code for individual character
        }

        int code=intCode.get(0);
        intCode.remove(0);
        String string=table.get(code);
        String new_string=null;
        StringBuffer output=new StringBuffer(string);

        for(int keyCode:intCode)
        {
            if(table_size==keyCode)
            {
                new_string=string+string.charAt(0);
            }
            else if(table.containsKey(keyCode))
            {
                new_string=table.get(keyCode);
            }
            output.append(new_string);
            if(table.size()<max_table_size)
            {
                table.put(table_size++,string+new_string.charAt(0));
            }
            string=new_string;
        }
        //////////////////////////////////////////////////////////////////////////////////////////////

        System.out.print(output);
        createtxtfile(String.valueOf(output));


    }
    public static void createtxtfile(String output) throws IOException
    {
        String outputfilename=input_file.substring(0,input_file.indexOf("."))+"_decoded.txt";

        try{
            //Create BufferWriter
            FileWriter file = new FileWriter(outputfilename);
            BufferedWriter output1=new BufferedWriter(file);
            //Writes data to the file

            output1.write(output);
            System.out.println();

            //Flushed to the output file
            output1.flush();
            System.out.println("Data is flushed to "+outputfilename+" file.");
            output1.close();
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }
}
