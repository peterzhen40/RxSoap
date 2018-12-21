package cn.peterzhen.rxsoap.net;

import com.google.gson.JsonParseException;

import android.text.TextUtils;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.HttpException;

/**
 * 当前类注释:
 *
 * @author zhenyanjun
 * @date 2018/12/21 17:03
 */
public class ErrorUtils {

    public enum SYSTEM {MINBAO, JDYZB}

    /**
     * 民爆类错误码
     */
    public static HashMap<String, String> sMbErrorMap = new HashMap<String, String>();
    /**
     * 剧毒易制爆类错误码
     */
    public static HashMap<String, String> sJdErrMap = new HashMap<String, String>();

    static {
        /**=======================================民爆========================================================*/
        sMbErrorMap.put("err-9999", "连接服务器失败,不会出现在返回信息内,暂未使用");
        sMbErrorMap.put("err-0000", "服务器错误,一般有跟具体说明");
        sMbErrorMap.put("err-1111", "未找到相关信息");
        sMbErrorMap.put("err-1112", "单位id不能为空");
        sMbErrorMap.put("err-1113", "记录不存在");

        sMbErrorMap.put("err-1001", "用户名不能为空");
        sMbErrorMap.put("err-1002", "密码不能为空");
        sMbErrorMap.put("err-1003", "用户不存在");
        sMbErrorMap.put("err-1004", "密码不正确");
        sMbErrorMap.put("err-1005", "用户被锁定,禁止登录");
        sMbErrorMap.put("err-1006", "用户过期");
        sMbErrorMap.put("err-1007", "用户未对应任何单位");
        sMbErrorMap.put("err-1008", "用户所属单位信息不存在");
        sMbErrorMap.put("err-1009", "用户所属单位被锁定");

        sMbErrorMap.put("err-1100", "SN已失效,请注销之后重新登录");
        sMbErrorMap.put("err-1101", "设备代码错误");
        sMbErrorMap.put("err-1102", "APP被禁止使用");

        sMbErrorMap.put("err-1500", "要审核的项目类型不能为空");
        sMbErrorMap.put("err-1501", "节点名不能为空");
        sMbErrorMap.put("err-1502", "记录id不能为空");
        /************** 作业监理日志或施工日志 *****************/
        sMbErrorMap.put("err-1200", "施工单位id或者名称不能为空");
        sMbErrorMap.put("err-1201", "填报日期不能为空");
        sMbErrorMap.put("err-1202", "爆破项目id或者项目名称不能为空");
        sMbErrorMap.put("err-1203", "天气情况不能为空");
        sMbErrorMap.put("err-1204", "工程部位不能为空");
        sMbErrorMap.put("err-1205", "项目管辖机关code不能为空");
        sMbErrorMap.put("err-1206", "现场监理人员不能为空"); // 施工单位规划负责人不能为空(监理日志) 或
        // 施工规划负责人不能为空(施工日志)
        sMbErrorMap.put("err-1207", "工程技术人员不能为空"); // 监理单位负责监督的监理人不能为空(监理日志) 或
        // 现场监理人员不能为空(施工日志)
        sMbErrorMap.put("err-1208", "爆破员不能为空"); // 监理单位项目技术负责人不能为空(监理日志) 或
        // 爆破现场警戒人员不能为空(施工日志)
        sMbErrorMap.put("err-1209", "安全员不能为空");
        sMbErrorMap.put("err-1210", "业主单位负责人不能为空");
        sMbErrorMap.put("err-1211", "使工程进度不能为空");
        sMbErrorMap.put("err-1212", "作业单位人员动态不能为空");
        sMbErrorMap.put("err-1213", "爆破作业人员是否持证上岗不能为空(0 是,1 否)");
        sMbErrorMap.put("err-1214", "是否按照爆破设计方案进行不能为空(0 是,1 否)");
        sMbErrorMap.put("err-1215", "基本情况不能为空");
        sMbErrorMap.put("err-1216", "清退民爆物品不能为空(当是否清退民爆物品值为 0 时)");
        sMbErrorMap.put("err-1217", "流失民爆物品不能为空(当是否流失民爆物品值为 0 时)");
        sMbErrorMap.put("err-1218", "盲炮处理方式不能为空(当是否有盲炮值为 0 时)");
        sMbErrorMap.put("err-1219", "安全事故处理方式不能为空(当是否发生安全事故值为 0 时)");
        sMbErrorMap.put("err-1220", "监理单位id或者名称不能为空");
        sMbErrorMap.put("err-1221", "项目地址不能为空");
        sMbErrorMap.put("err-1222", "项目类别不能为空");
        sMbErrorMap.put("err-1223", "项目类型不能为空(1控制爆破,0非控制爆破)");
        sMbErrorMap.put("err-1224", "施工规划负责人不能为空");
        sMbErrorMap.put("err-1225", "爆破现场警戒不能为空");
        sMbErrorMap.put("err-1226", "使用炸药数量不能为空");
        sMbErrorMap.put("err-1227", "使用雷管数量不能为空");
        sMbErrorMap.put("err-1228", "使用索类数量不能为空");
        sMbErrorMap.put("err-1229", "爆破现场警戒负责人不能为空");
        sMbErrorMap.put("err-1230", "作业安全警戒距离不能为空");
        sMbErrorMap.put("err-1231", "作业时间范围不能为空");
        sMbErrorMap.put("err-1232", "放炮时间不能为空");

        /************** 现场安全检查 *****************/
        sMbErrorMap.put("err-1300", "公安账户不存在或密码错误");
        sMbErrorMap.put("err-1301", "公安机关用户名不能为空");
        sMbErrorMap.put("err-1302", "公安机关用户密码不能为空");
        sMbErrorMap.put("err-1303", "输入的用户名密码错误");
        sMbErrorMap.put("err-1304", "检查机关id与名称不能为空");
        sMbErrorMap.put("err-1305", "爆破项目id与名称不能为空");
        sMbErrorMap.put("err-1306", "检查时间不能为空");
        sMbErrorMap.put("err-1307", "检查类型不能为空");
        sMbErrorMap.put("err-1308", "检查内容项不能为空");
        sMbErrorMap.put("err-1309", "项目和单位信息不存在");

        /************** 库房安全检查 *****************/
        sMbErrorMap.put("err-1400", "检查机关id与名称不能为空");
        sMbErrorMap.put("err-1401", "储存仓库id与名称不能为空");
        sMbErrorMap.put("err-1402", "检查时间不能为空");
        sMbErrorMap.put("err-1403", "检查类型不能为空");
        sMbErrorMap.put("err-1404", "检查内容项不能为空");
        /************** 赣州民爆 *****************/
        sMbErrorMap.put("err-360701", "使用数量大于配送数量");
        sMbErrorMap.put("err-360702", "退库数量大于配送数量");
        sMbErrorMap.put("err-360703", "配送数量大于计划数量");
        sMbErrorMap.put("err-360704", "获取信息失败");
        sMbErrorMap.put("err-360705", "只有本单位可以删除");
        sMbErrorMap.put("err-360706", "只有新增的爆破计划可以删除");
        sMbErrorMap.put("err-360707", "获取信息失败");
        sMbErrorMap.put("err-360711", "上传爆破录像失败");
        sMbErrorMap.put("err-360712", "只有公安用户可以检查");
        sMbErrorMap.put("err-360713", "原密码不正确");
        /************** 广西民爆 *****************/
        sMbErrorMap.put("err-450001", "获取查看实时视频用户失败");
        sMbErrorMap.put("err-450002", "该库房中的炸药数量小于要配送的炸药数量");
        sMbErrorMap.put("err-450003", "该库房中的雷管数量小于要配送的雷管数量");

        /**=======================================剧毒易制爆========================================================*/
        sJdErrMap.put("err-9999", "链接服务器超时");
        sJdErrMap.put("err-0000", "服务器错误");

        sJdErrMap.put("err-1001", "用户名不能为空");
        sJdErrMap.put("err-1002", "密码名不能为空");
        sJdErrMap.put("err-1003", "用户名不存在");
        sJdErrMap.put("err-1004", "密码不正确");
        sJdErrMap.put("err-1005", "用户被锁定,禁止登陆");
        sJdErrMap.put("err-1006", "用户过期");
        sJdErrMap.put("err-1007", "用户未对应任何单位");
        sJdErrMap.put("err-1008", "用户所属单位信息不存在");
        sJdErrMap.put("err-1009", "用户所属单位被锁定");
        sJdErrMap.put("err-1100", "SN已失效，请重新登录");

        sJdErrMap.put("err-1101", "设备代码错误");
        sJdErrMap.put("err-1102", "APP被禁止使用");
        sJdErrMap.put("err-1103", "设备唯一标识码为空");

        sJdErrMap.put("err-1200", "购买人不能为空");
        sJdErrMap.put("err-1201", "购买人证件号不能为空");
        sJdErrMap.put("err-1205", "购买人证件照片不能为空");
        sJdErrMap.put("err-1206", "现场照片不能为空");
        sJdErrMap.put("err-1210", "销售人不能为空");
        sJdErrMap.put("err-1211", "销售人证件号不能为空");
        sJdErrMap.put("err-1220", "销售物品不能为空");
        sJdErrMap.put("err-1230", "物品id不能为空");
        sJdErrMap.put("err-1231", "物品不存在");
        sJdErrMap.put("err-1232", "厂家不能为空");
        sJdErrMap.put("err-1233", "批号不能为空");
        sJdErrMap.put("err-1234", "规格不能为空");
        sJdErrMap.put("err-1235", "物品对应的厂家,批号,规格重复");
        sJdErrMap.put("err-1236", "记录id不能为空");
        sJdErrMap.put("err-1237", "记录不存在");
        sJdErrMap.put("err-1250", "实名销售权限不够");
        sJdErrMap.put("err-1251", "实名销售权限不能为空");
        //仅硝基复混肥实名销售才会出现
        sJdErrMap.put("err-1268", "标签参数为空");
        sJdErrMap.put("err-1269", "只能是硝基复混肥实名销售才可使用");
        sJdErrMap.put("err-1270", "标签不存在");
        sJdErrMap.put("err-1271", "标签对应的物品不存在");
        sJdErrMap.put("err-1300", "销售物品的库存不足");

        //新疆 实名销售
        sJdErrMap.put("err-1260", "业务类型不能为空");
        sJdErrMap.put("err-1261", "单位id为空或单位名为空");
        sJdErrMap.put("err-1262", "单位未库存初始化");
        sJdErrMap.put("err-1263", "销售单位不能是本单位");
        sJdErrMap.put("err-1265", "销售单位无对应物品");
        sJdErrMap.put("err-1266", "未选择出入库物品");
        sJdErrMap.put("err-1267", "销售单位无物品");
        sJdErrMap.put("err-1300", "销售物品的库存不足");
    }

