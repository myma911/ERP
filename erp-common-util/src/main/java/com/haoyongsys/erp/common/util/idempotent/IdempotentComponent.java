package com.haoyongsys.erp.common.util.idempotent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.haoyongsys.erp.common.pojo.R;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.constant.Constant;
import com.haoyongsys.erp.common.pojo.exception.AException;
import com.haoyongsys.erp.common.util.redis.RedisUtil;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

@Component
public class IdempotentComponent {

   

    @Autowired
    private RedisUtil redisUtil;

    public R createToken() {
        String str = IdUtil.objectId();
        StrBuilder token = new StrBuilder();
        token.append(Constant.IdempotentComponent.TOKEN_PREFIX).append(str);
        boolean success = redisUtil.set(token.toString(), token.toString(), Constant.IdempotentComponent.EXPIRE_TIME_SECOND);
        if (success) {
        	return R.OK(token.toString());
        }
        // 幂等性校验码生成失败
        return R.Error();
    }

    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.IdempotentComponent.IDEMPOTENT_NAME);
        if (StrUtil.isBlank(token)) {// header中不存在token
            token = request.getParameter(Constant.IdempotentComponent.IDEMPOTENT_NAME);
            if (StrUtil.isBlank(token)) {// parameter中也不存在token
                throw new AException(StateCodeEnum.UNIQUE_CANNOT_EMPTY);
            }
        }

        if (!redisUtil.hasKey(token)) {
        	throw new AException(StateCodeEnum.UNIQUE_INVALID);
        }

        Boolean del = redisUtil.del(token);
        if (null == del || !del) {
            throw new AException(StateCodeEnum.UNIQUE_INVALID);
        }
    }
}