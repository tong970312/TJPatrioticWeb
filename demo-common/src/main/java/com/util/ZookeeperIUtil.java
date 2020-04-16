package com.util;

import com.example.demo.dao.entity.UserInfo;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
public class ZookeeperIUtil implements Watcher{
    static final String zkHost = "123.57.133.58:2181";

    private ZooKeeper zookeeper;
    private static final int SESSION_TIME_OUT = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    @Override
    public void process(WatchedEvent event) {

        if (event.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event");
            countDownLatch.countDown();
        }
    }

    public Boolean inputZk(Map<String, UserInfo> userInfoMap) throws Exception {
        ZookeeperIUtil zookeeperIUtil = new ZookeeperIUtil();
        zookeeperIUtil.connectZookeeper(zkHost);
        System.out.println("======"+zookeeperIUtil.setData("/zookeeper/userData",userInfoMap.toString()));
        System.out.println(zookeeperIUtil.getData("/zookeeper/userData"));
        return true;
    }

    public static void main(String[] args) throws Exception {
        ZookeeperIUtil zookeeperIUtil = new ZookeeperIUtil();
        zookeeperIUtil.inputZk(null);
    }

    /**连接zookeeper
     * @param host
     * @throws Exception
     */
    public void connectZookeeper(String host) throws Exception{
        zookeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zookeeper connection success");
    }

    /**
     * 创建节点
     * @param path
     * @param data
     * @throws Exception
     */
    public String createNode(String path,String data) throws Exception{
        return this.zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }



    /**
     * 获取节点上面的数据
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getData(String path) throws KeeperException, InterruptedException{
        byte[] data = zookeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    /**
     * 设置节点信息
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat setData(String path,String data) throws KeeperException, InterruptedException{
        Stat stat = zookeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    /**
     * 删除节点
     * @param path
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void deleteNode(String path) throws InterruptedException, KeeperException{
        zookeeper.delete(path, -1);
    }

    /**
     * 关闭连接
     * @throws InterruptedException
     */
    public void closeConnection() throws InterruptedException{
        if (zookeeper != null) {
            zookeeper.close();
        }
    }
}
