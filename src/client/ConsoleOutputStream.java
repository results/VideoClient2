package client;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.swing.JList;

public class ConsoleOutputStream extends PrintStream {

    private ArrayList<String> list;
    @SuppressWarnings("unused")
    private JList<?> jlist;

    //private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

    public ConsoleOutputStream(OutputStream out, ArrayList<String> list, JList<?> jlist) {
        super(out);
        this.list = list;
        this.jlist = jlist;
    }

    public void onCall(String str) {
        list.add(String.valueOf(str));
    }

    @SuppressWarnings("unused")
    public void println(Object obj) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat day = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String str = ("[" + day.format(date) + "] " + String.valueOf(obj));
        onCall(str);
       // super.println(ses);
    }
}