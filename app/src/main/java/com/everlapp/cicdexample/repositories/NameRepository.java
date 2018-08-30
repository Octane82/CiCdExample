package com.everlapp.cicdexample.repositories;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * Class for test V1
 */
public class NameRepository {

    private final FileReader fileReader;

    public NameRepository(FileReader fileReader) {
        this.fileReader = fileReader;
    }




    public String getName() throws IOException {
        Gson gson = new Gson();
        User user = gson.fromJson(fileReader.readFile(), User.class);
        return user.name;
    }



    /*public String readFile() throws FileNotFoundException {
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream in = new FileInputStream(file);
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, Charset.defaultCharset());
    }*/

}
