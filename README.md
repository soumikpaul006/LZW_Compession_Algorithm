**About the algorithm**

The Lempel–Ziv–Welch (LZW) algorithm is a lossless data compression algorithm. LZW is an
adaptive compression algorithm that does not assume prior knowledge of the input data distribution.
This algorithm works well when the input data is sufficiently large and there is redundancy in the data.
Two examples of commonly used file formats that use LZW compression are the GIF image format
served from websites and the TIFF image format. LZW compression is also suitable for compressing
text files, and is the algorithm in the compress Unix file compression utility.
This algorithm has two steps:

1. Encoding/Compressing
2. Decoding/Decompressing

**Pseudocode for the LZW encoding algorithm:**
```
MAX_TABLE_SIZE=2(bit_length) //bit_length is number of encoding bits
initialize TABLE[0 to 255] = code for individual characters
STRING = null
while there are still input symbols:
    SYMBOL = get input symbol
    if STRING + SYMBOL is in TABLE:
        STRING = STRING + SYMBOL
    else:
        output the code for STRING
        If TABLE.size < MAX_TABLE_SIZE: // if table is not full
            add STRING + SYMBOL to TABLE // STRING + SYMBOL now has a code
        STRING = SYMBOL
output the code for STRING

```
**Pseudocode for the LZW decoding algorithm**

```
MAX_TABLE_SIZE=2(bit_length)
initialize TABLE[0 to 255] = code for individual characters
CODE = read next code from encoder
STRING = TABLE[CODE]
output STRING
while there are still codes to receive:
    CODE = read next code from encoder
    if TABLE[CODE] is not defined: // needed because sometimes the
        NEW_STRING = STRING + STRING[0] // decoder may not yet have code!
    else:
        NEW_STRING = TABLE[CODE]
        output NEW_STRING
    if TABLE.size < MAX_TABLE_SIZE:
        add STRING + NEW_STRING[0] to TABLE
    STRING = NEW_STRING
```
**Programming language used**
java

**Programming language version**
17.0.4.1

**Information on how to run the project**

**First run Encode file**
```
javac Encode.java 
java Encode file_name.txt bitLength
```
After successfully executing the Encode file it will generate input1.lzw file as an output.

**Second run Decode file**
```
javac Decode.java 
java Decode file_name.txt bitLength
```
After successfully executing the Decode file it will generate input1_decoded.txt file
as an output.

**Program design description**

This project consists of two parts encoding/compressing and decoding/decompressing which together executes LZW algorithm.

**Encoding/compressing**

The LZW algorithm reads a series of symbols as input, stringifies the symbols into strings, and then encodes the strings as integer codes.
Compression is made possible since codes take up less room than strings do. The greedy LZW algorithm outputs the longest string for which it has a code after finding it.

**Decoding/Decompressing**

In contrast to compression, decompression transforms integer codes into the strings they stand for.
LZW's decompression procedure is also simple to code, although it takes a little more effort to comprehend.
Also, it has an advantage over static compression techniques because the decoding procedure doesn't require the transmission of a dictionary or any other extraneous data.

**Code Design**

Encode.java

The given Java program implements the LZW compression algorithm for encoding a text file.
It reads the content of the input file, creates a dictionary mapping each character in the ASCII table to a unique integer code, and applies the LZW encoding algorithm to generate a sequence of integer codes representing the compressed data.
The program then writes the encoded data to a new file in binary format. 

Decode.java

This Java program decodes LZW-encoded data, a lossless compression technique used for reducing the size of data files without losing information.
It takes a compressed input file and the bit length of the encoding as command-line arguments, reads the input file, decodes the compressed data using the LZW algorithm, and creates a new file containing the uncompressed output. 
The program initializes a dictionary of 256 entries, updates it during the decoding process, and writes the uncompressed data to a new file.

**File Breakdown**

The program consist of two file encode.java and decode.java. 
The encode.java consist of two function encode, which implements the compressing algorithm and createfile which generate .lzw file.
The decode.java consist of two function decode which implements the decompressing algorithm and createtxtfile which gereate .txt file.

**Data Structure Used**

ArrayList and HashMap

**What works well**

In circumstances where a character sequence repeats itself frequently, the LZW algorithm performs well. 
Text files, specialized file formats like GIF and TIFF, data transport applications, and real-time data compression benefit the most from its effectiveness. 
However not all data kinds or use cases may be appropriate, thus it's important to assess the unique requirements and data features.

**What does not work well**

For LZW to perform compression, the data must be searched for patterns and repetitions.
Data compression will be little to nonexistent if the data is very erratic because there won't be many, if any, patterns to take advantage of.
The output of compression could even in some circumstances be a little bit larger than the input itself.
Only ASCII characters 256 and less are used in the dictionary. The characters used by ASCII are exclusively those in English. 
Thus, it will not be feasible to compress and decompress strings that contain foreign languages.
A broader vocabulary that takes into account additional languages can help to solve this, though.