    /**
     * 根据error code 获取 error 状态
     *
     * @param errorCode error code
     * @return error 状态
     */
    public static String getErrorMessageByCode(String errorCode,SYSTEM system) {
        if (system == SYSTEM.MINBAO) {
            if (sMbErrorMap.containsKey(errorCode)) {
                return sMbErrorMap.get(errorCode);
            }
        }else {
            if (sJdErrMap.containsKey(errorCode)) {
                return sJdErrMap.get(errorCode);
            }
        }
        
        return "未知错误：" + errorCode;
    }

    public static String getErrorMessage(Throwable throwable, SYSTEM system) {
        if (throwable != null) {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                switch (httpException.code()) {
                    case 403:
                        return "403:用户过期了，请重新登录";
                    case 401:
                        return "401:未授权";
                    case 404:
                        return "404:网页不存在";
                    case 405:
                        return "405:方法不被允许";
                    default:
                        return httpException.message();
                }
            } else if (throwable instanceof UnknownHostException) {
                return "未知域名";
            } else if (throwable instanceof JSONException
                    || throwable instanceof JsonParseException) {
                return "Json解析失败";
            } else if (throwable instanceof SocketTimeoutException) {
                return "连接超时";
            } else {
                String errorStr = throwable.getMessage();

                if (TextUtils.isEmpty(errorStr)) {
                    return "错误信息为空";
                }

                //err开头
                if (errorStr.startsWith("err")) {
                    //形式：err-4444:返回数据为空，返回后一段
                    String errorMsg = "";
                    try {
                        errorMsg = errorStr.split(":")[1];
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        //报错，没有后一段，errorStr即错误码err-4444
                        errorMsg = getErrorMessageByCode(errorStr, system);
                    }
                    return errorMsg;
                } else {
                    //不是error开头，直接返回
                    return errorStr;
                }
            }
        }
        return "错误为空";
    }

}
