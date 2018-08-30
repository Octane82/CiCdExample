package com.everlapp.cicdexample.repositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileReader {

    private final File file;

    public FileReader(File file) {
        this.file = file;
    }

    public String readFile() throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, Charset.defaultCharset());
    }

}
