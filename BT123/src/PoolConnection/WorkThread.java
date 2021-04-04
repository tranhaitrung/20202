package PoolConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public class WorkThread extends Thread {
    private String taskName;
    public CountDownLatch latch;

    public WorkThread(CountDownLatch latch, String taskName){
        this.latch = latch;
        this.taskName = taskName;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + "is starting. " +taskName);
        excute();
        //latch.countDown();
        System.out.println(Thread.currentThread().getName() + " FINISH!!");
    }

    private void excute(){
        try {
            String sql = "select count(code) as total from city";
            Connection conn = BasicConnectionPool.create().getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            Thread.sleep(2000);
            resultSet.next();
            System.out.println("Task : "+taskName + ": Run MSQL : "+ resultSet.getInt("total"));

        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }


}
