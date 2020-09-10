package com.lt.BIO.Stream;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁先生
 * @Date 2020/9/9
 * @see java.io.PushbackInputStream
 * 它是 FilterInputStream 的子类，是一个处理流，它内部维护了一个缓冲数组buf
 * 在读入字节的过程中可以将读取到的字节数据回退给缓冲区中保存，下次可以再次从缓冲区中读出该字节数据。
 * 所以PushBackInputStream 允许多次读取输入流的字节数据，只要将读到的字节放回缓冲区即可。
 * 应用场景：对数据进行分类规整
 **/
public class PushBachInputStreamTest {


    @Test
    public void readFileClassification() throws IOException {
        try (
                InputStream is = getClass().getResourceAsStream("/PushBackISUser.txt");
                /**
                 *★默认情况下，PushbackInputStream只为unread()单个字符分配足够的空间。
                 * 如果希望能够进行更多的回推，则必须在构造时指定容量。
                 */
                PushbackInputStream pbis = new PushbackInputStream(is, 8);
        ) {
            List<Integer> numberList = new ArrayList<>();
            List<String> characterList = new ArrayList<>();
            byte[] buffer = new byte[8];
            pbis.read(buffer);
            System.out.println(new String(buffer));
            System.out.println("-------");
            pbis.unread(buffer);
            int len = 0;
            while (-1 != (len = pbis.read(buffer))) {
                System.out.println(new String(buffer));
            }
        }

    }

}
