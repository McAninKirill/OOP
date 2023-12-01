package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class FindSubstr {
    static public int bufferSize = 1024;

    static public List<Integer> findSubstr(String subStr, String filename){


        List<Integer> ans = new ArrayList<>();
        int lastIndexPosition;
        int prevConcatCache = 0;
        String previousStr = "";

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classloader.getResourceAsStream(filename);) {
            InputStreamReader streamreader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufread = new BufferedReader(streamreader);

            char[] buffer = new char[bufferSize];

            while (bufread.read(buffer, 0, bufferSize) > 0) { // Returns an estimate of the number of remaining bytes that can be read
                String content = "";
                int gotFromPrev = 0;
                if (previousStr.length() - subStr.length() + 1 > 0) {
                    content = content.concat(previousStr.substring(previousStr.length() - subStr.length() + 1));
                    gotFromPrev = subStr.length() - 1;
                } else {
                    content = content.concat(previousStr);
                    gotFromPrev = previousStr.length();
                }

                // reading a buffer of text

                StringBuilder strbild = new StringBuilder();
                strbild.append(new String(buffer));
                content = content.concat(strbild.toString());

                if (!content.isEmpty()) {
                    lastIndexPosition = content.lastIndexOf(subStr);

                    while (lastIndexPosition != -1) {
                        ans.add(prevConcatCache + lastIndexPosition - gotFromPrev);
                        lastIndexPosition = content.lastIndexOf(subStr, lastIndexPosition - 1);
                    }
                }
                prevConcatCache += bufferSize;
                previousStr = content;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(ans);
        return ans;
    }
}

