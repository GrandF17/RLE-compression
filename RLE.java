import java.io.*;
import java.util.*;

public class RLE {

    public RLE() { }


    public String compress(InputStream in, OutputStream out) {
        try {
            byte count = 1;
            byte negative_count = 0;
            int bite = in.read();
            ArrayList<Integer> a = new ArrayList<>();//to collect all our non-recurring bites
            while (bite != -1) {
                int next = in.read();
                if (count == 127 || next != bite) {
                    if (count > 1 || negative_count == -128) {
                        if(!a.isEmpty()) {
                            out.write(negative_count);
                            for (Integer integer : a) { out.write(integer); }
                            negative_count = 0;
                            a.clear();
                        }
                        if(count > 1) {
                            out.write(count);
                            out.write(bite);
                            count = 1;
                        } else {
                            a.add(bite);
                            negative_count--;
                        }
                    } else {
                        a.add(bite);
                        negative_count--;
                    }
                    bite = next;
                } else { count++; }
            }

            if(!a.isEmpty()) {
                out.write(negative_count);
                for (Integer integer : a) { out.write(integer); }
                a.clear();
            }
            return ("Complete!");
        } catch (IOException e) {
            e.printStackTrace();
            return ("Fail!");
        }
    }

    public String decompress(InputStream in, OutputStream out) {
        try {
            int count = in.read();
            while (count != -1) {
                int bite;
                while((byte) count < 0) {
                    bite = in.read();
                    out.write(bite);
                    count++;
                }
                if((byte) count > 0) {
                    bite = in.read();
                    for (int i = 0; i < count; i++)
                        out.write(bite);
                }
                count = in.read();
            }
            return ("Complete!");
        } catch (IOException e) {
            e.printStackTrace();
            return ("Fail!");
        }
    }

    /*class WriteBuffer {
        int pos;
        long buffer;
        OutputStream out;

        WriteBuffer(OutputStream out) {
            this.out = out;
        }

        public void write(int n, int m) throws IOException {
            buffer = (buffer << n) + m % (1 << n);
            pos = pos + n;
            while (pos >= 8) {
                out.write((int) (buffer >> (pos - 8)));
                pos = pos - 8;
            }
        }

        public void flush() throws IOException {
            write((8 - pos % 8) % 8, 0);
        }
    }*/
}