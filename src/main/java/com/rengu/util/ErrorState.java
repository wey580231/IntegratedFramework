package com.rengu.util;

/**
 * 异常类型类
 * Created by wey580231 on 2017/6/15.
 */
public final class ErrorState {

    //异常状态
    public static final Integer ERROR_UNSOLVED = 1;           //异常未处理
    public static final Integer ERROR_APS_PROCESS = 2;       //aps处理中
    public static final Integer ERROR_APS_FINISH = 3;        //aps处理完成
    public static final Integer ERROR_ADJUSTED = 4;          //调整完成，此状态不是必须，但通过调整优化后，可以更新至此状态
    public static final Integer ERROR_FINISH = 5;            //已下发MES处理，该异常处理完成
}
