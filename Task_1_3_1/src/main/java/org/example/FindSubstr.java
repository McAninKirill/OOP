package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class FindSubstr {
    private int bufferSize = 1024;
    private final int Limit = 1000000;
    private String filename;

    public FindSubstr(String filename){
        this.filename = filename;
    }

    public List<Integer> findSubstr(String subStr) throws IOException{


        List<Integer> ans = new ArrayList<>();
        int lastIndexPosition;
        int prevConcatCache = 0;
        String previousStr = "";

        try (FileInputStream inputStream = new FileInputStream(this.filename)) {

            while (inputStream.available() > 0) { // Returns an estimate of the number of remaining bytes that can be read
                String content = "";
                int gotFromPrev = 0;
                if (previousStr.length() - subStr.length() + 1 > 0) {
                    content = content.concat(previousStr.substring(
                            previousStr.length() - subStr.length() + 1));
                    gotFromPrev = subStr.length() - 1;
                }

                // reading a buffer of text
                byte[] buffer = new byte[this.bufferSize];
                StringBuilder strbild = new StringBuilder();
                inputStream.read(buffer);
                strbild.append(new String(buffer));
                content = content.concat(strbild.toString());

                if (!content.isEmpty()) {
                    lastIndexPosition = content.lastIndexOf(subStr);
                    if (ans.size() >= this.Limit) {
                        while (lastIndexPosition != -1) {
                            lastIndexPosition = content.lastIndexOf(subStr, lastIndexPosition - 1);
                        }
                    } else {
                        while (lastIndexPosition != -1) {
                            ans.add(prevConcatCache + lastIndexPosition - gotFromPrev);
                            lastIndexPosition = content.lastIndexOf(subStr, lastIndexPosition - 1);
                        }
                    }
                }
                prevConcatCache += this.bufferSize;
                previousStr = content;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(ans);
        return ans;
    }
}

