package com.finger.fpt;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FPT5校验结果
 */
public class FPT5ValidResult {
    /**
     * 校验结果描述
     */
    String msg;
    /**
     * 文件头信息
     */
    String headMsg;
    /**
     * 是否成功
     */
    boolean isSuccess;
    /**
     * 捺印信息
     */
    Map<String,List<String>> tpMsg = new HashMap<>();
    /**
     * 案件信息
     */
    Map<String,List<String>> lpMsg = new HashMap<>();

    /**
     * 现场指掌纹查询比对请求信息
     */
    Map<String,List<String>> latentTaskPackageMsg = new HashMap<>();
    /**
     * 捺印指掌纹查询比对请求信息
     */
    Map<String,List<String>> printTaskPackageMsg = new HashMap<>();
    /**
     * 查重比中信息
     */
    Map<String,List<String>> ttHitResultPackageMsg = new HashMap<>();
    /**
     * 正查/倒查比中信息
     */
    Map<String,List<String>> ltHitResultPackageMsg = new HashMap<>();
    /**
     * 串查比中信息
     */
    Map<String,List<String>> llHitResultPackageMsg = new HashMap<>();

    public Map<String, List<String>> getTpMsg() {
        return tpMsg;
    }

    public void setTpMsg(Map<String, List<String>> tpMsg) {
        this.tpMsg = tpMsg;
    }

    public Map<String, List<String>> getLpMsg() {
        return lpMsg;
    }

    public void setLpMsg(Map<String, List<String>> lpMsg) {
        this.lpMsg = lpMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHeadMsg() {
        return headMsg;
    }

    public void setHeadMsg(String headMsg) {
        this.headMsg = headMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Map<String, List<String>> getLatentTaskPackageMsg() {
        return latentTaskPackageMsg;
    }

    public void setLatentTaskPackageMsg(Map<String, List<String>> latentTaskPackageMsg) {
        this.latentTaskPackageMsg = latentTaskPackageMsg;
    }

    public Map<String, List<String>> getPrintTaskPackageMsg() {
        return printTaskPackageMsg;
    }

    public void setPrintTaskPackageMsg(Map<String, List<String>> printTaskPackageMsg) {
        this.printTaskPackageMsg = printTaskPackageMsg;
    }

    public Map<String, List<String>> getTtHitResultPackageMsg() {
        return ttHitResultPackageMsg;
    }

    public void setTtHitResultPackageMsg(Map<String, List<String>> ttHitResultPackageMsg) {
        this.ttHitResultPackageMsg = ttHitResultPackageMsg;
    }

    public Map<String, List<String>> getLtHitResultPackageMsg() {
        return ltHitResultPackageMsg;
    }

    public void setLtHitResultPackageMsg(Map<String, List<String>> ltHitResultPackageMsg) {
        this.ltHitResultPackageMsg = ltHitResultPackageMsg;
    }

    public Map<String, List<String>> getLlHitResultPackageMsg() {
        return llHitResultPackageMsg;
    }

    public void setLlHitResultPackageMsg(Map<String, List<String>> llHitResultPackageMsg) {
        this.llHitResultPackageMsg = llHitResultPackageMsg;
    }

    public String defautlToString(String enteyKey) {
        StringBuffer result = new StringBuffer();
        if(this.headMsg != null){
            result.append("==================文件头信息==================").append(enteyKey);
            result.append(this.headMsg).append(enteyKey);
        }
        if(tpMsg != null){
            for(String personId : tpMsg.keySet()){
                result.append("==================捺印信息【").append(personId).append("】==================").append(enteyKey);
                for(String msg : tpMsg.get(personId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        if(lpMsg != null){
            for(String caseId : lpMsg.keySet()){
                result.append("==================案件信息【").append(caseId).append("】==================").append(enteyKey);
                for(String msg : lpMsg.get(caseId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        if(latentTaskPackageMsg != null){
            for(String taskId : latentTaskPackageMsg.keySet()){
                result.append("==================现场指掌纹查询比对请求信息【").append(taskId).append("】==================").append(enteyKey);
                for(String msg : latentTaskPackageMsg.get(taskId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        if(ttHitResultPackageMsg != null){
            for(String taskId : ttHitResultPackageMsg.keySet()){
                result.append("==================指掌纹查重比中信息【").append(taskId).append("】==================").append(enteyKey);
                for(String msg : ttHitResultPackageMsg.get(taskId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        if(ltHitResultPackageMsg != null){
            for(String taskId : ltHitResultPackageMsg.keySet()){
                result.append("==================指掌纹正查和倒查比中信息【").append(taskId).append("】==================").append(enteyKey);
                for(String msg : ltHitResultPackageMsg.get(taskId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        if(llHitResultPackageMsg != null){
            for(String taskId : llHitResultPackageMsg.keySet()){
                result.append("==================指掌纹串查比中信息【").append(taskId).append("】==================").append(enteyKey);
                for(String msg : llHitResultPackageMsg.get(taskId)){
                    result.append(msg).append(enteyKey);
                }
            }
        }
        return result.toString();
    }
}
