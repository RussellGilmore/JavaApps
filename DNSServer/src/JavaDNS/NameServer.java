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

import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell Gilmore
 */
public class NameServer implements Runnable {

    ExecutorService pool;

    final ServerSocket server;
    private boolean willClose;

    public NameServer() throws IOException {
        server = new ServerSocket(6052);
        pool = Executors.newCachedThreadPool();

    }

    @Override
    public void run() {
        while (!willClose) {
            try {
                Socket clientSocket = server.accept();
                System.out.println("Got a connection: " + clientSocket.toString());
                Worker work = new Worker(clientSocket);
                pool.submit(work);

            } catch (IOException ex) {
                Logger.getLogger(NameServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            close();
        } catch (IOException ex) {
            Logger.getLogger(NameServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() throws IOException {
        server.close();
        pool.shutdown();
    }

    public void serverDo() {

    }

}
