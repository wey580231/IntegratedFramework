package com.rengu.util;

//创建快照线程
public class BackupThread implements Runnable {

    public static final int Reset_Activex = 1;                  //恢复成功后，调用APS的可视化接口进行交互控件的恢复
    public static final int Recover_Snapshot = 2;               //恢复快照
    public static final int Query_Order_State = 3;              //查询是否存在订单被取消的情况

    private String bottomShotId;
    private int operateState;

    public BackupThread(int operateState) {
        this.operateState = operateState;
    }

    public BackupThread(int operateState, String bottomShotId) {
        this.operateState = operateState;
        this.bottomShotId = bottomShotId;
    }

    public void run() {

        int apsState = ApsTools.BUSY;
        int queryCount = 0;

        while (queryCount < 10 && apsState == ApsTools.BUSY) {

            apsState = ApsTools.instance().queryExecuteState();

            if (operateState == Reset_Activex) {
                ApsTools.instance().resetInteActivex();
            } else if (operateState == Recover_Snapshot) {
                if (apsState == ApsTools.IDLE) {
                    ApsTools.instance().createApsSnapshot(bottomShotId);
                }
            } else if (operateState == Query_Order_State) {
                if (apsState == ApsTools.IDLE) {
                    ApsTools.instance().getInterAdjust();
                }
            }

            MyLog.getLogger().info("======线程====" + apsState);

            queryCount++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
