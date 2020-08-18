package com.haoyongsys.erp.api.controller.wxpay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.pay.H5SceneInfo;
import com.haoyongsys.erp.common.pojo.pay.WxPayBean;

import cn.aaron911.pay.core.enums.SignType;
import cn.aaron911.pay.core.enums.TradeType;
import cn.aaron911.pay.core.kit.HttpKit;
import cn.aaron911.pay.core.kit.RsaKit;
import cn.aaron911.pay.core.kit.WxPayKit;
import cn.aaron911.pay.wxpay.WxPayApi;
import cn.aaron911.pay.wxpay.WxPayApiConfig;
import cn.aaron911.pay.wxpay.WxPayApiConfigKit;
import cn.aaron911.pay.wxpay.model.GetTransferInfoModel;
import cn.aaron911.pay.wxpay.model.RefundModel;
import cn.aaron911.pay.wxpay.model.RefundQueryModel;
import cn.aaron911.pay.wxpay.model.SendReadPackModel;
import cn.aaron911.pay.wxpay.model.TransferModel;
import cn.aaron911.pay.wxpay.model.UnifiedOrderModel;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 *
 * <p>微信支付 Demo</p>
 *
 */
@Controller
@RequestMapping("/wxPay")
public class WxPayController extends AbstractWxPayApiController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WxPayBean wxPayBean;

    private String notifyUrl;
    private String refundNotifyUrl;
    private static final String USER_PAYING = "USERPAYING";


    @Override
    public WxPayApiConfig getApiConfig() {
        WxPayApiConfig apiConfig;

        try {
            apiConfig = WxPayApiConfigKit.getApiConfig(wxPayBean.getAppId());
        } catch (Exception e) {
            apiConfig = WxPayApiConfig.builder()
                    .appId(wxPayBean.getAppId())
                    .mchId(wxPayBean.getMchId())
                    .partnerKey(wxPayBean.getPartnerKey())
                    .certPath(wxPayBean.getCertPath())
                    .domain(wxPayBean.getDomain())
                    .build();
        }
        notifyUrl = apiConfig.getDomain().concat("/wxPay/payNotify");
        refundNotifyUrl = apiConfig.getDomain().concat("/wxPay/refundNotify");
        return apiConfig;
    }

    @RequestMapping("")
    @ResponseBody
    public String index() {
        log.info("欢迎使用 IJPay 中的微信支付 -By Javen  <br/><br>  ");
        log.info(wxPayBean.toString());
        return ("欢迎使用 IJPay 中的微信支付 -By Javen  <br/><br> ");
    }

    @GetMapping("/test")
    @ResponseBody
    public WxPayBean test() {
        return wxPayBean;
    }

    @GetMapping("/getKey")
    @ResponseBody
    public String getKey() {
        return WxPayApi.getSignKey(wxPayBean.getMchId(), wxPayBean.getPartnerKey(), SignType.MD5);
    }

    /**
     * 微信H5 支付
     * 注意：必须再web页面中发起支付且域名已添加到开发配置中
     */
    @RequestMapping(value = "/wapPay", method = {RequestMethod.POST, RequestMethod.GET})
    public void wapPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }

        H5SceneInfo sceneInfo = new H5SceneInfo();

        H5SceneInfo.H5 h5_info = new H5SceneInfo.H5();
        h5_info.setType("Wap");
        //此域名必须在商户平台--"产品中心"--"开发配置"中添加
        h5_info.setWap_url("https://gitee.com/javen205/IJPay");
        h5_info.setWap_name("IJPay VIP 充值");
        sceneInfo.setH5Info(h5_info);

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-H5支付")
                .attach("Node.js 版:https://gitee.com/javen205/TNWX")
                .out_trade_no(WxPayKit.generateStr())
                .total_fee("1000")
         //       .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.MWEB.getTradeType())
                .scene_info(JSONUtil.toJsonStr(sceneInfo))
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        log.info(xmlResult);

        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String return_code = result.get("return_code");
        String return_msg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(return_code)) {
            throw new RuntimeException(return_msg);
        }
        String result_code = result.get("result_code");
        if (!WxPayKit.codeIsOk(result_code)) {
            throw new RuntimeException(return_msg);
        }
        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回

        String prepayId = result.get("prepay_id");
        String webUrl = result.get("mweb_url");

        log.info("prepay_id:" + prepayId + " mweb_url:" + webUrl);
        response.sendRedirect(webUrl);
    }

    


    /**
     * 微信APP支付
     */
    @RequestMapping(value = "/appPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public R appPay(HttpServletRequest request) {

//        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-App支付")
                .attach("Node.js 版:https://gitee.com/javen205/TNWXX")
                .out_trade_no(WxPayKit.generateStr())
                .total_fee("1000")
      //          .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.APP.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);

        log.info(xmlResult);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
        	return R.Error(returnMsg);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
        	return R.Error(returnMsg);
        }
        // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
        String prepayId = result.get("prepay_id");

        Map<String, String> packageParams = WxPayKit.appPrepayIdCreateSign(wxPayApiConfig.getAppId(), wxPayApiConfig.getMchId(), prepayId,
                wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String jsonStr = JSONUtil.parseFromMap(packageParams).toString();
        log.info("返回apk的参数:" + jsonStr);
        return R.OK(jsonStr);
    }

    /**
     * 微信小程序支付
     */
    @RequestMapping(value = "/miniAppPay", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public R miniAppPay(HttpServletRequest request) {
        //需要通过授权来获取openId
        String openId = (String) request.getSession().getAttribute("openId");
//
//        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body("IJPay 让支付触手可及-小程序支付")
                .attach("Node.js 版:https://gitee.com/javen205/TNWXX")
                .out_trade_no(WxPayKit.generateStr())
                .total_fee("1000")
 //               .spbill_create_ip(ip)
                .notify_url(notifyUrl)
                .trade_type(TradeType.JSAPI.getTradeType())
                .openid(openId)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);

        log.info(xmlResult);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);

        String returnCode = result.get("return_code");
        String returnMsg = result.get("return_msg");
        if (!WxPayKit.codeIsOk(returnCode)) {
        	return R.Error(returnMsg);
        }
        String resultCode = result.get("result_code");
        if (!WxPayKit.codeIsOk(resultCode)) {
        	return R.Error(returnMsg);
        }
        // 以下字段在 return_code 和 result_code 都为 SUCCESS 的时候有返回
        String prepayId = result.get("prepay_id");
        Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayApiConfig.getAppId(), prepayId,
                wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256);
        String jsonStr = JSONUtil.parseFromMap(packageParams).toString();
        log.info("小程序支付的参数:" + jsonStr);
        return R.OK(jsonStr);
    }

    /**
     * 企业付款到零钱
     */
    @RequestMapping(value = "/transfer", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String transfer(HttpServletRequest request, @RequestParam("openId") String openId) {

//        String ip = IpKit.getRealIp(request);
//        if (StrKit.isBlank(ip)) {
//            ip = "127.0.0.1";
//        }

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = TransferModel.builder()
                .mch_appid(wxPayApiConfig.getAppId())
                .mchid(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .partner_trade_no(WxPayKit.generateStr())
                .openid(openId)
                .check_name("NO_CHECK")
                .amount("100")
                .desc("IJPay 让支付触手可及-企业付款")
  //              .spbill_create_ip(ip)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256, false);

        // 提现
        String transfers = WxPayApi.transfers(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        log.info("提现结果:" + transfers);
        Map<String, String> map = WxPayKit.xmlToMap(transfers);
        String returnCode = map.get("return_code");
        String resultCode = map.get("result_code");
        if (WxPayKit.codeIsOk(returnCode) && WxPayKit.codeIsOk(resultCode)) {
            // 提现成功
        } else {
            // 提现失败
        }
        return transfers;
    }

    /**
     * 查询企业付款到零钱
     */
    @RequestMapping(value = "/transferInfo", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String transferInfo(@RequestParam("partner_trade_no") String partnerTradeNo) {
        try {
            WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

            Map<String, String> params = GetTransferInfoModel.builder()
                    .nonce_str(WxPayKit.generateStr())
                    .partner_trade_no(partnerTradeNo)
                    .mch_id(wxPayApiConfig.getMchId())
                    .appid(wxPayApiConfig.getAppId())
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256, false);

            return WxPayApi.getTransferInfo(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取RSA加密公钥
     */
    @RequestMapping(value = "/getPublicKey", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getPublicKey() {
        try {
            WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

            Map<String, String> params = new HashMap<String, String>(4);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("nonce_str", String.valueOf(System.currentTimeMillis()));
            params.put("sign_type", "MD5");
            String createSign = WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.MD5);
            params.put("sign", createSign);
            return WxPayApi.getPublicKey(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 企业付款到银行卡
     */
    @RequestMapping(value = "/payBank", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String payBank() {
        try {
            WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

            //通过WxPayApi.getPublicKey接口获取RSA加密公钥
            //假设获取到的RSA加密公钥为PUBLIC_KEY(PKCS#8格式)
            final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6Bl76IwSvBTiibZ+CNRUA6BfahMshZ0WJpHD1GpmvcQjeN6Yrv6c9eIl6gB4nU3isN7bn+LmoVTpH1gHViaV2YyG/zXj4z4h7r+V+ezesMqqorEg38BCNUHNmhnw4/C0I4gBAQ4x0SJOGnfKGZKR9yzvbkJtvEn732JcEZCbdTZmaxkwlenXvM+mStcJaxBCB/h5xJ5VOF5nDbTPzLphIpzddr3zx/Jxjna9QB1v/YSKYXn+iuwruNUXGCvvxBWaBGKrjOdRTRy9adWOgNmtuYDQJ2YOfG8PtPe06ELKjmr2CfaAGrKKUroyaGvy3qxAV0PlT+UQ4ADSXWt/zl0o5wIDAQAB";

            Map<String, String> params = new HashMap<String, String>(10);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("partner_trade_no", System.currentTimeMillis() + "");
            params.put("nonce_str", System.currentTimeMillis() + "");
            //收款方银行卡号
            params.put("enc_bank_no", RsaKit.encryptByPublicKeyByWx("银行卡号", PUBLIC_KEY));
            //收款方用户名
            params.put("enc_true_name", RsaKit.encryptByPublicKeyByWx("银行卡持有人姓名", PUBLIC_KEY));
            //收款方开户行
            params.put("bank_code", "1001");
            params.put("amount", "1");
            params.put("desc", "IJPay 让支付触手可及-付款到银行卡");
            params.put("sign", WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.HMACSHA256));
            return WxPayApi.payBank(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询企业付款到银行
     */
    @RequestMapping(value = "/queryBank", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String queryBank(@RequestParam("partner_trade_no") String partnerTradeNo) {
        try {
            WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

            Map<String, String> params = new HashMap<String, String>(4);
            params.put("mch_id", wxPayApiConfig.getMchId());
            params.put("partner_trade_no", partnerTradeNo);
            params.put("nonce_str", System.currentTimeMillis() + "");
            params.put("sign", WxPayKit.createSign(params, wxPayApiConfig.getPartnerKey(), SignType.MD5));
            return WxPayApi.queryBank(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信退款
     */
    @RequestMapping(value = "/refund", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refund(@RequestParam("transactionId") String transactionId,
                         @RequestParam("out_trade_no") String outTradeNo) {

        if (StrUtil.isBlank(outTradeNo) && StrUtil.isBlank(transactionId)) {
            return "transactionId、out_trade_no二选一";
        }
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = RefundModel.builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .transaction_id(transactionId)
                .out_trade_no(outTradeNo)
                .out_refund_no(WxPayKit.generateStr())
                .total_fee("1")
                .refund_fee("1")
                .notify_url(refundNotifyUrl)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);

        return WxPayApi.orderRefund(false, params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
    }

    /**
     * 微信退款查询
     */
    @RequestMapping(value = "/refundQuery", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refundQuery(@RequestParam("transactionId") String transactionId,
                              @RequestParam("out_trade_no") String outTradeNo,
                              @RequestParam("out_refund_no") String outRefundNo,
                              @RequestParam("refund_id") String refundId) {

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        Map<String, String> params = RefundQueryModel.builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .transaction_id(transactionId)
                .out_trade_no(outTradeNo)
                .out_refund_no(outRefundNo)
                .refund_id(refundId)
                .build()
                .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);

        return WxPayApi.orderRefundQuery(false, params);
    }

    /**
     * 退款通知
     */
    @RequestMapping(value = "/refundNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String refundNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("退款通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");
        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        if (WxPayKit.codeIsOk(returnCode)) {
            String reqInfo = Base64.decodeStr(params.get("req_info"));
            String decryptData = WxPayKit.decryptData(reqInfo, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey());
            log.info("退款通知解密后的数据=" + decryptData);
            // 更新订单信息
            // 发送通知等
            Map<String, String> xml = new HashMap<String, String>(2);
            xml.put("return_code", "SUCCESS");
            xml.put("return_msg", "OK");
            return WxPayKit.toXml(xml);
        }
        return null;
    }
    

    @RequestMapping(value = "/sendRedPack", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String sendRedPack(HttpServletRequest request, @RequestParam("openId") String openId) {
        try {
//            String ip = IpKit.getRealIp(request);
//            if (StrKit.isBlank(ip)) {
//                ip = "127.0.0.1";
//            }

            WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

            Map<String, String> params = SendReadPackModel.builder()
                    .nonce_str(WxPayKit.generateStr())
                    .mch_billno(WxPayKit.generateStr())
                    .mch_id(wxPayApiConfig.getMchId())
                    .wxappid(wxPayApiConfig.getAppId())
                    .send_name("IJPay 红包测试")
                    .re_openid(openId)
                    .total_amount("1000")
                    .total_num("1")
                    .wishing("感谢您使用 IJPay")
 //                   .client_ip(ip)
                    .act_name("感恩回馈活动")
                    .remark("点 start 送红包，快来抢!")
                    .build()
                    .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5);
            String result = WxPayApi.sendRedPack(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
            System.out.println("发送红包结果:" + result);
            Map<String, String> map = WxPayKit.xmlToMap(result);
            return JSONUtil.toJsonStr(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步通知
     */
    @RequestMapping(value = "/payNotify", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String payNotify(HttpServletRequest request) {
        String xmlMsg = HttpKit.readData(request);
        log.info("支付通知=" + xmlMsg);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);

        String returnCode = params.get("return_code");

        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 注意此处签名方式需与统一下单的签名类型一致
        if (WxPayKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                // 更新订单信息
                // 发送通知等
                Map<String, String> xml = new HashMap<String, String>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            }
        }
        return null;
    }
}
