/*
 * The MIT License
 *
 * Copyright 2017 Russell Gilmore.
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
package backup.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell Gilmore
 */
public final class Timer {

    final int minutes;
    final Path srcDir;
    final Path dstDir;
    private final ScheduledExecutorService scheduler;

    public Timer(int minutes, Path src, Path dst) {
        this.minutes = minutes;
        this.srcDir = src;
        this.dstDir = dst;
        this.scheduler = Executors.newScheduledThreadPool(1);
        copyFile();
    }

    public void copyFile() {
        Runnable intervalBackup = () -> {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            Path stampedFolder = Paths.get(dstDir.toAbsolutePath().toString() +"\\" + timeStamp);
            System.out.println("Stamped Folder " + stampedFolder.toString());
//            try {
//                Files.createDirectory(stampedFolder);
//            } catch (IOException ex) {
//                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            try {
                Files.walk(srcDir)
                        .forEach((src) -> {
                            try {
                                final Path relativePath = srcDir.relativize(src);
                                final Path dst = stampedFolder.resolve(relativePath);
                                //System.out.println(src + ": " + relativePath + " -> " + dst);
                                if (Files.exists(dst)) {
                                    return;
                                }
                                
                                if (Files.isDirectory(src)) {
                                    Files.createDirectory(dst);
                                    return;
                                }
                                
                                Files.copy(src, dst);
                            } catch (IOException ex) {
                                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
            } catch (IOException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        final ScheduledFuture<?> beeperHandle = this.scheduler.scheduleAtFixedRate(intervalBackup, this.minutes, this.minutes, MINUTES);
        

    }

}
