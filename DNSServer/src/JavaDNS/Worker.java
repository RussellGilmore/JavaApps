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

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell Gilmore
 */
public class Worker implements Runnable {

    private final Socket clientSocket;
    private final Reader clientReader;
    private final Writer clientWriter;

    public Worker(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        clientReader = new InputStreamReader(clientSocket.getInputStream());
        clientWriter = new OutputStreamWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            BufferedWriter w = new BufferedWriter(clientWriter);
            BufferedReader r = new BufferedReader(clientReader);

            while (r.ready()) {
                String host = r.readLine();
                System.out.println("Client sent: " + host);
                w.write(InetAddress.getByName(host).toString() + '\n');
            }
            w.flush();

        } catch (IOException russell) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, russell);

        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
