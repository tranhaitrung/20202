package PoolConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws SQLException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(12);
        int number_connection = 10;
        BasicConnectionPool bs = BasicConnectionPool.create();
        System.out.println("SIZE " + bs.getSize());


        for (int i=0;i<12;i++){
            Connection conn = bs.getConnection();
            Thread worker = new WorkThread(latch,""+i);
            worker.start();

        }
        latch.await();
        System.out.println("DONE ALL TASK");
    }
}
