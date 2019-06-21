/*
 * The MIT License
 *
 * Copyright 2016 Russell Gilmore.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package JavaDNS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell Gilmore
 */
public class Client implements Runnable {

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 6052);
            Writer clientWriter;

            clientWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter w = new BufferedWriter(clientWriter);
            BufferedReader reader = new BufferedReader(new FileReader("domains.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                w.write(line + "\n");
            }
            w.flush();
            reader.close();
            InputStream inFromServer = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inFromServer));
            String host;
            while ((host = in.readLine()) != null) {
                System.out.println("Server Says " + host);
            }

        } catch (IOException e) {
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
